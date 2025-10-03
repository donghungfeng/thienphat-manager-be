package com.example.zalo_manager.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

public class MapperUtil {
    private static final ModelMapper modelMapper;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    public static  <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }

    public static  <D, T> List<D> mapEntityListIntoDtoPage(List<T> entities, Class<D> dtoClass) {
        return entities.stream().map(objectEntity -> modelMapper.map(objectEntity, dtoClass)).collect(Collectors.toList());
    }

    public static <D, T> D map(final T source, Class<D> destination) {
        return modelMapper.map(source, destination);
    }

    public static <D, T> D mapValue(final T source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    // ✅ Parse JSON string thành object (readValue)
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON parse error: " + e.getMessage(), e);
        }
    }

    // ✅ Parse JSON string -> JsonNode
    public static JsonNode toJsonNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException("JSON parse to JsonNode error: " + e.getMessage(), e);
        }
    }
}
