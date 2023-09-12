package org.kainos.ea.client;

public class JobRoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Delivery Employee doesn't exist";
    }
}
