package coachingmateanalytics.coachingmate.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for JSON operations.
 * 
 * @author KanadeM
 * @since 6/20/2017 16:37 PM
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
        // Configure to ignore properties that exist in JSON but not in Java object
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String object2Json(Object o) {
        if (o == null) {
            return null;
        }

        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<String> listObject2ListJson(List<T> objects) {
        if (objects == null) {
            return null;
        }

        List<String> lists = new ArrayList<>();
        for (T t : objects) {
            lists.add(JsonUtil.object2Json(t));
        }

        return lists;
    }

    public static <T> List<T> listJson2ListObject(List<String> jsons, Class<T> c) {
        if (jsons == null) {
            return null;
        }

        List<T> ts = new ArrayList<>();
        for (String j : jsons) {
            ts.add(JsonUtil.json2Object(j, c));
        }

        return ts;
    }

    public static <T> T json2Object(String json, Class<T> c) {
        if (!StringUtils.hasLength(json)) {
            return null;
        }

        try {
            return mapper.readValue(json, c);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T json2Object(String json, TypeReference<T> tr) {
        if (!StringUtils.hasLength(json)) {
            return null;
        }

        try {
            return mapper.readValue(json, tr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
