package com.example.consumer.commands;

import java.util.Map;

public interface Command<T> {

    void execute(T object, Map<String, Object> args);
}
