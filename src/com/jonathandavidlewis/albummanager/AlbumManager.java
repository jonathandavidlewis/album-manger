package com.jonathandavidlewis.albummanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AlbumManager {

    public static void main(String[] args) {
        System.out.println("This will sort the albums in a file.");
        String filePath = promptUserInput("Please enter the full or relative file path of your album list, then press ENTER: ");

        List<Album> list = importAlbums(filePath);

        Collections.sort(list);

        String outputFilePath = promptUserInput("Please enter the full or relative output file path, then press ENTER: ");

        exportAlbums(list, outputFilePath);
    }

    private static String promptUserInput(String promptText) {
        System.out.println(promptText);
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }
    private static List<Album> importAlbums(String filePath) {
        String textLine = null;

        List<Album> list = new ArrayList<Album>();

        try {
            FileReader fileReader = new FileReader(filePath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Create a list of album instances

            int lineNumber = 0;
            while ((textLine = bufferedReader.readLine()) != null) {

                //Split each line by commas and create an album instance.

                List<String> albumInfo = Arrays.asList(textLine.split(","));

                String artistName = albumInfo.get(0);
                String albumName = albumInfo.get(1);
                int releaseDate = Integer.parseInt(albumInfo.get(2));

                Album thisAlbum = new Album(artistName, albumName, releaseDate);

                list.add(thisAlbum);
                lineNumber++;
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file.");
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        }

        return list;
    }

    private static void exportAlbums(List<Album> list, String filePath) {
        StringBuffer outputText = new StringBuffer();

        for(Album album : list) {
            String albumEntryAsString = album.toString() + "\n";
            outputText.append(albumEntryAsString);
        }

        Path path = Paths.get(filePath);
        byte[] strToBytes = outputText.toString().getBytes();

        try {
            Files.write(path, strToBytes);
        } catch (IOException ex) {
            System.out.println("Error writing file.");
        }
        System.out.println("Your albums have been output to: " + path.toAbsolutePath());
    }
}
