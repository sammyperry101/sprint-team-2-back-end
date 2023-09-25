package org.kainos.ea.client;

public class FailedToGetFamilyException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get family.";
    }
}
