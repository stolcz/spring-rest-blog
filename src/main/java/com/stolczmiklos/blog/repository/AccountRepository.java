package com.stolczmiklos.blog.repository;

import com.stolczmiklos.blog.domain.AccountEntity;
import com.stolczmiklos.blog.domain.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByUserName(String accountName);

    AccountEntity findByEmail(String email);

    AccountEntity findByToken(TokenEntity token);

}
