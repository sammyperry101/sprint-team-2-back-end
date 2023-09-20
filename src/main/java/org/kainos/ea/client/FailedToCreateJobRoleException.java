package org.kainos.ea.client;

public class FailedToCreateJobRoleException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to create job role.";
    }
}
