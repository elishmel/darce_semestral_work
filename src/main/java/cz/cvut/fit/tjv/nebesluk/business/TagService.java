package cz.cvut.fit.tjv.nebesluk.business;

import cz.cvut.fit.tjv.nebesluk.dao.repository.TagRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Tag;

public class TagService extends CrudService<Tag,String> {
    public TagService(TagRepository tagRepository){
        super(tagRepository);
    }
}