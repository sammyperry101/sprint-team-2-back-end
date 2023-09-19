package org.kainos.ea.client;

public class CapabilitiesWithFamilyIDNotFound extends Throwable{
    @Override
    public String getMessage(){
        return "Could not find Capabilities with FamilyID";
    }
}
