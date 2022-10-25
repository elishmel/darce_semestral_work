package cz.cvut.fit.tjv.nebesluk.dao.memory;

import cz.cvut.fit.tjv.nebesluk.dao.repository.TagRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Tag;

public class TagItemInMemRepository extends InMemoryRepository<Tag,String> implements TagRepository {
}
