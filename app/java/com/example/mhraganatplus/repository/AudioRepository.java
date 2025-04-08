package com.example.mhraganatplus.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.mhraganatplus.database.AppDatabase;
import com.example.mhraganatplus.database.TrackDao;
import com.example.mhraganatplus.database.PlaylistDao;
import com.example.mhraganatplus.model.Track;
import com.example.mhraganatplus.model.Playlist;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioRepository {
    private final TrackDao trackDao;
    private final PlaylistDao playlistDao;
    private final ExecutorService executor;

    public AudioRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        trackDao = db.trackDao();
        playlistDao = db.playlistDao();
        executor = Executors.newSingleThreadExecutor();
    }

    // Track operations
    public LiveData<List<Track>> getAllTracks() {
        return trackDao.getAllTracks();
    }

    public LiveData<Track> getTrackById(String trackId) {
        return trackDao.getTrackById(trackId);
    }

    public void insertTrack(Track track) {
        executor.execute(() -> trackDao.insert(track));
    }

    public void updateTrack(Track track) {
        executor.execute(() -> trackDao.update(track));
    }

    // Playlist operations
    public LiveData<List<Playlist>> getAllPlaylists() {
        return playlistDao.getAllPlaylists();
    }

    public void insertPlaylist(Playlist playlist) {
        executor.execute(() -> playlistDao.insert(playlist));
    }

    public void updatePlaylist(Playlist playlist) {
        executor.execute(() -> playlistDao.update(playlist));
    }

    public void deletePlaylist(int playlistId) {
        executor.execute(() -> playlistDao.delete(playlistId));
    }
}