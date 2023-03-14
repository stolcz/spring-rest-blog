package com.stolczmiklos.blog.controller;

import com.stolczmiklos.blog.dto.RatingCreationCommand;
import com.stolczmiklos.blog.dto.RatingListItem;
import com.stolczmiklos.blog.service.AccountService;
import com.stolczmiklos.blog.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    private RatingService ratingService;
    private AccountService accountService;

    @Autowired
    public RatingController(RatingService ratingService, AccountService accountService) {
        this.ratingService = ratingService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody @Valid RatingCreationCommand ratingCreationCommand) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accountName = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN");
        ratingService.createRating(ratingCreationCommand, accountName);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RatingListItem>> getRatingsListByFeed(@PathVariable Long id, Authentication authentication) {
        Long accountId = -1L;
        if (authentication != null) {
            accountId = accountService.findByUserName(authentication.getName()).getId();
        }
        return new ResponseEntity<>(ratingService.listRatingsByFeed(id, accountId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RatingListItem>> getRatingsListByComment(@PathVariable Long id, Authentication authentication) {
        Long accountId = -1L;
        if (authentication != null) {
            accountId = accountService.findByUserName(authentication.getName()).getId();
        }
        return new ResponseEntity<>(ratingService.listRatingsByComment(id, accountId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RatingListItem>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }

}
