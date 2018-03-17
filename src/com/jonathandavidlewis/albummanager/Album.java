package com.jonathandavidlewis.albummanager;

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
        int comparrison = this.artistName.compareTo(o.artistName);
        if (comparrison != 0) {
            return comparrison;
        } else {
            comparrison = this.releaseDate - o.releaseDate;
            if (comparrison != 0) {
                return comparrison;
            } else {
                return this.albumName.compareTo(o.albumName);
            }
        }
    }

    @Override
    public String toString() {
        if (albumName == null) return super.toString();
        else return String.format("%s,%s,%d", this.albumName, this.artistName, this.releaseDate);
    }

}

