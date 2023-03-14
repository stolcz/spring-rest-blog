package com.stolczmiklos.blog.dto;

import com.stolczmiklos.blog.domain.AccountEntity;
import com.stolczmiklos.blog.domain.CommentEntity;
import com.stolczmiklos.blog.domain.FeedEntity;

import java.util.ArrayList;
import java.util.List;

public class UserInfoRequest {

    private String userName;
    private String email;
    private List<FeedEntity> feeds = new ArrayList<>();
    private List<CommentEntity> comments = new ArrayList<>();

    public UserInfoRequest() {
    }

    public UserInfoRequest(AccountEntity accountEntity) {
        this.userName = accountEntity.getUserName();
        this.email = accountEntity.getEmail();
        this.feeds = accountEntity.getFeeds();
        this.comments = accountEntity.getComments();
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

    public List<FeedEntity> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedEntity> feeds) {
        this.feeds = feeds;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
