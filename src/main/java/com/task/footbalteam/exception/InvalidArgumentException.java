package com.task.footbalteam.exception;

public class InvalidArgumentException extends IllegalArgumentException{

    private static final long serialVersionUID = 1L;
    private static final String INVALID_ARGUMENT = "Invalid argument";

    public InvalidArgumentException(String message){
        super( message==null || message.isEmpty() ? INVALID_ARGUMENT : message);
    }

    public InvalidArgumentException(){
        super(INVALID_ARGUMENT);
    }

}
