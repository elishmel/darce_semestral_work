package cz.cvut.fit.tjv.nebesluk.business;

import cz.cvut.fit.tjv.nebesluk.dao.repository.CrudRepository;
import cz.cvut.fit.tjv.nebesluk.domain.DomainEntity;
import cz.cvut.fit.tjv.nebesluk.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.tjv.nebesluk.exceptions.EntityNotExistsException;

import java.util.Optional;

public abstract class CrudService <E extends DomainEntity<K>,K> {

    protected final CrudRepository<E,K> repository;

    protected CrudService(CrudRepository<E,K> _repository){
        repository = _repository;
    }

    public E create(E entity){
        if(repository.existsByID(entity.getId())){
            throw new EntityAlreadyExistsException(entity);
        }
        return repository.save(entity);
    }

    public Optional<E> readByID(K id){
        return repository.findByID(id);
    }

    public Iterable<E> readAll(){
        return repository.findAll();
    }

    public E update(E entity){
        if(repository.existsByID(entity.getId())){
            return repository.save(entity);
        }
        else{
            throw new EntityNotExistsException(entity);
        }
    }

    public void deleteByID(K id){
        repository.deleteByID(id);
    }
}
