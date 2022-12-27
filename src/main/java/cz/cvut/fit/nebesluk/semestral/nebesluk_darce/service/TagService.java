package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.TagRepository;

public class TagService extends AbstractService<Tag,String> {

    public TagService(TagRepository repository){
        super(repository);
    }
}
