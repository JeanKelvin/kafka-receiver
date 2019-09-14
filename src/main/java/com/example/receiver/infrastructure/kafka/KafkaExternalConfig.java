package com.example.receiver.infrastructure.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class KafkaExternalConfig {

    @Value("${kafka.bootstrap_servers}")
    private String bootstrapServers;

    @Value("${kafka.group_id}")
    private String groupId;
}
