package com.example.receiver.mappers;

import com.google.gson.Gson;

public interface BaseJsonMapper {

    static <T> T fromJson(String json, Class<T> classType) {
        final Gson gson = new Gson();
        return classType.cast(gson.fromJson(json, classType));
    }
}
