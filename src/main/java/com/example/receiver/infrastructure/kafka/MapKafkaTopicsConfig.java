package com.example.receiver.infrastructure.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "kafka")
public class MapKafkaTopicsConfig {

    private Map<String, String> map;

    private Map<String, String> producerMap;
}
