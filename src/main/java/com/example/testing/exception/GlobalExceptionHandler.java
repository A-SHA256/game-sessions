package com.example.testing.exception;

import com.example.testing.dto.ErrorResDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResDTO> methodArgHandler(MethodArgumentNotValidException ex) {
        ErrorResDTO error = commons(ex, ex.getStatusCode().value());
        ex.getBindingResult().getFieldErrors().forEach(err -> error.getErrors()
                .put(err.getField(), err.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResDTO> constraintViolationHandler(ConstraintViolationException ex) {
        ErrorResDTO error = commons(ex, HttpStatus.BAD_REQUEST.value());
        ex.getConstraintViolations().forEach(v -> error.getErrors()
                .put(v.getPropertyPath().toString(), v.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResDTO> generalExHandler(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commons(ex, HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundEx.class)
    public ResponseEntity<ErrorResDTO> objNotFoundHandler(ObjectNotFoundEx ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commons(ex, HttpStatus.NOT_FOUND.value()));
    }

    public ErrorResDTO commons(Exception ex, Integer status) {
        ErrorResDTO error = new ErrorResDTO();
        error.setStatus(status);
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }
}
