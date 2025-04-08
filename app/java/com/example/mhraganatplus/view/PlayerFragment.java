package com.example.mhraganatplus.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mhraganatplus.R;
import com.example.mhraganatplus.viewmodel.PlayerViewModel;

public class PlayerFragment extends Fragment {
    private PlayerViewModel viewModel;
    private SeekBar seekBar;
    private TextView currentTime;
    private TextView totalTime;
    private TextView trackTitle;
    private TextView trackArtist;
    private ImageView albumArt;
    private ImageButton playPauseButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        viewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
        
        // Initialize views
        seekBar = view.findViewById(R.id.seek_bar);
        currentTime = view.findViewById(R.id.current_time);
        totalTime = view.findViewById(R.id.total_time);
        trackTitle = view.findViewById(R.id.track_title);
        trackArtist = view.findViewById(R.id.track_artist);
        albumArt = view.findViewById(R.id.album_art);
        playPauseButton = view.findViewById(R.id.btn_play_pause);

        // Setup click listeners
        playPauseButton.setOnClickListener(v -> viewModel.playPause());
        view.findViewById(R.id.btn_previous).setOnClickListener(v -> viewModel.previous());
        view.findViewById(R.id.btn_next).setOnClickListener(v -> viewModel.next());

        // Observe ViewModel data
        viewModel.getCurrentTrack().observe(getViewLifecycleOwner(), track -> {
            if (track != null) {
                trackTitle.setText(track.title);
                trackArtist.setText(track.artist);
                // Load album art here
            }
        });

        viewModel.getIsPlaying().observe(getViewLifecycleOwner(), isPlaying -> {
            playPauseButton.setImageResource(
                isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
        });

        viewModel.getCurrentPosition().observe(getViewLifecycleOwner(), position -> {
            seekBar.setProgress(position);
            currentTime.setText(formatTime(position));
        });
    }

    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}