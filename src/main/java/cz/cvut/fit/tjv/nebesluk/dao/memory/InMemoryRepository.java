package cz.cvut.fit.tjv.nebesluk.dao.memory;

import cz.cvut.fit.tjv.nebesluk.dao.repository.CrudRepository;
import cz.cvut.fit.tjv.nebesluk.domain.DomainEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryRepository<T extends DomainEntity<ID>,ID> implements CrudRepository<T,ID> {

    private Map<ID,T> database = new HashMap<>();

    public T save(T e){
        database.put(e.getId(),e);
        return e;
    }

    public boolean existsByID(ID id){
        return database.containsKey(id);
    }

    public Optional<T> findByID(ID id){
        return Optional.ofNullable(database.get(id));
    }

    public Collection<T> findAll(){
        return database.values();
    }

    public void deleteByID(ID id){
        database.remove(id);
    }
}
