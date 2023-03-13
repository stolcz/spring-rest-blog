package com.stolczmiklos.blog.domain;

import com.stolczmiklos.blog.dto.SaveAccountRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    @OneToMany(mappedBy = "account")
    private List<FeedEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<CommentEntity> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    @OneToMany(mappedBy = "account")
    private List<RatingEntity> ratingEntities = new ArrayList<>();

    @OneToOne
    @JoinColumn
    private Token token;

    private Boolean isConfirmed;

    private LocalDateTime timeStamp;

    public AccountEntity() {
    }

    public AccountEntity(SaveAccountRequest saveAccountRequest) {
        this.userName = saveAccountRequest.getUserName();
        this.email = saveAccountRequest.getEmail();
        this.role = Role.ROLE_USER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<FeedEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<FeedEntity> posts) {
        this.posts = posts;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RatingEntity> getRatingEntities() {
        return ratingEntities;
    }

    public void setRatingEntities(List<RatingEntity> ratingEntities) {
        this.ratingEntities = ratingEntities;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
