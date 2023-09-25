package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleFilter {

    private String roleNameFilter;
    private String bandNameFilter;
    private String capabilityNameFilter;

    @JsonCreator
    JobRoleFilter(
            @JsonProperty("roleNameFilter") String roleNameFilter,
            @JsonProperty("bandNameFilter") String bandNameFilter,
            @JsonProperty("capabilityNameFilter") String capabilityNameFilter){
        this.roleNameFilter = roleNameFilter;
        this.bandNameFilter = bandNameFilter;
        this.capabilityNameFilter = capabilityNameFilter;
    }

    public String getRoleNameFilter() {
        return roleNameFilter;
    }

    public void setRoleNameFilter(String roleNameFilter) {
        this.roleNameFilter = roleNameFilter;
    }

    public String getBandNameFilter() {
        return bandNameFilter;
    }

    public void setBandNameFilter(String bandNameFilter) {
        this.bandNameFilter = bandNameFilter;
    }

    public String getCapabilityNameFilter() {
        return capabilityNameFilter;
    }

    public void setCapabilityNameFilter(String capabilityNameFilter) {
        this.capabilityNameFilter = capabilityNameFilter;
    }
}
