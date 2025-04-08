package com.example.mhraganatplus.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mhraganatplus.model.Track;
import java.util.List;

@Dao
public interface TrackDao {
    @Insert
    void insert(Track track);

    @Update
    void update(Track track);

    @Query("SELECT * FROM tracks")
    LiveData<List<Track>> getAllTracks();

    @Query("SELECT * FROM tracks WHERE id = :trackId")
    LiveData<Track> getTrackById(String trackId);

    @Query("SELECT * FROM tracks WHERE isFavorite = 1")
    LiveData<List<Track>> getFavoriteTracks();

    @Query("SELECT * FROM tracks WHERE artist = :artist")
    LiveData<List<Track>> getTracksByArtist(String artist);

    @Query("SELECT * FROM tracks WHERE mood = :mood")
    LiveData<List<Track>> getTracksByMood(String mood);
}