package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FamilyRequest {

    private int familyID;
    private String name;
    private int capabilityID;

    @JsonCreator
    public FamilyRequest(
            @JsonProperty("familyID") int familyID,
            @JsonProperty("name") String name,
            @JsonProperty("capabilityID") int capabilityID
    ){
        this.familyID = familyID;
        this.name = name;
        this.capabilityID = capabilityID;
    }

    public int getFamilyID() {
        return familyID;
    }

    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(int capabilityID) {
        this.capabilityID = capabilityID;
    }
}
