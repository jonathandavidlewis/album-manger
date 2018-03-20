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

        // Read from the file

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

            // Sort the list by predefined pattern

            Collections.sort(list);

            StringBuffer outputText = new StringBuffer();

            for(Album album : list) {
                String albumEntryAsString = album.toString() + "\n";
                outputText.append(albumEntryAsString);
            }

            String outputFilePath = promptUserInput("Please enter the full or relative output file path, then press ENTER: ");

            // Save the file

            Path path = Paths.get(outputFilePath);
            byte[] strToBytes = outputText.toString().getBytes();

            Files.write(path, strToBytes);

            System.out.println(outputText);

            System.out.println("Your albums have been output to: " + path.toAbsolutePath());

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file.");
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        }
    }

    private static String promptUserInput(String promptText) {
        System.out.println(promptText);
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

}
