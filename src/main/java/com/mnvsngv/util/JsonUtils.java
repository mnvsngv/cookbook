package com.mnvsngv.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertJsonToObject(String json, Class<T> valueType) throws IOException {
        return mapper.readValue(json, valueType);
    }
}
