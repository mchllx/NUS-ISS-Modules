package com.example.backend.exceptions;

public class SQLAddCommentException extends Exception {

    public SQLAddCommentException() {
    }

    public SQLAddCommentException(String errorMessage) {
        super(errorMessage);
    }
    
}

