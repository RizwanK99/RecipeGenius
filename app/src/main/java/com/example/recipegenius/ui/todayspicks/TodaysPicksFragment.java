package com.example.recipegenius.ui.todayspicks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipegenius.databinding.FragmentTodayspicksBinding;


public class TodaysPicksFragment extends Fragment {

    private FragmentTodayspicksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TodaysPicksModel todaysPicksViewModel =
                new ViewModelProvider(this).get(TodaysPicksModel.class);

        binding = FragmentTodayspicksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }


}