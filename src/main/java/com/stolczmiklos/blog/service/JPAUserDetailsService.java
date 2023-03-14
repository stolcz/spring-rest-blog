package com.stolczmiklos.blog.service;

import com.stolczmiklos.blog.domain.AccountEntity;
import com.stolczmiklos.blog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JPAUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public JPAUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByUserName(accountName);

        if (account == null) {
            throw new UsernameNotFoundException("No account found with name: " + accountName);
        }

        UserDetails principal = User.withUsername(accountName).password(account.getPassword()).build();

        return principal;
    }
}
