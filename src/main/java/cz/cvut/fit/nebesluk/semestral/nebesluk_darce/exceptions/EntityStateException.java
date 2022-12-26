package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions;

public class EntityStateException extends RuntimeException{
    public EntityStateException(){

    }

    public <E> EntityStateException(E entity){
        super("Invalid state of entity " + entity);
    }

    public EntityStateException(String s){
        super(s);
    }
}