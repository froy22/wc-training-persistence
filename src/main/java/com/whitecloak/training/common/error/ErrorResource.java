package com.whitecloak.training.common.error;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class ErrorResource {

    private String message;

    public ErrorResource() {}

    public ErrorResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResource{" +
            "message='" + message + '\'' +
            '}';
    }
}
