package com.example.backend.exceptions;

public class MongoAddCommentException extends Exception {

    public MongoAddCommentException() {
    }

    public MongoAddCommentException(String errorMessage) {
        super(errorMessage);
    }
    
}
