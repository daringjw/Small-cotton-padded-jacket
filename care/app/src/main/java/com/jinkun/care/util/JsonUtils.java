package com.jinkun.care.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 对Gson的封装
 *
 * @author zhou <p/> 2014-3-10
 */
public class JsonUtils {
    private static Gson gson;

    private JsonUtils() {
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .registerTypeAdapter(String.class, new StringConverter())
                    .create();
        }
        return gson;
    }

    /**
     * 解析json
     */
    public static <T> T json2Object(String jsonData, Class<T> cls) {
        if (TextUtils.isEmpty(jsonData)) {
            return null;
        }
        T t = null;
        try {
            t = getGson().fromJson(jsonData, cls);
        } catch (JsonSyntaxException e) {
            return null;
        } catch (JsonParseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return t;
    }

    /**
     * 解析object
     */
    public static <T> T object2Object(Object object, Class<T> cls) {
        String jsonData = object2Json(object);
        if (TextUtils.isEmpty(jsonData)) {
            return null;
        }
        T t = null;
        try {
            t = getGson().fromJson(jsonData, cls);
        } catch (JsonSyntaxException e) {
            return null;
        } catch (JsonParseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return t;
    }

    /**
     * 解析jsonArray
     */
    public static <T> List<T> json2ObjectArrayList(String jsonArray, Class<T> clazz) {
        if (TextUtils.isEmpty(jsonArray)) {
            return null;
        }
        List<T> lists = new ArrayList<T>();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(jsonArray).getAsJsonArray();
        for (JsonElement obj : array) {
            T t = getGson().fromJson(obj, clazz);
            lists.add(t);
        }
        return lists;
    }

    public static JsonObject object2JsonObject(Object object) {
        if (object == null) {
            return null;
        }
        return getGson().toJsonTree(object).getAsJsonObject();
    }

    /**
     * object 转换成json
     */
    public static String object2Json(Object object) {
        return getGson().toJson(object);
    }

    /**
     * list 转换成json
     */
    public static <T> String list2Json(List<T> lists) {
        return getGson().toJson(lists);
    }

    /**
     * bean 转换成json
     */
    public static String bean2Json(Object object) {
        if (object == null) {
            return null;
        }
        return getGson().toJson(object);

    }

    /**
     * 实现了 序列化 接口    对为null的字段进行转换
     */
    public static class StringConverter implements JsonSerializer<String>, JsonDeserializer<String> {
        //字符串为null 转换成"",否则为字符串类型
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json.getAsJsonPrimitive().getAsString();
        }

        @Override
        public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
            return src == null || src.equals("null") ? new JsonPrimitive("") : new JsonPrimitive(src.toString());
        }
    }
}
