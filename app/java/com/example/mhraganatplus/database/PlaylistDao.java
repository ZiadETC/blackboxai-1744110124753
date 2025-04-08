package com.example.mhraganatplus.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mhraganatplus.model.Playlist;
import java.util.List;

@Dao
public interface PlaylistDao {
    @Insert
    void insert(Playlist playlist);

    @Update
    void update(Playlist playlist);

    @Query("DELETE FROM playlists WHERE id = :playlistId")
    void delete(int playlistId);

    @Query("SELECT * FROM playlists")
    LiveData<List<Playlist>> getAllPlaylists();

    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    LiveData<Playlist> getPlaylistById(int playlistId);

    @Query("SELECT * FROM playlists WHERE name LIKE :searchQuery")
    LiveData<List<Playlist>> searchPlaylists(String searchQuery);
}