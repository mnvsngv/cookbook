package com.mnvsngv.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static <T> T convertJsonToObject(String json, Class<T> valueType) {
        Type type = new TypeToken<T>() {}.getType();
        return gson.fromJson(json, type);
    }
}
