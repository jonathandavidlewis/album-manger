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

        List<Album> albums = importAlbums(filePath);

        Collections.sort(albums);

        String outputFilePath = promptUserInput("Please enter the full or relative output file path, then press ENTER: ");

        String albumsAsString = convertAlbumsToString(albums);

        saveStringToFile(albumsAsString, outputFilePath);
    }

    private static String promptUserInput(String promptText) {
        System.out.println(promptText);
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

    private static List<Album> importAlbums(String filePath) {
        String csvLine = null;

        List<Album> list = new ArrayList<Album>();

        try {
            FileReader fileReader = new FileReader(filePath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((csvLine = bufferedReader.readLine()) != null) {
                list.add(parseAlbum(csvLine));
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file.");
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        }

        return list;
    }

    private static void saveStringToFile(String str, String filePath) {
        Path path = Paths.get(filePath);
        byte[] strToBytes = str.getBytes();

        try {
            Files.write(path, strToBytes);
            System.out.println("Your file has been saved to: " + path.toAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Error writing file.");
        }
    }

    private static String convertAlbumsToString(List<Album> list) {
        StringBuilder outputText = new StringBuilder();

        for(Album album : list) {
            String albumEntryAsString = album.toString() + "\n";
            outputText.append(albumEntryAsString);
        }
        return outputText.toString();
    }

    private static Album parseAlbum(String csvLine) {
        List<String> albumInfo = Arrays.asList(csvLine.split(","));

        String artistName = albumInfo.get(0);
        String albumName = albumInfo.get(1);
        int releaseDate = Integer.parseInt(albumInfo.get(2));

        return new Album(artistName, albumName, releaseDate);
    }
}
