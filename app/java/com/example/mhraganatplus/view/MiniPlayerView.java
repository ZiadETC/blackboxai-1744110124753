package com.example.mhraganatplus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mhraganatplus.R;
import com.example.mhraganatplus.model.Track;
import com.example.mhraganatplus.viewmodel.PlayerViewModel;

public class MiniPlayerView extends ConstraintLayout {
    private ImageView albumArt;
    private TextView trackTitle;
    private TextView trackArtist;
    private ImageButton playPauseButton;
    private PlayerViewModel viewModel;

    public MiniPlayerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MiniPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MiniPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.mini_player, this, true);
        
        albumArt = findViewById(R.id.mini_album_art);
        trackTitle = findViewById(R.id.mini_track_title);
        trackArtist = findViewById(R.id.mini_track_artist);
        playPauseButton = findViewById(R.id.mini_btn_play_pause);
    }

    public void setViewModel(PlayerViewModel viewModel) {
        this.viewModel = viewModel;
        setupListeners();
        observeViewModel();
    }

    private void setupListeners() {
        playPauseButton.setOnClickListener(v -> viewModel.playPause());
        setOnClickListener(v -> {
            // Expand to full player
        });
    }

    private void observeViewModel() {
        viewModel.getCurrentTrack().observeForever(track -> {
            if (track != null) {
                trackTitle.setText(track.title);
                trackArtist.setText(track.artist);
                // Load album art
            }
        });

        viewModel.getIsPlaying().observeForever(isPlaying -> {
            playPauseButton.setImageResource(
                isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
        });
    }
}