package com.rochanahuel.Product_Commands.infraestructure.kafka.service;

import com.rochanahuel.Product_Commands.application.dto.kafka.DeleteProductEvent;
import com.rochanahuel.Product_Commands.application.dto.kafka.NewProductEvent;
import com.rochanahuel.Product_Commands.application.dto.kafka.UpdateProductEvent;

import java.util.UUID;

public interface EventService {
    void newProductEvent(String topicName, UUID key, NewProductEvent newProduct);
    void deleteProductEvent(String topicName, UUID key, DeleteProductEvent deleteProduct);
    void updateProductEvent(String topicName, UUID key, UpdateProductEvent<?> updateProductEvent);
}
