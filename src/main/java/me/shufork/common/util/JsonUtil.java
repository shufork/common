package me.shufork.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Slf4j
public class JsonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(dateFormat);
    }

    private JsonUtil(){}

    public static String toJsonOrNull(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("failed to convert object{} to json.", obj);
            return null;
        }
    }

    public static <T> T parseJson(final String json, final Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

}
