package com.example.RestApi.inDetail.ErrorHandlers;

import java.time.LocalDate;

public class ErrorDetails {
    private LocalDate time;
    private String message;
    private String description;

    public ErrorDetails(LocalDate time, String message, String description) {
        this.time = time;
        this.message = message;
        this.description = description;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "time=" + time +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
