package com.stolczmiklos.blog.repository;

import com.stolczmiklos.blog.domain.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<FeedEntity, Long> {

    List<FeedEntity> findAllByOrderByCreatedAtDesc();

}
