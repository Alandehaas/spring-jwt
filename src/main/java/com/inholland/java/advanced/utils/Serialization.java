package com.inholland.java.advanced.utils;

import java.io.*;
import java.util.List;

public class Serialization<T> {

    public void saveToFile(List<T> objects, String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(objects);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> readFromFile(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (List<T>) ois.readObject();
        }
    }
}