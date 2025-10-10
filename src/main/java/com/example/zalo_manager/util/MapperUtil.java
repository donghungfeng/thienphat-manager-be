package com.example.zalo_manager.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

public class MapperUtil {
    private static final ModelMapper modelMapper;
    public static final ObjectMapper objectMapper = new ObjectMapper(); // ✅ public để có thể dùng ngoài

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    // -------------------- MODEL MAPPER UTILS --------------------

    public static <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }

    public static <D, T> List<D> mapEntityListIntoDtoPage(List<T> entities, Class<D> dtoClass) {
        return entities.stream().map(objectEntity -> modelMapper.map(objectEntity, dtoClass)).collect(Collectors.toList());
    }

    public static <D, T> D map(final T source, Class<D> destination) {
        return modelMapper.map(source, destination);
    }

    public static <D, T> D mapValue(final T source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    // -------------------- JSON UTILS --------------------

    // ✅ Parse JSON string -> Object
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

    // ✅ Convert Object -> JsonNode
    public static JsonNode toJsonNode(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    // ✅ Convert Object -> JSON String
    public static String toJson(Object source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (Exception e) {
            throw new RuntimeException("JSON serialization error: " + e.getMessage(), e);
        }
    }

    // ✅ Tạo ObjectNode trống (để build JSON thủ công)
    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    // ✅ Tạo ArrayNode trống
    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

    // ✅ Clone JsonNode (deep copy)
    public static JsonNode clone(JsonNode node) {
        return node == null ? null : node.deepCopy();
    }

    // ✅ Merge 2 ObjectNode (ghi đè field nếu trùng)
    public static ObjectNode merge(ObjectNode base, ObjectNode update) {
        ObjectNode merged = base.deepCopy();
        update.fieldNames().forEachRemaining(field -> merged.set(field, update.get(field)));
        return merged;
    }
}
