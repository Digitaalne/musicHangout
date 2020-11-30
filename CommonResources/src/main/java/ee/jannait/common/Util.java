package ee.jannait.common;

import com.google.gson.Gson;

public class Util {
    private static final Gson gson = new Gson();

    public static String getJson(Object obj){
        return gson.toJson(obj);
    }

    public static Object getObject(String json, Class clazz){
        return gson.fromJson(json, clazz);
    }
}
