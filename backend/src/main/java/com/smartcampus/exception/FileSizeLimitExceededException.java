package com.smartcampus.exception;

public class FileSizeLimitExceededException extends RuntimeException {
    public FileSizeLimitExceededException(String message) {
        super(message);
    }
}
