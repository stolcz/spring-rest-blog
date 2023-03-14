package com.stolczmiklos.blog.dto;


import com.stolczmiklos.blog.domain.ConfigEntity;

public class ConfigRequest {

    private boolean registrationConfirmation;

    public ConfigRequest() {
    }

    public ConfigRequest(ConfigEntity config) {
        this.registrationConfirmation = config.isRegistrationConfirmation();
    }

    public boolean isRegistrationConfirmation() {
        return registrationConfirmation;
    }

    public void setRegistrationConfirmation(boolean registrationConfirmation) {
        this.registrationConfirmation = registrationConfirmation;
    }

}
