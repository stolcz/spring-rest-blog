package com.stolczmiklos.blog.service;

import com.stolczmiklos.blog.controller.FeedController;
import com.stolczmiklos.blog.domain.CommentEntity;
import com.stolczmiklos.blog.domain.FeedEntity;
import com.stolczmiklos.blog.dto.FeedCreationCommand;
import com.stolczmiklos.blog.repository.CommentRepository;
import com.stolczmiklos.blog.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);

    private FeedRepository feedRepository;
    private CommentRepository commentRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository, CommentRepository commentRepository) {
        this.feedRepository = feedRepository;
        this.commentRepository = commentRepository;
    }

    public FeedEntity createFeed(FeedCreationCommand command) {
        FeedEntity feed = new FeedEntity(command.getAuthor(), command.getTitle(), command.getText(), command.getImageUrl(), command.getVideoUrl());

        return feedRepository.save(feed);
    }

    public boolean deleteFeed(Long feedID) {
        try {
            FeedEntity feed = feedRepository.getOne(feedID);

            for (CommentEntity comment : feed.getComments()) {
                commentRepository.deleteById(comment.getId());
            }
            feedRepository.deleteById(feedID);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);

            return false;
        }
        return true;
    }
}
