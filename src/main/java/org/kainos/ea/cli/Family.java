package org.kainos.ea.cli;

public class Family {
    private int familyId;
    private String name;
    private int capability;

    public Family(int familyId, String name, int capability) {
        this.familyId = familyId;
        this.name = name;
        this.capability = capability;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapability() {
        return capability;
    }

    public void setCapability(int capability) {
        this.capability = capability;
    }
}
