package com.stolczmiklos.blog.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FeedValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return com.stolczmiklos.blog.dto.NewFeedCreationCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        com.stolczmiklos.blog.dto.NewFeedCreationCommand newFeedCreationCommand = (com.stolczmiklos.blog.dto.NewFeedCreationCommand) target;
        if (newFeedCreationCommand.getTitle().trim().length() < 1) {
            errors.rejectValue("title", "post.title.notGiven");
        }
        if (newFeedCreationCommand.getText().trim().length() < 1) {
            errors.rejectValue("text", "post.text.notGiven");
        }
    }
}
