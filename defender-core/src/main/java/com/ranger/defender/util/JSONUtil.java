package com.ranger.defender.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author ranger
 * @Date 2020/6/7 16:40
 **/
public class JSONUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static String toString(Object object){
        if(null == object){
            return "";
        }
        try{
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            return null;
        }
    }

    public static <T> T jsonToObject(String jsonString,Class<T> type){
        try{
            return objectMapper.readValue(jsonString,type);
        }catch (Exception e){
            return null;
        }
    }
}
