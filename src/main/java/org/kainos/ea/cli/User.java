package org.kainos.ea.cli;

public class User {
    private final int userId;
    private final String email;
    private final Role role;

    public User(int userId, String email, Role role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
