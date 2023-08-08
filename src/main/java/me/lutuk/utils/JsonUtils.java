package me.lutuk.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//src/main/resources/mythicIds.json
public class JsonUtils {

    public static JsonObject getFromJsonFile() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("config/mythicAppraiser/mythicIds.json")));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        return jsonObject;
    }

}

