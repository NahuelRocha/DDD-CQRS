package com.rochanahuel.Product_Commands.domain.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.*;
import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(InvalidSkuException.class)
    public ResponseEntity<Map<String,String>> invalidSkuExceptionHandler(InvalidSkuException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("SKU_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidProductNameException.class)
    public ResponseEntity<Map<String,String>> invalidProductNameExceptionHandler(InvalidProductNameException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("NAME_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidProductDescriptionException.class)
    public ResponseEntity<Map<String,String>> invalidProductDescriptionExceptionHandler(InvalidProductDescriptionException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("DESCRIPTION_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<Map<String,String>> invalidPriceExceptionHandler(InvalidPriceException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("PRICE_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> productNotFoundExceptionHandler(ProductNotFoundException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("PRODUCT_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<Map<String,String>> InvalidCategoryExceptionHandler(InvalidCategoryException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("CATEGORY_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SkuIsInUseException.class)
    public ResponseEntity<Map<String,String>> skuIsInUseExceptionHandler(SkuIsInUseException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("SKU_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidStockException.class)
    public ResponseEntity<Map<String,String>> invalidStockExceptionHandler(InvalidStockException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("STOCK_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TheNameIsEqualException.class)
    public ResponseEntity<Map<String,String>> theNameIsEqualExceptionHandler(TheNameIsEqualException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("NAME_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyUpdatePropertiesException.class)
    public ResponseEntity<Map<String,String>> EmptyUpdatePropertiesExceptionHandler(EmptyUpdatePropertiesException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("PROPERTIES_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TheDescriptionIsEqualException.class)
    public ResponseEntity<Map<String,String>> TheDescriptionIsEqualExceptionHandler(TheDescriptionIsEqualException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("DESCRIPTION_ERROR", ex.getMessage());

        return new ResponseEntity<>(resp , HttpStatus.CONFLICT);
    }
}
