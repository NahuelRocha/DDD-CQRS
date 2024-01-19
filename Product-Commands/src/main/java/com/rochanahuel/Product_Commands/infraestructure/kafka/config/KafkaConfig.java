package com.rochanahuel.Product_Commands.infraestructure.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic newProductTopic(){
        return TopicBuilder
                .name("newProduct")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deleteProductTopic(){
        return TopicBuilder
                .name("deleteProduct")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic updateNameOfProductTopic(){
        return TopicBuilder
                .name("updateProduct")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
