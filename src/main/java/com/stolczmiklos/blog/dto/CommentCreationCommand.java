package com.stolczmiklos.blog.dto;

public class CommentCreationCommand {

    private Long feedID;
    private String author;
    private String text;

    public Long getFeedID() {
        return feedID;
    }

    public void setFeedID(Long feedID) {
        this.feedID = feedID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
