package org.kainos.ea.cli;

public enum Role {
    EMPLOYEE(2),
    ADMIN(1);

    private final int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public static Role fromId(int id) {
        switch (id) {
            case 0:
                return EMPLOYEE;
            case 1:
                return ADMIN;
        }

        return null;
    }
}