package org.kainos.ea.client;

public class FailedToGetRoleException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get job roles!";
    }
}
