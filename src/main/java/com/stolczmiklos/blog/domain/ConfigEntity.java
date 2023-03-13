package com.stolczmiklos.blog.domain;

import javax.persistence.*;

@Entity
@Table(name = "config")
public class ConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private boolean registrationConfirmation;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isRegistrationConfirmation() {
        return registrationConfirmation;
    }

    public void setRegistrationConfirmation(boolean registrationConfirmation) {
        this.registrationConfirmation = registrationConfirmation;
    }

}
