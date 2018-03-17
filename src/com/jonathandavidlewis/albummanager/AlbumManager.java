package com.jonathandavidlewis.albummanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AlbumManager {

    public static void main(String[] args) {

        String textLine = null;

        List<Album> list = new ArrayList<Album>();

        try {
            FileReader fileReader = new FileReader("sample-album-list.txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int lineNumber = 0;
            while ((textLine = bufferedReader.readLine()) != null) {

                List<String> albumInfo = Arrays.asList(textLine.split(","));

                String artistName = albumInfo.get(0);
                String albumName = albumInfo.get(1);
                int releaseDate = Integer.parseInt(albumInfo.get(2));

                Album thisAlbum = new Album(artistName, albumName, releaseDate);

                list.add(thisAlbum);
                lineNumber++;

            }

            bufferedReader.close();

            Collections.sort(list);

            StringBuffer outputText = new StringBuffer();

            for(Album album : list) {
                String albumEntryAsString = album.toString() + "\n";
                outputText.append(albumEntryAsString);
            }

            File directory = new File("output");
            if (! directory.exists()){
                directory.mkdir();
            }

            Path path = Paths.get(directory + "/album-output.txt");
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
}
