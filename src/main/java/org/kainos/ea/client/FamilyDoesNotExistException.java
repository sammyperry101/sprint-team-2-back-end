package org.kainos.ea.client;

public class FamilyDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Family does not exist";
    }
}
