package com.task.footbalteam.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class ExistException extends IllegalStateException{
    private static final long serialVersionUID = 1L;
    private static final String EXIST_EXCEPTION = "Entity exist";

    public ExistException(String message){
        super(message.isEmpty() ? EXIST_EXCEPTION : message);
    }

    public ExistException(){
        super(EXIST_EXCEPTION);
    }

}
