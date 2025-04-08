package com.example.mhraganatplus.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mhraganatplus.R;
import com.example.mhraganatplus.viewmodel.PlayerViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LibraryFragment extends Fragment {
    private PlayerViewModel viewModel;
    private TrackAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        viewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
        adapter = new TrackAdapter(track -> {
            viewModel.setCurrentTrack(track);
            // Navigate to player fragment
        });

        RecyclerView recyclerView = view.findViewById(R.id.track_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_playlist);
        fab.setOnClickListener(v -> {
            // Show create playlist dialog
        });

        viewModel.getAllTracks().observe(getViewLifecycleOwner(), tracks -> {
            adapter.submitList(tracks);
        });
    }
}