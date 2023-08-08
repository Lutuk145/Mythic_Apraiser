package me.lutuk.utils;

import me.lutuk.ExampleMod;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static void copyDataFiles() {
        copyFilesFromResources("mythicIds.json");
    }

    private static void copyFilesFromResources(String fileName) {
        InputStream inputStream = ExampleMod.class.getResourceAsStream("/mythicdata/" + fileName);
        createDirectory(Config.CONFIG_DIRS + fileName);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(Config.CONFIG_DIRS + fileName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath).getParent();
        if (path != null) {
            File file = new File(path.toString());
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
}
