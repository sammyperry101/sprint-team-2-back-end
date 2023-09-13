package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    private String token;
    private String email;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }



    @JsonCreator
    public LoginResponse(
            @JsonProperty("token") String token,
            @JsonProperty("email") String email
    ) {
        this.token = token;
        this.email = email;
    }
}
