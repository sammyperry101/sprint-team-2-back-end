package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleFilter {

    private String roleNameFilter;
    private int bandID;
    private int capabilityID;

    @JsonCreator
    public JobRoleFilter(
            @JsonProperty("roleNameFilter") String roleNameFilter,
            @JsonProperty("bandID") int bandID,
            @JsonProperty("capabilityId") int capabilityID){
        this.roleNameFilter = roleNameFilter;
        this.bandID = bandID;
        this.capabilityID = capabilityID;
    }

    public String getRoleNameFilter() {
        return roleNameFilter;
    }

    public void setRoleNameFilter(String roleNameFilter) {
        this.roleNameFilter = roleNameFilter;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(int capabilityID) {
        this.capabilityID = capabilityID;
    }
}
