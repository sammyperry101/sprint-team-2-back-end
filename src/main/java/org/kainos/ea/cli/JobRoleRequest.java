package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {
    private int RoleID;
    private String roleName;
    private String sharepointLink;
    private String bandName;
    private String capabilityName;

    @JsonCreator
    public JobRoleRequest(@JsonProperty("roleId") int roleID,
                          @JsonProperty("roleName") String roleName,
                          @JsonProperty("sharepointLink") String sharepointLink,
                          @JsonProperty("bandName") String bandName,
                          @JsonProperty("capabilityName") String capabilityName) {
        RoleID = roleID;
        this.roleName = roleName;
        this.sharepointLink = sharepointLink;
        this.bandName = bandName;
        this.capabilityName = capabilityName;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSharepointLink() {
        return sharepointLink;
    }

    public void setSharepointLink(String sharepointLink) {
        this.sharepointLink = sharepointLink;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
