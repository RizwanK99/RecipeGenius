package com.example.recipegenius.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentSettingsBinding;

public class SettingsFragment extends PreferenceFragmentCompat {

    private FragmentSettingsBinding binding;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

}