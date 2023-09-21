package org.kainos.ea.cli;

public class AuthRole {
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRoleName(String roleName) {
        this.role_name = roleName;
    }

    public AuthRole(int roleID, String roleName) {
        this.roleID = roleID;
        this.role_name = roleName;
    }

    private int roleID;
    private String role_name;


}
