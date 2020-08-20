/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 12:34:45
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpStatus status, WebRequest request)
    {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        GenericError error = new GenericError(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), errorMessage);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handlerException(Exception ex, WebRequest request)
    {
        final String errorMessage = ex.getLocalizedMessage();
        return new ResponseEntity<>(new GenericError(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), errorMessage), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
