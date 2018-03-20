package com.jonathandavidlewis.albummanager;

import java.util.Comparator;

public class Album implements Comparable<Album>{

    private String artistName;
    private String albumName;
    private int releaseDate;

    public Album(String artistName, String albumName, int releaseDate) {
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

    public static final Comparator<Album> ArtistNameComparator = new Comparator<Album>(){

        @Override
        public int compare(Album o1, Album o2) {
            return o1.artistName.compareTo(o2.artistName);
        }

    };

    public static final Comparator<Album> ReleaseDateComparator = new Comparator<Album>(){

        @Override
        public int compare(Album o1, Album o2) {
            return o1.releaseDate - o2.releaseDate;
        }

    };

    public static final Comparator<Album> AlbumNameComparator = new Comparator<Album>(){

        @Override
        public int compare(Album o1, Album o2) {
            return o1.albumName.compareTo(o2.albumName);
        }

    };

    @Override
    public String toString() {
        if (albumName == null) return super.toString();
        else return String.format("%s,%s,%d", this.artistName, this.albumName, this.releaseDate);
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
}

