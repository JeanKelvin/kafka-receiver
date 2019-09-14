package com.example.receiver.commands;

import java.util.Map;

public interface Command<T> {

    void execute(T object, Map<String, Object> args);
}
