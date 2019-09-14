package com.example.receiver.commands;


import java.util.HashMap;
import java.util.Map;

public interface CommandConsumerKafka extends Command<String> {

    default void startExecute(String message, String topic) {
        //TODO Criar LOG
        Map<String, Object> args = new HashMap<>();
        args.put(ArgsCommandEnum.TOPIC.toString(), topic);
        execute(message, args);
    }
}
