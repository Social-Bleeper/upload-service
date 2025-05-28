package com.bleeper.upload_service.messaging;

import com.bleeper.upload_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class UploadProducer {
    private final RabbitTemplate rabbitTemplate;

    public UploadProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    public void sendMessage(UploadMessage uploadMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, uploadMessage);
        System.out.println("Sent message: " + uploadMessage.getUploadId());
    }
}
