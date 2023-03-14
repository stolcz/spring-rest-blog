package com.stolczmiklos.blog.controller;

import com.stolczmiklos.blog.dto.CommentCreationCommand;
import com.stolczmiklos.blog.service.CommentService;
import com.stolczmiklos.blog.validator.CommentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;
    private CommentValidator commentValidator;

    @Autowired
    public CommentController(CommentService commentService, CommentValidator commentValidator) {
        this.commentService = commentService;
        this.commentValidator = commentValidator;
    }

    @InitBinder("commentCreationCommand")
    protected void initReservationBinder(WebDataBinder binder) {
        binder.addValidators(commentValidator);
    }


    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentCreationCommand command) {
        commentService.createComment(command);
        LOGGER.info("New comment is created");

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestBody @Valid Long commentID) {
        boolean success = commentService.deleteComment(commentID);
        if (success) {
            LOGGER.info("The comment is deleted. ID: " + commentID);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
