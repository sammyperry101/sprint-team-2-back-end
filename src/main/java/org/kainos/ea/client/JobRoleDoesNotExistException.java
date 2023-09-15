package org.kainos.ea.client;

public class JobRoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Job Role doesn't exist";
    }
}