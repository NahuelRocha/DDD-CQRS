package com.rochanahuel.Product_Query.kafka.service;

import com.rochanahuel.Product_Query.kafka.dto.DeleteProductEvent;
import com.rochanahuel.Product_Query.kafka.dto.NewProductEvent;
import com.rochanahuel.Product_Query.kafka.dto.UpdateProductEvent;
import com.rochanahuel.Product_Query.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ProductService service;
    @KafkaListener(topics = {"newProduct"}, groupId = "new-product-group", containerFactory = "kafkaListenerNewProduct")
    public void newProductConsumer(NewProductEvent newProduct){

        log.info("We received a new PRODUCT " + newProduct.getName() + " with SKU: " + newProduct.getSkuId());

        service.saveProduct(newProduct);
    }

    @KafkaListener(topics = {"deleteProduct"}, groupId = "delete-product-group", containerFactory = "kafkaListenerDeleteProduct")
    public void deleteProductConsumer(DeleteProductEvent deleteProduct){

        log.info("We received a new DELETE event for product with SKU: " + deleteProduct.getSkuId());

        service.deleteProduct(deleteProduct);
    }

    @KafkaListener(topics = {"updateProduct"}, groupId = "update-product-group", containerFactory = "kafkaListenerUpdateProduct")
    public void updateProductConsumer(UpdateProductEvent updateProduct){

        log.info("We received a new UPDATE event for product with SKU: " + updateProduct.getSku());

        service.updateProduct(updateProduct);
    }
}
