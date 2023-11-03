package com.tra22.spring.redis.exception;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message){
        super(message);
    }
}
