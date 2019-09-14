package com.example.consumer.infrastructure.kafka;

import com.example.consumer.commands.CommandConsumerKafka;
import com.example.consumer.factory.BeanManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicConsumer {

    private final MapKafkaTopicsConfig mapKafkaTopicsConfig;

    @KafkaListener(topics = "#{'${kafka.topics}'.split(',')}")
    public void consume(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Payload String message) {
        startProcess(topic, message);
    }

    private void startProcess(String topic, String message) {
        log.debug("Mensagem recebida do topico " + topic + ": " + message);

        CommandConsumerKafka commandConsumerKafka = carregarConsumerCommand(topic);
        commandConsumerKafka.startExecute(message, topic);
    }

    private CommandConsumerKafka carregarConsumerCommand(String topic) {
        try {
            final Map.Entry<String, String> entryClassName = this.mapKafkaTopicsConfig.getMap()
                    .entrySet()
                    .stream()
                    .filter(m -> m.getKey().equals(topic))
                    .findFirst()
                    .orElse(null);


            if (isNull(entryClassName)) {
                final String errorMessage = String.format("Nao foi encontrado um Map para o topico %s", topic);
                throw new RuntimeException(errorMessage);
            }

            final String className = entryClassName.getValue();
            final Class classToInject = Class.forName(className);

            final CommandConsumerKafka command = (CommandConsumerKafka) BeanManagerFactory.get(classToInject);

            if (isNull(command)) {
                final String errorMessage = String.format("Não foi encontrado um objeto do tipo %s no contexto da aplicação para o tópico %s",
                        className, topic);
                throw new RuntimeException(errorMessage);
            }

            return command;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
