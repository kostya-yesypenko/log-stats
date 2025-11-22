package com.fileparser.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<File> getJsonFiles(String folderPath) {
        File folder = new File(folderPath);

        return Arrays.stream(folder.listFiles((dir, name) -> name.endsWith(".json")))
                .toList();
    }
}
