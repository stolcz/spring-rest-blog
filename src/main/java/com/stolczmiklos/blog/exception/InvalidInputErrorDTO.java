package com.stolczmiklos.blog.exception;

public class InvalidInputErrorDTO {

    String errorMessage;

    public InvalidInputErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
