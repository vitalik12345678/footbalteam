package com.task.footbalteam.exception.handler;

import com.task.footbalteam.exception.BadRequestException;
import com.task.footbalteam.exception.ErrorDetails;
import com.task.footbalteam.exception.ExistException;
import com.task.footbalteam.exception.NotExistException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistException.class)
    public final ResponseEntity<Object> handleExistException(ExistException exception){
        return buildExceptionBody(exception,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotExistException.class)
    public final ResponseEntity<Object> handleNotExistException(NotExistException exception){
        return buildExceptionBody(exception,HttpStatus.NOT_FOUND);
    }

  /*  @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return buildExceptionBody(exception,HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<Object> handleNumberFormatException(NumberFormatException exception){
        return buildExceptionBody(exception,HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> buildExceptionBody(Exception exception, HttpStatus httpStatus) {
        ErrorDetails exceptionResponse = ErrorDetails.builder()
                .status(httpStatus.value())
                .message(exception.getMessage())
                .build();
        log.debug(exception.getMessage());
        return ResponseEntity
                .status(httpStatus)
                .body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder sb = new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            sb.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(" and ");
        });
        sb.setLength(sb.length() - 5);
        return buildExceptionBody(new BadRequestException(sb.toString()), status);
    }
}
