package com.rochanahuel.Product_Commands.infraestructure.kafka.service;

import com.rochanahuel.Product_Commands.application.dto.kafka.DeleteProductEvent;
import com.rochanahuel.Product_Commands.application.dto.kafka.NewProductEvent;
import com.rochanahuel.Product_Commands.application.dto.kafka.UpdateProductEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaProducerService implements EventService{

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired KafkaTemplate<UUID, NewProductEvent> newProductkafkaTemplate;
    @Autowired KafkaTemplate<UUID, DeleteProductEvent> deleteProductkafkaTemplate;
    @Autowired KafkaTemplate<UUID, UpdateProductEvent<?>> updateProductkafkaTemplate;


    @Override
    public void newProductEvent(String topicName, UUID key, NewProductEvent newProduct){
        var future = newProductkafkaTemplate.send(topicName, key, newProduct);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                LOGGER.info(exception.getMessage());
                future.completeExceptionally(exception);

            } else future.complete(sendResult);

            LOGGER.info("New product created: " + newProduct.getName() + " created with sku " + newProduct.getSkuId());
        });
    }

    @Override
    public void deleteProductEvent(String topicName, UUID key, DeleteProductEvent deleteProduct) {

        var future = deleteProductkafkaTemplate.send(topicName, key, deleteProduct);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                LOGGER.info(exception.getMessage());
                future.completeExceptionally(exception);

            } else future.complete(sendResult);

            LOGGER.info("Delete product with sku: " + deleteProduct.getSkuId());
        });
    }

    @Override
    public void updateProductEvent(String topicName, UUID key, UpdateProductEvent<?> updateProduct) {
        var future = updateProductkafkaTemplate.send(topicName, key, updateProduct);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                LOGGER.info(exception.getMessage());
                future.completeExceptionally(exception);

            } else future.complete(sendResult);

            LOGGER.info("New updated product transaction: " + key + " for product with SKU:" + updateProduct.getSku()
                + " change properties: " + updateProduct.getUpdateValue());
        });
    }
}
