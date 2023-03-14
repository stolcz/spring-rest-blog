package com.stolczmiklos.blog.service;

import com.stolczmiklos.blog.domain.AccountEntity;
import com.stolczmiklos.blog.domain.CommentEntity;
import com.stolczmiklos.blog.domain.FeedEntity;
import com.stolczmiklos.blog.domain.RatingEntity;
import com.stolczmiklos.blog.dto.RatingCreationCommand;
import com.stolczmiklos.blog.dto.RatingListItem;
import com.stolczmiklos.blog.repository.AccountRepository;
import com.stolczmiklos.blog.repository.CommentRepository;
import com.stolczmiklos.blog.repository.FeedRepository;
import com.stolczmiklos.blog.repository.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RatingService {

    private RatingRepository ratingRepository;
    private AccountRepository accountRepository;
    private CommentRepository commentRepository;
    private FeedRepository feedRepository;

    public RatingService(RatingRepository ratingRepository, AccountRepository accountRepository, CommentRepository commentRepository, FeedRepository feedRepository) {
        this.ratingRepository = ratingRepository;
        this.accountRepository = accountRepository;
        this.commentRepository = commentRepository;
        this.feedRepository = feedRepository;
    }

    public RatingEntity createRating(RatingCreationCommand ratingCreationCommand, String accountName) {
        AccountEntity account = accountRepository.findByUserName(accountName);
        CommentEntity comment = commentRepository.getById(ratingCreationCommand.getCommentID());
        FeedEntity feed = feedRepository.getById(ratingCreationCommand.getFeedID());
        RatingEntity ratingEntity = new RatingEntity(account, comment, feed);

        return ratingRepository.save(ratingEntity);
    }

    public List<RatingListItem> getAllRatings() {
        List<RatingListItem> ratingListItems = new ArrayList<>();
        List<RatingEntity> ratingEntities = ratingRepository.findAll();
        for (RatingEntity ratingEntity : ratingEntities) {
            ratingListItems.add(new RatingListItem(ratingEntity, ratingEntity.getComment().getId(), ratingEntity.getFeed().getId()));
        }
        return ratingListItems;
    }

    public List<RatingListItem> listRatingsByFeed(Long id, Long accountId) {
        List<RatingListItem> ratingListItems = new ArrayList<>();
        FeedEntity feed = feedRepository.getById(id);
        List<RatingEntity> ratings = ratingRepository.findAllByFeedOrderByCreatedAtDesc(feed);
        for (RatingEntity rating : ratings) {
            if (rating.getAccount().getId() == accountId) {
                ratingListItems.add(new RatingListItem(rating, feed.getId(), null));
            }
        }

        return ratingListItems;
    }

    public List<RatingListItem> listRatingsByComment(Long id, Long accountId) {
        List<RatingListItem> ratingListItems = new ArrayList<>();
        CommentEntity comment = commentRepository.getById(id);
        List<RatingEntity> ratings = ratingRepository.findAllByCommentOrderByCreatedAtDesc(comment);
        for (RatingEntity rating : ratings) {
            if (rating.getAccount().getId() == accountId) {
                ratingListItems.add(new RatingListItem(rating, comment.getId(), null));
            }
        }

        return ratingListItems;
    }

}
