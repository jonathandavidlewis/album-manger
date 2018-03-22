package com.jonathandavidlewis.albummanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static String promptUserInput(String promptText) {
        System.out.println(promptText);
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

    public static String convertListToCsv(List list) {
        StringBuilder outputText = new StringBuilder();

        for(Object entry : list) {
            String albumEntryAsString = entry.toString() + "\n";
            outputText.append(albumEntryAsString);
        }
        return outputText.toString();
    }

    public static void saveStringToFile(String str, String filePath) {
        Path path = Paths.get(filePath);
        byte[] strToBytes = str.getBytes();

        try {
            Files.write(path, strToBytes);
            System.out.println("Your file has been saved to: " + path.toAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Error writing file.");
        }
    }

}
