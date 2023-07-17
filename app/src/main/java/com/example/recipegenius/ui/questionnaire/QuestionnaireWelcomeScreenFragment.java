package com.example.recipegenius.ui.questionnaire;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentQuestionnaireWelcomeScreenBinding;

public class QuestionnaireWelcomeScreenFragment extends Fragment {

    private FragmentQuestionnaireWelcomeScreenBinding binding;

    TextView welcome;
    TextView description;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentQuestionnaireWelcomeScreenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // initialize welcome message and description
        TextView welcome = root.findViewById(R.id.welcome);
        TextView description = root.findViewById(R.id.questionnaire_description);

        welcome.setText("Welcome!");
        description.setText("Let's get started with a quick questionnaire. We'll use this to identify your goals, preferences, etc. \n\nThis section will ask you about your dietary goals. \n\nFeel free to skip this questionnaire - you can always update these details later.");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
