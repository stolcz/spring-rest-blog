package com.stolczmiklos.blog.repository;

import com.stolczmiklos.blog.domain.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    TokenEntity findByCode(String code);

}
