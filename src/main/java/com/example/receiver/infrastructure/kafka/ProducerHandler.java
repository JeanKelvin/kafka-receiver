package com.example.receiver.infrastructure.kafka;

public interface ProducerHandler<T> {

    void produce(String topicName, String message);
}