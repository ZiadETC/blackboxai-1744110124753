package com.example.mhraganatplus.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mhraganatplus.R;
import com.example.mhraganatplus.model.Track;

public class TrackAdapter extends ListAdapter<Track, TrackAdapter.TrackViewHolder> {
    private final OnTrackClickListener listener;

    public TrackAdapter(OnTrackClickListener listener) {
        super(new TrackDiffCallback());
        this.listener = listener;
    }

    public interface OnTrackClickListener {
        void onTrackClick(Track track);
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = getItem(position);
        holder.bind(track, listener);
    }

    static class TrackViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final TextView artistView;
        private final ImageButton favoriteButton;

        TrackViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.track_title);
            artistView = itemView.findViewById(R.id.track_artist);
            favoriteButton = itemView.findViewById(R.id.btn_favorite);
        }

        void bind(Track track, OnTrackClickListener listener) {
            titleView.setText(track.title);
            artistView.setText(track.artist);
            favoriteButton.setImageResource(
                track.isFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);

            itemView.setOnClickListener(v -> listener.onTrackClick(track));
            favoriteButton.setOnClickListener(v -> {
                // Toggle favorite status
            });
        }
    }

    static class TrackDiffCallback extends DiffUtil.ItemCallback<Track> {
        @Override
        public boolean areItemsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
            return oldItem.equals(newItem);
        }
    }
}