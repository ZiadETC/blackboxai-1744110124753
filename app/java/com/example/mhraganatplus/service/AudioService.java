package com.example.mhraganatplus.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Binder;
import android.util.Log;

import com.example.mhraganatplus.model.Track;
import java.io.IOException;

public class AudioService extends Service {
    private final IBinder binder = new LocalBinder();
    private MediaPlayer mediaPlayer;
    private Track currentTrack;
    private boolean isPrepared = false;

    public class LocalBinder extends Binder {
        public AudioService getService() {
            return AudioService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    public void playTrack(Track track) {
        try {
            if (currentTrack != null && currentTrack.id.equals(track.id) && isPrepared) {
                mediaPlayer.start();
                return;
            }

            mediaPlayer.reset();
            mediaPlayer.setDataSource(track.path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> {
                isPrepared = true;
                mp.start();
            });
            currentTrack = track;
        } catch (IOException e) {
            Log.e("AudioService", "Error playing track", e);
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void resume() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying() && isPrepared) {
            mediaPlayer.start();
        }
    }

    public void seekTo(int position) {
        if (mediaPlayer != null && isPrepared) {
            mediaPlayer.seekTo(position);
        }
    }

    public int getCurrentPosition() {
        return mediaPlayer != null ? mediaPlayer.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return mediaPlayer != null ? mediaPlayer.getDuration() : 0;
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}