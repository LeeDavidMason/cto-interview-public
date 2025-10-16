package com.citi.cto.ctointerviewpublic;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class SerialisationUtil {

    public static <T> T loadJsonFromResources(String path, Class<T> clazz) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        return new ObjectMapper().readValue(inputStream, clazz);
    }
}
