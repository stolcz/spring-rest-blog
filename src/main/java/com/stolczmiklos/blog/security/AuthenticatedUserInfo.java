package com.stolczmiklos.blog.security;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUserInfo {

    private String userName;
    private String email;

    public AuthenticatedUserInfo() {
    }

    public AuthenticatedUserInfo(UserDetails user) {
        this.userName = user.getUsername();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
