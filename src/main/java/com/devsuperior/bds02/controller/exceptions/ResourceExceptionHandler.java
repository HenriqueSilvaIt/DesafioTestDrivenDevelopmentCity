package com.devsuperior.bds02.controller.exceptions;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setMessage(e.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> entityNotFound(DatabaseException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setMessage(e.getMessage());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
