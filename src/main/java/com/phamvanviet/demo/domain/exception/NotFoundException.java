package com.phamvanviet.demo.domain.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String exceptoin) {
        super(exceptoin);
    }
}
