package cz.cvut.fit.tjv.nebesluk.domain;

public interface DomainEntity<T> {

    /**
     * Get primary key for this entity
     * @return primary key
     */
    T getId();
}
