package com.stolczmiklos.blog.repository;

import com.stolczmiklos.blog.domain.CommentEntity;
import com.stolczmiklos.blog.domain.FeedEntity;
import com.stolczmiklos.blog.domain.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    List<RatingEntity> findAllByFeedOrderByCreatedAtDesc(FeedEntity feed);

    List<RatingEntity> findAllByCommentOrderByCreatedAtDesc(CommentEntity feed);

}

