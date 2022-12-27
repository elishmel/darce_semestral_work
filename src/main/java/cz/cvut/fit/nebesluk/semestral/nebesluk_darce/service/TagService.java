package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService extends AbstractService<Tag,String> {

    public TagService(TagRepository repository){
        super(repository);
    }
}
