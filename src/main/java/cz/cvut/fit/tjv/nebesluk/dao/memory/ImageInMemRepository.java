package cz.cvut.fit.tjv.nebesluk.dao.memory;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ImageRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageInMemRepository extends InMemoryRepository<Image,Long> implements ImageRepository {
}
