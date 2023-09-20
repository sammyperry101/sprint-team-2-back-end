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

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public AuthRole(int roleID, String role_name) {
        this.roleID = roleID;
        this.role_name = role_name;
    }

    private int roleID;
    private String role_name;


}
