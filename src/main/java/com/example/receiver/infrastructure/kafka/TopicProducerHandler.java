package com.example.receiver.infrastructure.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicProducerHandler implements ProducerHandler<String> {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TopicProducerHandler(@Qualifier("kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }
}