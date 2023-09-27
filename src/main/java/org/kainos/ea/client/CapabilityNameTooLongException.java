package org.kainos.ea.client;

public class CapabilityNameTooLongException extends Throwable{
    @Override
    public String getMessage(){
        return "Capability Name is over 70 characters";
    }
}
