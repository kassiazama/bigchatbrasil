package com.kassiazama.bigchatbrasil.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<StandardErrorResponse> notAcceptable(MethodNotAllowedException exception, HttpServletRequest request) {
        StandardErrorResponse error = new StandardErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        error.setError("Method Not Allowed");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }
}


    