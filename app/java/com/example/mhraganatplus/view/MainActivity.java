package com.example.mhraganatplus.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mhraganatplus.R;
import com.example.mhraganatplus.service.AudioService;
import com.example.mhraganatplus.viewmodel.PlayerViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private PlayerViewModel viewModel;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup ViewModel
        viewModel = new ViewModelProvider(this).get(PlayerViewModel.class);

        // Setup service connection
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                AudioService.LocalBinder binder = (AudioService.LocalBinder) service;
                viewModel.setAudioService(binder.getService());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                viewModel.clearAudioService();
            }
        };

        // Start and bind to AudioService
        Intent serviceIntent = new Intent(this, AudioService.class);
        startService(serviceIntent);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        // Setup bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNav, navController);

        // Setup MiniPlayer
        MiniPlayerView miniPlayer = findViewById(R.id.mini_player);
        miniPlayer.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
