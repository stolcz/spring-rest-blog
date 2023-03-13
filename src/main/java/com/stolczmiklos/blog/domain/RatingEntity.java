package com.stolczmiklos.blog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @OneToOne
    private CommentEntity comment;

    @OneToOne
    private FeedEntity feed;

    @ManyToOne
    private AccountEntity account;

    public RatingEntity() {
    }

    public RatingEntity(AccountEntity account, CommentEntity comment, FeedEntity feed) {
        this.account = account;
        this.comment = comment;
        this.feed = feed;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }

    public FeedEntity getFeed() {
        return feed;
    }

    public void setFeed(FeedEntity feed) {
        this.feed = feed;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
