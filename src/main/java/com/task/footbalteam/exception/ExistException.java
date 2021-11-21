package com.task.footbalteam.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is custom exception for entity which
 *  exists in Database or other data storage.
 * The constructor accepts message for Exception
 * <p>
 * Use @code throw new NotExistException("Entity is exist")
 *
 */
@Slf4j

public class ExistException extends IllegalArgumentException{
    private static final long serialVersionUID = 1L;
    private static final String EXIST_EXCEPTION = "Entity exist";

    public ExistException(String message){
        super(message.isEmpty() ? EXIST_EXCEPTION : message);
    }

    public ExistException(){
        super(EXIST_EXCEPTION);
    }

}
