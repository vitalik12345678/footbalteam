package com.task.footbalteam.exception;

public class NotCorrectDataException extends IllegalStateException{
    private static final long serialVersionUID = 1L;
    private static final String INVALID_ARGUMENT = "Incorrect input data";

    public NotCorrectDataException(String message){
        super( message==null || message.isEmpty() ? INVALID_ARGUMENT : message);
    }

    public NotCorrectDataException(){
        super(INVALID_ARGUMENT);
    }
}
