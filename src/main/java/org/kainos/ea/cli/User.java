package org.kainos.ea.cli;

import java.security.Principal;

public class User implements Principal {
    private final int userId;
    private final String email;
    private final Role role;

    public String getHashedPassword() {
        return hashedPassword;
    }

    private String hashedPassword;


    public User(int userId, String email, Role role, String hashedPassword) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.hashedPassword = hashedPassword;
    }



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


    @Override
    public String getName(){
        return getEmail();
    }
    public Role getRole() {
        return role;
    }
}
