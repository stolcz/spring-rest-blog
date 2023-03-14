package com.stolczmiklos.blog.validator;

import com.stolczmiklos.blog.dto.CommentCreationCommand;
import com.stolczmiklos.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {

    private CommentService commentService;

    @Autowired
    public CommentValidator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CommentCreationCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CommentCreationCommand commentCreationCommand = (CommentCreationCommand) target;
        if (commentCreationCommand.getText().trim().length() < 1) {
            errors.rejectValue("text", "comment.text.notGiven");
        }
    }
}