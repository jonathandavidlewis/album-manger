package com.jonathandavidlewis.albummanager;

public class Album{

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

}

