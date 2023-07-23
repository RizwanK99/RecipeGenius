package com.example.recipegenius.ui.settings;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentSettingsBinding;

public class SettingsFragment extends PreferenceFragmentCompat {

    private FragmentSettingsBinding binding;
    public SwitchPreference handsfreePref;

//     public View onCreateView(@NonNull LayoutInflater inflater,
//             ViewGroup container, Bundle savedInstanceState) {
//         SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
//         binding = FragmentSettingsBinding.inflate(inflater, container, false);
//         View root = binding.getRoot();
    //     Switch simpleSwitch = (Switch) root.findViewById(R.id.notificationsPreference);
    //     System.out.println(simpleSwitch);

    //     SharedPreferences notificationsPreference = getActivity().getSharedPreferences("notificationsPreference",
    //             Context.MODE_PRIVATE);
    //     SharedPreferences.Editor editor = notificationsPreference.edit();

    //     editor.putInt("enabled", 0);
    //     editor.apply();
//         Button toggleSwitch = (Button) root.findViewById(R.id.handsfreePreference);
//         toggleSwitch.setOnClickListener(new View.OnClickListener() {
//             public void onClick(View v) {
//                 View.OnClickListener context = this;
//                 SharedPreferences handsfreePreference = getActivity().getSharedPreferences("handsfreePreference",
//                         Context.MODE_PRIVATE);
//                 SharedPreferences.Editor editor = handsfreePreference.edit();
//
//                 if (handsfreePreference.getInt("enabled", 0) == 0) {
//                     editor.putInt("enabled", 1);
//                     editor.apply();
//                     System.out.println("Hands free mode enabled");
//                 } else {
//                     editor.putInt("enabled", 0);
//                     editor.apply();
//                     System.out.println("Hands free mode disabled");
//                 }
//             }
//         });
//         return root;
//
//     }
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Indicate here the XML resource you created above that holds the preferences
        setPreferencesFromResource(R.xml.preferences, rootKey);
        
    }

    // public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    //    if (key.equals("notificationsPreference")) {
    //        SwitchPreference notificationsPreference = findPreference(key);
    //        SharedPreferences.Editor editor = sharedPreferences.edit();
    //        editor.putInt("enabled", 0);
    //        editor.apply();
    //    }
    //    if (key.equals("handsfreePreference")) {
    //        SwitchPreference handsfreePreference = findPreference(key);
    //        SharedPreferences.Editor editor = sharedPreferences.edit();
    //        editor.putInt("enabled", 0);
    //        editor.apply();
    //    }

    // }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("HandsFree")) {
            boolean handsfree = sharedPreferences.getBoolean("HandsFree", false);
            //Do whatever you want here. This is an example.
            System.out.println("Hands free mode enabled");
        }
    }

}

