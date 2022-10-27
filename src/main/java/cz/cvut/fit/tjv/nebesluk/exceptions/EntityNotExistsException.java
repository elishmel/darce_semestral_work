package cz.cvut.fit.tjv.nebesluk.exceptions;

public class EntityNotExistsException extends EntityStateException{
    public EntityNotExistsException(){

    }

    public <E> EntityNotExistsException(E entity){
        super("Entity was not found " + entity);
    }

    public EntityNotExistsException(String s){
        super(s);
    }
}