package com.example.receiver.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public interface BaseEntityMapper {

    static <T> T toEntity(Object o, Class<T> clazz) {
        if (o == null) {
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(o, clazz);
    }
}
