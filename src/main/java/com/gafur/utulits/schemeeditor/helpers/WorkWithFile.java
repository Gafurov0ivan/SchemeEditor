package com.gafur.utulits.schemeeditor.helpers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Read and write in file operations
 *
 * @author igafurov
 * @since 29.02.2017
 */
public class WorkWithFile {

    /**
     * When application start, user should input arguments, like standName and moduleName
     *
     * @return userInput string
     */
    public static String userInput() {
        String userInput = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String userInputWithoutUtf = reader.readLine();
            byte[] ruBytes = userInputWithoutUtf.getBytes("windows-1251");
            userInput = new String(ruBytes, "windows-1251");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    /**
     * Read string from file
     *
     * @param path path
     * @return string from file
     */
    public static String readFile(String path) {
        Charset encoding = Charset.forName("windows-1251");
        String content = "";
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            content = new String(encoded, encoding);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return content;
    }

    public static String writeFile(String path, String content) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "windows-1251"));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
