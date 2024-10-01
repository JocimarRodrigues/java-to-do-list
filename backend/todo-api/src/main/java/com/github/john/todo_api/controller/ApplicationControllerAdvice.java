package com.github.john.todo_api.controller;

import com.github.john.todo_api.exception.ApiErrors;
import com.github.john.todo_api.exception.CustomGenericException;
import com.github.john.todo_api.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    // Esse aqui funciona para todas as exception do BusinessRule
    @ExceptionHandler(CustomGenericException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRuleException(CustomGenericException ex) {
    String msgError = ex.getMessage();
    return new ApiErrors(msgError);
    }

//    @ExceptionHandler(ResponseStatusException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErrors handleResponseStatusException(ResponseStatusException ex) {
//        String errorMessage = ex.getReason();  // A razão contém a mensagem de erro
//        return new ApiErrors(errorMessage);
//    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException( NotFoundException ex ){
        return new ApiErrors(ex.getMessage());
    }

    // Esse aqui vai funcionar para todos os erros em que o NotValid der true
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }

}
