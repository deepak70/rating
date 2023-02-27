package com.ates.rating.app.exception;

import com.ates.rating.app.ExceptionResponse;
import com.ates.rating.app.GenericResponse;
import com.ates.rating.app.exception.handler.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @Author Deepak Walve
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public GenericResponse<Boolean> exceptionResponse(AppException exception) {
        return GenericResponse.<Boolean>builder()
                .data(false)
                .message(exception.getMessage())
                .success(false)
                .build();
    }
}
