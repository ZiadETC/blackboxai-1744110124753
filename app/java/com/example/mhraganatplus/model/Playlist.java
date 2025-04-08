package com.example.mhraganatplus.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "playlists")
public class Playlist {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String trackIds; // Comma-separated list of track IDs
}