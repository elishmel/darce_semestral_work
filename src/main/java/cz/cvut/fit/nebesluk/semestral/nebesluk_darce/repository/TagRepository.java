package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,String> {
}
