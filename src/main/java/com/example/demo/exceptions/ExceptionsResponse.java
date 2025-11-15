package com.example.demo.exceptions;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonFormat;

public record ExceptionsResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) { }
