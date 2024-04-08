package com.hwt.tthappy.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 反序列化
 *
 * @author hu
 * @since 2023-9-07
 */
public class JacksonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        /*objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);*/
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JacksonUtil() {
    }

    public static String toJsonString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new UserFriendlyException("序列化Json失败：" + e.getMessage());
        }
    }

    public static <T> T parseObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new UserFriendlyException("反序列化Json失败：" + e.getMessage());
        }
    }

    public static <T> T parseObject(String jsonStr, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonStr, typeReference);
        } catch (Exception e) {
            throw new UserFriendlyException("反序列化Json失败：" + e.getMessage());
        }
    }

    public static <T> T parseArray(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new UserFriendlyException("反序列化Array失败：" + e.getMessage());
        }
    }
}
