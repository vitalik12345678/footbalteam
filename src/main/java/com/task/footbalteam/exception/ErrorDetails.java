package com.task.footbalteam.exception;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@With
@Builder
public class ErrorDetails {

    private int status;
    private String message;

}
