package com.whitecloak.training.common.response;

public class MessageResource {

    private String message;

    public MessageResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
