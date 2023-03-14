package com.stolczmiklos.blog.dto;

import javax.validation.constraints.NotBlank;

public class AccountModificationRequest {
    @NotBlank(message = "Töltse ki a mezőt!")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
