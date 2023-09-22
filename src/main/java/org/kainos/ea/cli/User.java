package org.kainos.ea.cli;

public class User {
    private final int userId;
    private final String email;
    private final AuthRole role;

    public String getHashedPassword() {
        return hashedPassword;
    }

    private String hashedPassword;


    public User(int userId, String email, AuthRole role, String hashedPassword) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.hashedPassword = hashedPassword;
    }



    public User(int userId, String email, AuthRole role) {
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

    public AuthRole getRole() {
        return role;
    }
}
