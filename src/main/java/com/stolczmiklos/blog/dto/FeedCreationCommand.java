package com.stolczmiklos.blog.dto;

public class FeedCreationCommand {

    private String author;
    private String title;
    private String text;
    private String imageUrl;
    private String videoUrl;

    public FeedCreationCommand() {
    }

    public FeedCreationCommand(String author, String title, String text, String imageUrl, String videoUrl) {
        this.author = author;
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
