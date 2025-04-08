package com.example.mhraganatplus.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mhraganatplus.R;
import com.example.mhraganatplus.util.ThemeHelper;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {
    private SwitchMaterial themeSwitch;
    private SwitchMaterial shuffleSwitch;
    private SwitchMaterial repeatSwitch;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        themeSwitch = view.findViewById(R.id.switch_theme);
        shuffleSwitch = view.findViewById(R.id.switch_shuffle);
        repeatSwitch = view.findViewById(R.id.switch_repeat);

        // Load current settings
        int currentTheme = ThemeHelper.getSavedThemePreference(context);
        themeSwitch.setChecked(currentTheme == ThemeHelper.THEME_DARK);

        setupListeners();
    }

    private void setupListeners() {
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int theme = isChecked ? ThemeHelper.THEME_DARK : ThemeHelper.THEME_LIGHT;
            ThemeHelper.saveThemePreference(context, theme);
            ThemeHelper.applyTheme(theme);
        });

        shuffleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Toggle shuffle
        });

        repeatSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Toggle repeat
        });

        view.findViewById(R.id.btn_clear_cache).setOnClickListener(v -> {
            // Clear cache
        });
    }
}
