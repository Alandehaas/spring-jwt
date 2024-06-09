package com.inholland.java.advanced.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Json<T> {

    private final Class<T> clazz;
    private final Gson gson = new Gson();

    public Json(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> read(String path) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            return gson.fromJson(reader, TypeToken.getParameterized(List.class, clazz).getType());
        }
    }

    public void write(String path, List<T> objects) throws IOException {
        try (var writer = Files.newBufferedWriter(Paths.get(path))) {
            gson.toJson(objects, writer);
        }
    }
}
