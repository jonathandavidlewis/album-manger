package com.jonathandavidlewis.albummanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Album implements Comparable<Album>{

    private String artistName;
    private String albumName;
    private int releaseDate;

    private Album(String artistName, String albumName, int releaseDate) {
        this.artistName = artistName;
        this.albumName = albumName;
        this.releaseDate = releaseDate;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public int getReleaseDate() {
        return this.releaseDate;
    }

    @Override
    public int compareTo(Album o) {
        int comparison = this.artistName.compareTo(o.artistName);
        if (comparison != 0) {
            return comparison;
        } else {
            comparison = this.releaseDate - o.releaseDate;
            if (comparison != 0) {
                return comparison;
            } else {
                return this.albumName.compareTo(o.albumName);
            }
        }
    }

    private static final Comparator<Album> ArtistNameComparator = new Comparator<Album>(){

        @Override
        public int compare(Album o1, Album o2) {
            return o1.artistName.compareTo(o2.artistName);
        }

    };

    private static final Comparator<Album> ReleaseDateComparator = new Comparator<Album>(){

        @Override
        public int compare(Album o1, Album o2) {
            return o1.releaseDate - o2.releaseDate;
        }

    };

    private static final Comparator<Album> AlbumNameComparator = new Comparator<Album>(){

        @Override
        public int compare(Album o1, Album o2) {
            return o1.albumName.compareTo(o2.albumName);
        }

    };

    @Override
    public String toString() {
        return String.format("%s,%s,%d", this.artistName, this.albumName, this.releaseDate);
    }

    public static Comparator<Album> getComparator(String fieldName) {
        if (fieldName.equals("artist")) {
            return ArtistNameComparator;
        } else if (fieldName.equals("album")) {
            return AlbumNameComparator;
        } else if (fieldName.equals("date")) {
            return ReleaseDateComparator;
        }
        System.out.println("No comparator matched input. Use only 'album', 'date', or 'artist'");
        return null;
    }

    public static Album parseCsvToAlbum(String csvLine) {
        List<String> albumInfo = Arrays.asList(csvLine.split(","));

        String artistName = albumInfo.get(0);
        String albumName = albumInfo.get(1);
        int releaseDate = Integer.parseInt(albumInfo.get(2));

        return new Album(artistName, albumName, releaseDate);
    }

    public static List<Album> importAlbumsFromCsv(String filePath) {
        String csvLine = null;

        List<Album> list = new ArrayList<Album>();

        try {
            FileReader fileReader = new FileReader(filePath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((csvLine = bufferedReader.readLine()) != null) {
                list.add(Album.parseCsvToAlbum(csvLine));
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file: " + filePath + " Please check the file path and try again.");
        } catch (IOException ex) {
            System.out.println("Error reading file: " + filePath);
        }
        return list;
    }

    public static Comparator<Album> createCustomComparator(String promptText) {
        System.out.println(promptText);
        Comparator<Album> sortOrder = null;
        String sortField;
        String sortFields;

        do {
            sortField = Utils.promptUserInput("By what field would you like to sort? Type 'album', 'artist', 'date' or 'none'");
            sortFields = sortField;
            if (sortField.equals("none")) {
                return null;
            }
            sortOrder = Album.getComparator(sortField);
        } while(sortOrder == null);

        while (!sortField.equals("none")) {
            sortField = Utils.promptUserInput("By what other field would you like to sort? Type 'album', 'artist', 'date' or 'none'");
            if (sortField.equals("none")) {
                break;
            }
            Comparator<Album> additionalSortOrder = Album.getComparator(sortField);
            if (additionalSortOrder == null) {
                continue;
            }
            sortFields += ", " + sortField;
            sortOrder = sortOrder.thenComparing(additionalSortOrder);
        }
        System.out.println("Albums will be sorted by " + sortFields);
        return sortOrder;
    }
}

