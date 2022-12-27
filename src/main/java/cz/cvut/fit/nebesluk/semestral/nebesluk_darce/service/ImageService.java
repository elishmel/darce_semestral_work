package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Image;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ImageRepository;

public class ImageService extends AbstractService<Image,Long>{
    public ImageService(ImageRepository repository){
        super(repository);
    }
}
