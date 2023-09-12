package org.kainos.ea.client;

public class FailedToDeleteJobRoleException extends Throwable {
    @Override
    public String getMessage(){
        return "Delivery Employee doesn't exist";
    }
}
