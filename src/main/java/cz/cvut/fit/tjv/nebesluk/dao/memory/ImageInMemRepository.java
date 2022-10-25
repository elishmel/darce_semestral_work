package cz.cvut.fit.tjv.nebesluk.dao.memory;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ImageRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Image;

public class ImageInMemRepository extends InMemoryRepository<Image,Long> implements ImageRepository {
}
