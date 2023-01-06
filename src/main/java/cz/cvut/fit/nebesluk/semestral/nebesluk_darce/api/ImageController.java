package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Image;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    ImageService imageService;

    ImageController(ImageService imageService_){
        imageService = imageService_;
    }


    @GetMapping
    public Collection<Image> GetAllImages(){
        return imageService.GetAll();
    }

    @GetMapping("/{id}")
    public Image GetImageWithId(@PathVariable Long id){
        try {
            return imageService.GetById(id).orElseThrow(EntityNotExistsException::new);
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Image ReceiveImage(@RequestParam MultipartFile image, @Value("${server.url}") String server) throws IOException {
        // TODO: Belongs in ImageService
        String name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + image.hashCode();
        String extension = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        String fileName = name+extension;
        String filePath = "/pictures/"+fileName;
        Path path = Paths.get(this.getClass().getClassLoader().getResource("public/pictures").getPath()+"/"+fileName);
        Files.write(path,image.getBytes());
        Image i = new Image();
        i.setUrl(server+filePath);
        return imageService.Create(i);
    }

    @PutMapping("/{id}")
    public Image Update(@PathVariable Long id,@Value("${server.url}") String server,@RequestParam MultipartFile image) throws IOException {
        Delete(id);
        return ReceiveImage(image,server);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id){
        try{
            imageService.DeleteById(id);
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
