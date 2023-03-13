package com.stolczmiklos.blog.service;

import com.stolczmiklos.blog.repository.CommentRepository;
import com.stolczmiklos.blog.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedService {

    private FeedRepository feedRepository;

    private CommentRepository commentRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository, CommentRepository commentRepository) {
        this.feedRepository = feedRepository;
        this.commentRepository = commentRepository;
    }

}
