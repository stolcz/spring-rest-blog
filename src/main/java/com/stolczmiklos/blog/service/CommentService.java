package com.stolczmiklos.blog.service;

import com.stolczmiklos.blog.domain.CommentEntity;
import com.stolczmiklos.blog.domain.FeedEntity;
import com.stolczmiklos.blog.dto.CommentCreationCommand;
import com.stolczmiklos.blog.repository.CommentRepository;
import com.stolczmiklos.blog.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    private FeedRepository feedRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(FeedRepository feedRepository, CommentRepository commentRepository) {
        this.feedRepository = feedRepository;
        this.commentRepository = commentRepository;
    }

    public CommentEntity createComment(CommentCreationCommand command) {
        FeedEntity feed = feedRepository.getOne(command.getFeedID());
        CommentEntity comment = new CommentEntity(command.getAuthor(), command.getText(), feed);

        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long commentID) {
        try {
            commentRepository.deleteById(commentID);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);

            return false;
        }
        return true;
    }
}
