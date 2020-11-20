package com;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Processor {

    public static List<String> getMatches (String path, String pattern) {
        try {

            List<String> list = new ArrayList<>();
            Files.walk(Path.of(path))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .forEach(x -> {
                        try (FileReader fr = new FileReader(x)) {
                            StringBuilder buffer = new StringBuilder();
                            int index = fr.read();
                            while (index != -1) {
                                char ch = (char) index;
                                buffer.append(ch);
                                index = fr.read();
                            }
                            if (buffer.toString().contains(pattern)) {
                                list.add(x.getPath());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.singletonList("Error. Try again.");
        }
    }

}