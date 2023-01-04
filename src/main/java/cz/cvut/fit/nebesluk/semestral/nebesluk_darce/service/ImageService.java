package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Image;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ClientRepository;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Service
public class ImageService extends AbstractService<Image,Long>{

    public ImageService(ImageRepository repository){
        super(repository);
    }

    @Override
    public Image Create(Image entity) {
        entity.setImage_id(((ImageRepository)repository).getMaxId().orElse((long)-1)+1);
        return super.Create(entity);
    }

    @Override
    public void DeleteById(Long ID) throws IOException {
        var v = GetById(ID).getUrl().replace("http://localhost:8080/","");
        Path path = Paths.get(this.getClass().getClassLoader().getResource("public/pictures").getPath()+v);
        Files.delete(path);
        super.DeleteById(ID);
    }

    public Collection<Image> GetAll(){
        return repository.findAll();
    }

    public Image GetById(Long id){
        return ((ImageRepository)repository).findById(id).orElseThrow(EntityNotExistsException::new);
    }

}
