package ru.menkov.informsources.services;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Service {
    protected JsonObject jsonObject = new JsonObject();

    protected String getJsonString(String message, Integer status) {
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        String jsonToClient = jsonObject.toString();

        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }

    protected String getJsonString(String message, Integer status, String name) {
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        jsonObject.addProperty("name",name);
        String jsonToClient = jsonObject.toString();

        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }
}
