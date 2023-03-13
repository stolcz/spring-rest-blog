package com.stolczmiklos.blog.controller;

import com.stolczmiklos.blog.dto.AccountModificationRequest;
import com.stolczmiklos.blog.dto.SaveAccountRequest;
import com.stolczmiklos.blog.dto.UserInfoRequest;
import com.stolczmiklos.blog.security.AuthenticatedUserInfo;
import com.stolczmiklos.blog.service.AccountService;
import com.stolczmiklos.blog.service.ConfigService;
import com.stolczmiklos.blog.validator.AccountRegistrationValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;
    private AccountRegistrationValidator accountRegistrationValidator;
    private ConfigService configService;

    public AccountController(AccountService accountService, AccountRegistrationValidator accountRegistrationValidator, ConfigService configService) {
        this.accountService = accountService;
        this.accountRegistrationValidator = accountRegistrationValidator;
        this.configService = configService;
    }

    @InitBinder("saveAccountRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(accountRegistrationValidator);
    }

    @PostMapping("/newAccount")
    public ResponseEntity<?> saveAccount(@RequestBody @Valid SaveAccountRequest saveAccountRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        accountService.saveAccount(saveAccountRequest, configService.getConfig().isRegistrationConfirmation());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/login")
    public ResponseEntity<AuthenticatedUserInfo> login(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();

        if (!accountService.isEmailConfirmed(authentication.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AuthenticatedUserInfo authenticatedUserInfo = new AuthenticatedUserInfo((user));
        UserInfoRequest userInfoRequest = accountService.getUserInfo(authentication.getName());
        authenticatedUserInfo.setUserName(userInfoRequest.getUserName());

        return new ResponseEntity<>(authenticatedUserInfo, HttpStatus.OK);
    }

    @GetMapping("/myAccount")
    public ResponseEntity<UserInfoRequest> getUserInfo(Authentication authentication) {
        String userName = authentication.getName();
        UserInfoRequest userInfoRequest = accountService.getUserInfo(userName);

        return new ResponseEntity<>(userInfoRequest, HttpStatus.OK);
    }

    @PutMapping("/myAccount")
    public ResponseEntity<?> modifyUserInfo(@RequestBody @Valid AccountModificationRequest accountModificationRequest, Authentication authentication) {
        String userName = authentication.getName();
        accountService.modifyUser(userName, accountModificationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/confirmRegistration")
    public ResponseEntity<?> executeRegistration(@RequestParam("token") String validationCode) {
        accountService.validateRegistration(validationCode);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
