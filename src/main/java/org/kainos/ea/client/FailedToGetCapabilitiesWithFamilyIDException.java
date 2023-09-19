package org.kainos.ea.client;

public class FailedToGetCapabilitiesWithFamilyIDException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get Capabilities with FamilyID";
    }
}
