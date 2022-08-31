package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.mashape.unirest.http.ObjectMapper;

import java.io.Serializable;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class JSonUtil {
    public static String asJsonString(Object object){
        try{
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return objectMapper.writeValueAsString(object);
                    }catch (Exception e){
            throw new RuntimeException();
        }

    }
    public static <T> T objectFromString(Class<T>)aClass, String value) throws JsonPropertyDescription{
    return getObjectMapper().readValue(value, aclass);
    }

    public static ObjectMapper getObjectMapper(){
    return new ObjectMapper().registerModule(new ParameterNamesModule())
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure
        @Override
        public <T> T readValue(String s, Class<T> aClass) {
            return null;
        }

        @Override
        public String writeValue(Object o) {
            return null;
        }
    }
    }


}
