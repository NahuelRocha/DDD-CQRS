package com.rochanahuel.Product_Query.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Map<String,String>> InvalidFormatExceptionHandler(InvalidFormatException ex){
        Map<String,String> resp = new HashMap<>();

        String fieldName = ex.getPath().get(0).getFieldName();
        String propsName = ex.getValue().toString();
        String errorMessage = "Invalid key in JSON request, for field: " + propsName;

        resp.put(fieldName, errorMessage);

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }



    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> productNotFoundExceptionHandler(ProductNotFoundException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.NOT_FOUND);
    }



}
