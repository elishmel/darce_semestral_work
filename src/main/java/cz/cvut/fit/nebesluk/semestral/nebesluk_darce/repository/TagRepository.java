package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,String> {
}
