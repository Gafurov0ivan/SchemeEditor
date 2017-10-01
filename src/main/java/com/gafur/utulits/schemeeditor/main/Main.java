package com.gafur.utulits.schemeeditor.main;

import com.gafur.utulits.schemeeditor.helpers.WorkWithFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.gafur.utulits.schemeeditor.helpers.WorkWithFile.readFile;
import static com.gafur.utulits.schemeeditor.helpers.WorkWithFile.writeFile;

/**
 * Main class to execute
 *
 * @author igafurov
 * @since 29.02.2017
 */
public class Main {
    private static String folderPath;
    private static String replace;

    public static void main(String[] args) throws AWTException, IllegalAccessException, NoSuchFieldException, InterruptedException, IOException {
        System.out.println("Oy, hello nerd, insert password");
        folderPath = WorkWithFile.userInput();
        System.out.println("Please insert replace");
        replace = WorkWithFile.userInput();
        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile)
                    .forEach(item -> replace(item));
        }
    }

    private static void replace(Path path) {
        String textFile = readFile(path.toString());
        String regex = "([ ]{" + replace.length() + "," + replace.length() + "})";
        String result = textFile.replaceFirst(regex, replace);
        String check = writeFile(path.toString(), result);
        System.out.println(check);
    }
}
