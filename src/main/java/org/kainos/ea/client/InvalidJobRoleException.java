package org.kainos.ea.client;

public class InvalidJobRoleException extends Throwable {
    String message;
    public InvalidJobRoleException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return "Invalid Job Role: " + message;
    }
}
