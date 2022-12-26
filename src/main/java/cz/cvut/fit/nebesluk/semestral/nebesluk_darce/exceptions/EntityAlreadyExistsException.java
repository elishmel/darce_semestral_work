package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions;

public class EntityAlreadyExistsException extends EntityStateException {
    public EntityAlreadyExistsException(){

    }

    public <E> EntityAlreadyExistsException(E entity){
        super("Entity already exists " + entity);
    }

    public EntityAlreadyExistsException(String s){
        super(s);
    }
}