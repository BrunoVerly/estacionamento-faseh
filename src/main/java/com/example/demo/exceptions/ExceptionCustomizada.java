package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionCustomizada {

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionsResponse> badRequest(
            BadRequestException e, WebRequest webRequest) {
        return retornoException(HttpStatus.BAD_REQUEST, "Bad Request",
                e.getMessage(), webRequest);
    }

    @ExceptionHandler(InternalErrorException.class)
    public final ResponseEntity<ExceptionsResponse> internalSeverErrorr(
            InternalErrorException e, WebRequest webRequest) {
        return retornoException(HttpStatus.BAD_REQUEST, "Internal Error",
                e.getMessage(), webRequest);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ExceptionsResponse> httpMessageNotReadable(
            HttpMessageNotReadableException e, WebRequest webRequest) {
        String message = "Requisição inválida: " + e.getMostSpecificCause().getMessage();
        return retornoException(HttpStatus.BAD_REQUEST, "Bad Request",
                message, webRequest);
    }

    private ResponseEntity<ExceptionsResponse> retornoException(
            HttpStatus status, String error, String message, WebRequest webRequest) {
        ExceptionsResponse retorno = new ExceptionsResponse(
                Instant.now(),
                status.value(),
                error,
                message,
                webRequest.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(retorno, status);
    }
}