package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload;

public class ApiResponse {
    private String message;
    private String ResponseType;

    public ApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseType() {
        return ResponseType;
    }

    public void setResponseType(String responseType) {
        ResponseType = responseType;
    }

    public ApiResponse(String message, String responseType) {
        this.message = message;
        ResponseType = responseType;
    }
}
