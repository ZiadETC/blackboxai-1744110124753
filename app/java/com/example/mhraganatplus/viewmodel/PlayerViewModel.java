package com.example.mhraganatplus.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mhraganatplus.model.Track;
import com.example.mhraganatplus.repository.AudioRepository;
import com.example.mhraganatplus.service.AudioService;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private final AudioRepository repository;
    private final MutableLiveData<Track> currentTrack = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPlaying = new MutableLiveData<>(false);
    private final MutableLiveData<Integer> currentPosition = new MutableLiveData<>(0);
    private AudioService audioService;
    private boolean isBound = false;

    public PlayerViewModel(Application application) {
        super(application);
        repository = new AudioRepository(application);
    }

    public LiveData<Track> getCurrentTrack() {
        return currentTrack;
    }

    public LiveData<Boolean> getIsPlaying() {
        return isPlaying;
    }

    public LiveData<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentTrack(Track track) {
        currentTrack.setValue(track);
        if (audioService != null) {
            audioService.playTrack(track);
            isPlaying.setValue(true);
        }
    }

    public void playPause() {
        if (audioService == null) return;
        
        if (Boolean.TRUE.equals(isPlaying.getValue())) {
            audioService.pause();
            isPlaying.setValue(false);
        } else {
            audioService.resume();
            isPlaying.setValue(true);
        }
    }

    public void previous() {
        // Implement previous track logic
    }

    public void next() {
        // Implement next track logic
    }

    public void seekTo(int position) {
        if (audioService != null) {
            audioService.seekTo(position);
        }
    }

    public LiveData<List<Track>> getAllTracks() {
        return repository.getAllTracks();
    }

    public void setAudioService(AudioService service) {
        this.audioService = service;
        isBound = true;
    }

    public void clearAudioService() {
        this.audioService = null;
        isBound = false;
    }
}
