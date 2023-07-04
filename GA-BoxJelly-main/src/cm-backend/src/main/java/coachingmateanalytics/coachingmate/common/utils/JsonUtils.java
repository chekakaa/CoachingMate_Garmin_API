package coachingmateanalytics.coachingmate.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert an object to a JSON string.
     *
     * @param obj The object to be converted
     * @param <T> The object type
     * @return JSON string representation of the object
     */
    public static <T> String obj2string(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convert a JSON string to an object.
     *
     * @param str   The JSON string to be converted
     * @param clazz The class of the object to be converted
     * @param <T>   The object type
     * @return The object created from the JSON string
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
