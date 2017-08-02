package com.example.vdimitrova.mvptest.api.api_models.error_models;

/**
 * Created by vdimitrova on 23.05.17.
 */

public class ErrorStatusModel {
    private String errorMessage;
    private int statusCode;

    public ErrorStatusModel(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public ErrorStatusModel(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
