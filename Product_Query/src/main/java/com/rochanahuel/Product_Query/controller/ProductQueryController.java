package com.rochanahuel.Product_Query.controller;

import com.rochanahuel.Product_Query.dto.ProductDto;
import com.rochanahuel.Product_Query.service.serviceImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductQueryController {

    private final ProductServiceImpl service;

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable("sku") String skuId){

        var product = service.findProductById(skuId);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<ProductDto>> findAllProducts(){

        var products = service.findAllProducts();

        return ResponseEntity.ok(products);
    }

}

