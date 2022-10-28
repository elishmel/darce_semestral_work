package cz.cvut.fit.tjv.nebesluk.domain;

import java.io.Serializable;

public interface DomainEntity<T> extends Serializable {

    /**
     * Get primary key for this entity
     * @return primary key
     */
    T getId();
}
