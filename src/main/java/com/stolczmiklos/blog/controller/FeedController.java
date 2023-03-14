package com.stolczmiklos.blog.controller;

import com.stolczmiklos.blog.dto.FeedCreationCommand;
import com.stolczmiklos.blog.service.FeedService;
import com.stolczmiklos.blog.validator.FeedValidator;
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
public class FeedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);

    private FeedService feedService;
    private FeedValidator feedValidator;

    @Autowired
    public FeedController(FeedService feedService, FeedValidator feedValidator) {
        this.feedService = feedService;
        this.feedValidator = feedValidator;
    }

    @InitBinder("feedCreationCommand")
    protected void initReservationBinder(WebDataBinder binder) {
        binder.addValidators(feedValidator);
    }


    @PostMapping("/feed")
    public ResponseEntity<?> createFeed(@RequestBody @Valid FeedCreationCommand command) {
        feedService.createFeed(command);
        LOGGER.info("New feed is created");

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/feed")
    public ResponseEntity<?> deleteFeed(@RequestBody @Valid Long feedID) {
        boolean success = feedService.deleteFeed(feedID);
        if (success) {
            LOGGER.info("The feed and its comments are deleted. feedID: " + feedID);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
