package cz.cvut.fit.tjv.nebesluk.business;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ImageRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends CrudService<Image,Long>{
    public ImageService(ImageRepository imageRepository){
        super(imageRepository);
    }
}