package com.stolczmiklos.blog.dto;

import com.stolczmiklos.blog.domain.RatingEntity;

import java.time.format.DateTimeFormatter;

public class RatingListItem {

    private Long id;
    private String userName;
    private String text;
    private String createdAt;
    private Long feedID;
    private Long commentID;

    public RatingListItem() {
    }

    public RatingListItem(RatingEntity rating, Long feedID, Long commentID) {
        this.feedID = feedID;
        this.commentID = commentID;
        this.id = rating.getId();
        this.userName = rating.getAccount().getUserName();
        this.createdAt = rating.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy. MMMM. dd. - HH:mm"));
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getFeedID() {
        return feedID;
    }

    public void setFeedID(Long feedID) {
        this.feedID = feedID;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }
}
