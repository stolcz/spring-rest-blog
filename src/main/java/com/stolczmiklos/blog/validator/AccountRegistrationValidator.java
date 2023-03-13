package com.stolczmiklos.blog.validator;

import com.stolczmiklos.blog.dto.SaveAccountRequest;
import com.stolczmiklos.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountRegistrationValidator implements Validator {

    @Autowired
    AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SaveAccountRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveAccountRequest saveAccountRequest = (SaveAccountRequest) target;
        if (saveAccountRequest.getUserName().trim().length() < 1) {
            errors.rejectValue("userName", "userName.notGiven");

        } else if (accountService.findByUserName(saveAccountRequest.getUserName()) != null) {
            errors.rejectValue("userName", "userName.taken");
        }
        if (saveAccountRequest.getPassword().trim().length() < 1 || saveAccountRequest.getPassword() == null) {
            errors.rejectValue("password", "password.notGiven");
        }
        if (saveAccountRequest.getEmail().trim().length() < 1 || saveAccountRequest.getEmail() == null) {
            errors.rejectValue("email", "email.notGiven");
        } else if (accountService.findByEmail(saveAccountRequest.getEmail()) != null) {
            errors.rejectValue("email", "email.taken");
        }
    }
}
