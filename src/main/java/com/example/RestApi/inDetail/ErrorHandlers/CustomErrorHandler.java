package com.example.RestApi.inDetail.ErrorHandlers;

import com.example.RestApi.inDetail.Exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomErrorHandler extends
        ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails>
        handleAllException(Exception ex, WebRequest req){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(), ex.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity(errorDetails,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails>
        handleUserNotFoundException(Exception ex,WebRequest req){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(), ex.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity(errorDetails,
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object>
        handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                     HttpHeaders headers,
                                     HttpStatusCode status,
                                     WebRequest request) {
        String msg = ex.getFieldError().getDefaultMessage();
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(),msg ,
                request.getDescription(false)
        );
        return new ResponseEntity(errorDetails,status);
    }
}
