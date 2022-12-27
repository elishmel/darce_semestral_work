package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.DomainEntity;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractService<Entity extends DomainEntity<Key>,Key> {

    JpaRepository<Entity, Key> repository;

    protected AbstractService(JpaRepository<Entity, Key> repository_) {
        repository = repository_;
    }

    public Entity Create(Entity entity) {
        Objects.requireNonNull(entity);
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new EntityAlreadyExistsException(entity);
        }
        return repository.save(entity);
    }

    public Entity Update(Entity entity) {
        Objects.requireNonNull(entity);
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            return repository.save(entity);
        }
        throw new EntityNotExistsException(entity);
    }

    public Collection<Entity> ReadAll(){
        return repository.findAll();
    }

    public Optional<Entity> ReadById(Key ID){
        return repository.findById(ID);
    }

    public void DeleteById(Key ID){
        if(repository.existsById(ID)){
            repository.deleteById(ID);
        }
        throw new EntityNotExistsException("No entity with ID " + ID);
    }

}
