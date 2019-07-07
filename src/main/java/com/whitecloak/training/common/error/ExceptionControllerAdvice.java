package com.whitecloak.training.common.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ErrorResource handleApiException(ApiException e, HttpServletResponse response) {
        response.setStatus(e.getHttpStatus().value());
        return new ErrorResource(e.getMessage());
    }
}
