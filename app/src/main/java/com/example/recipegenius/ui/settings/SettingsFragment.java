package com.example.recipegenius.ui.settings;

import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentSettingsBinding;

public class SettingsFragment extends PreferenceFragmentCompat {

    private FragmentSettingsBinding binding;

    // public View onCreateView(@NonNull LayoutInflater inflater,
    //         ViewGroup container, Bundle savedInstanceState) {
    //     SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
    //     binding = FragmentSettingsBinding.inflate(inflater, container, false);
    //     View root = binding.getRoot();
    //     Switch simpleSwitch = (Switch) root.findViewById(R.id.notificationsPreferance);
    //     System.out.println(simpleSwitch);

    //     SharedPreferences notificationsPreferance = getActivity().getSharedPreferences("notificationsPreferance",
    //             Context.MODE_PRIVATE);
    //     SharedPreferences.Editor editor = notificationsPreferance.edit();

    //     editor.putInt("enabled", 0);
    //     editor.apply();
    //     return root;

    // }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

}