package com.stolczmiklos.blog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "token")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @OneToOne(mappedBy = "token")
    private AccountEntity accountEntity;

    public TokenEntity() {
    }

    public TokenEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
        this.actionType = ActionType.REGISTRATION;
        this.expirationDate = LocalDateTime.now().plusMinutes(10);
        this.code = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }










}
