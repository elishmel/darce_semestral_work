package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Image;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

@Service
public class ImageService extends AbstractService<Image,Long>{

    public ImageService(ImageRepository repository){
        super(repository);
    }

    @Override
    public Image Create(Image entity) {
        entity.setImage_id((((ImageRepository)repository).getMaxId().orElse((long)-1)) + 1);
        return super.Create(entity);
    }

    @Override
    public void DeleteById(Long ID) {

        try{
            var v = GetById(ID).orElseThrow(EntityNotExistsException::new).getUrl().replace("http://localhost:8080/","");
            Path path = Paths.get(this.getClass().getClassLoader().getResource("public/pictures").getPath()+v);
            Files.delete(path);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        super.DeleteById(ID);
    }

    public Collection<Image> GetAll(){
        return repository.findAll();
    }

    public Optional<Image> GetById(Long id){
        return ((ImageRepository)repository).findById(id);
    }

}
