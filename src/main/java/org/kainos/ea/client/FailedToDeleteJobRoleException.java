package org.kainos.ea.client;

public class FailedToDeleteJobRoleException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to delete Job Role";
    }
}
