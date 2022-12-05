package me.bitsoul.noa.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.bitsoul.noa.exception.JsonException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujinhua
 */
@Component
public class JsonUtil {
    private static ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    /**
     * 将java对象转换成json字符串
     *
     * @param obj 准备转换的对象
     * @return json字符串
     */
    public static String beanToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(obj.getClass() + e.getMessage());
        }
    }

    public static <T> T jsonToBean(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(cls + e.getMessage());
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(ArrayList.class, cls));
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(cls + e.getMessage());
        }
    }

}
