package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Image;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends AbstractService<Image,Long>{
    public ImageService(ImageRepository repository){
        super(repository);
    }
}
