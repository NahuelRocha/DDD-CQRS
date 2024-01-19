package com.rochanahuel.Product_Commands.infraestructure.controller;

import com.rochanahuel.Product_Commands.application.dto.business.CreateProductRequest;
import com.rochanahuel.Product_Commands.application.dto.business.ProductDto;
import com.rochanahuel.Product_Commands.application.dto.business.UpdateProductRequest;
import com.rochanahuel.Product_Commands.application.service.DomainProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductCommandController {

    private final DomainProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest productRequest){

        log.info("NEW REQUEST {}", productRequest.skuId());

        var createProduct = productService.createProduct(productRequest);

        return ResponseEntity.ok(createProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId){

        productService.deleteProduct(productId);

        return ResponseEntity.ok("Product deleted.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long productId, @RequestBody UpdateProductRequest updates){

        productService.updateProduct(productId, updates);

        return ResponseEntity.ok("Product updated.");
    }
}
