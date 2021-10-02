package com.sicred.votacao.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ExceptionMessage {
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    private ExceptionMessage(String path, String message, HttpStatus httpStatus) {
        this(path, message, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    private ExceptionMessage(String path, String message, Integer status, String error) {
        this.path = path;
        this.message = message;
        this.status = status;
        this.error = error;
    }

    public static ExceptionMessage of(String path, String message, HttpStatus httpStatus) {
        return new ExceptionMessage(path, message, httpStatus);
    }

    public static ExceptionMessage of(String path, String message, Integer status, String error) {
        return new ExceptionMessage(path, message, status, error);
    }

    public ResponseEntity<ExceptionMessage> toResponseEntity() {
        return ResponseEntity.status(status).body(this);
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionMessage that = (ExceptionMessage) o;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(status, that.status) && Objects.equals(error, that.error) && Objects.equals(message, that.message) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, status, error, message, path);
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
