package com.example.mhraganatplus.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tracks")
public class Track {
    @PrimaryKey
    @NonNull
    public String id;
    public String title;
    public String artist;
    public String album;
    public String path;
    public int duration;
    public String mood;
    public boolean isFavorite;
}