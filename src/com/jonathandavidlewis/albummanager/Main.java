package com.jonathandavidlewis.albummanager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("This will sort the albums in a file.");

        String filePath = Utils.promptUserInput("Please enter the full or relative file path of your album list, then press ENTER: ");
        String outputFilePath = Utils.promptUserInput("Please enter the full or relative output file path, then press ENTER: ");
        Comparator<Album> sortOrder = Album.createCustomComparator("Define a sort order.");

        List<Album> albums = Album.importAlbumsFromCsv(filePath);

        if(!(sortOrder == null)) {
            Collections.sort(albums, sortOrder);
        } else {
            System.out.println("Your albums were not sorted.");
        }

        String albumsAsCsv = Utils.convertListToCsv(albums);

        Utils.saveStringToFile(albumsAsCsv, outputFilePath);
    }
}
