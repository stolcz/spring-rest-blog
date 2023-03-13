package com.stolczmiklos.blog.service;

import com.stolczmiklos.blog.domain.AccountEntity;
import com.stolczmiklos.blog.domain.ActionType;
import com.stolczmiklos.blog.domain.TokenEntity;
import com.stolczmiklos.blog.dto.AccountModificationRequest;
import com.stolczmiklos.blog.dto.SaveAccountRequest;
import com.stolczmiklos.blog.dto.UserInfoRequest;
import com.stolczmiklos.blog.emailhandler.EmailSender;
import com.stolczmiklos.blog.repository.AccountRepository;
import com.stolczmiklos.blog.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public AccountEntity saveAccount(SaveAccountRequest saveAccountRequest, boolean confirmationNeeded) {
        if (accountRepository.findByUserName(saveAccountRequest.getUserName()) != null ||
                    accountRepository.findByEmail(saveAccountRequest.getEmail()) != null) {
            throw new RuntimeException("Felhasználó név/email már szerepel az adatbázisban!");
        }

        AccountEntity account = new AccountEntity(saveAccountRequest);

        account.setPassword(passwordEncoder.encode(saveAccountRequest.getPassword()));
        account.setTimeStamp(LocalDateTime.now());
        account.setConfirmed(false);
        TokenEntity token = this.tokenRepository.save(new TokenEntity(account));
        account.setToken(token);

//        account.setConfirmed(true);
        account.setConfirmed(!confirmationNeeded);
        accountRepository.save(account);

        try {
            this.sendVerificationEmail(account);
        } catch (Exception e) {
            LOGGER.error("Verification e-mail couldn't send!");

            return null;
        }
        LOGGER.info("New account created: " + account.getUserName());

        return account;
    }

    public UserInfoRequest getUserInfo(String userName) {
        AccountEntity accountEntity = accountRepository.findByUserName(userName);
        return new UserInfoRequest(accountEntity);
    }

    public AccountEntity findByUserName(String userName) {
        AccountEntity accountEntity = accountRepository.findByUserName(userName);
        return accountEntity;
    }

    public AccountEntity findByEmail(String email) {
        AccountEntity accountEntity = accountRepository.findByEmail(email);
        return accountEntity;
    }

    public AccountEntity validateRegistration(String validationCode) {

        TokenEntity token = tokenRepository.findByCode(validationCode);
        LocalDateTime expirationDate = token.getExpirationDate();

        if (expirationDate.isBefore(LocalDateTime.now())) {
            return null;
        }

        AccountEntity accountEntity = accountRepository.findByToken(token);
        accountEntity.setConfirmed(true);

        return this.accountRepository.save(accountEntity);
    }

    public boolean isEmailConfirmed(String name) {

        AccountEntity accountEntity = accountRepository.findByUserName(name);

        if (accountEntity.getConfirmed() != null) {
            return accountEntity.getConfirmed();
        } else {
            return false;
        }
    }

    private void sendVerificationEmail(AccountEntity accountEntity) {

        String recipientEmail = accountEntity.getEmail();
        String recipientUserName = accountEntity.getUserName();
        String verificationCode = accountEntity.getToken().getCode();

        EmailSender emailSender = new EmailSender(recipientEmail, recipientUserName, verificationCode, ActionType.REGISTRATION);

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(emailSender);
        exec.shutdown();

    }

    public void modifyUser(String userName, AccountModificationRequest accountModificationRequest) {
        AccountEntity accountEntity = accountRepository.findByUserName(userName);
        accountEntity.setPassword(passwordEncoder.encode(accountModificationRequest.getPassword()));
    }

}
