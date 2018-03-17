package com.jonathandavidlewis.albummanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

            System.out.println(list);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file.");
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        }
    }
}
