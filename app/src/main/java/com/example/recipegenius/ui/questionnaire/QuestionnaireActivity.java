package com.example.recipegenius.ui.questionnaire;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.recipegenius.MainActivity;
import com.example.recipegenius.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireActivity extends AppCompatActivity {
    private ViewPager screenPager;
    QuestionnaireViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_questionnaire);

        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }

        // fill list screen
        final List<QuestionnaireItem> mList = new ArrayList<>();
        boolean fresh_food = mList.add(new QuestionnaireItem("Page 1"));
        mList.add(new QuestionnaireItem("Page 2"));
        mList.add(new QuestionnaireItem("Page 3"));

        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new QuestionnaireViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);

        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size() - 1) { // when we reach the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isQuestionnaireComplete", true);
        editor.commit();
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isQuestionnaireComplete = pref.getBoolean("isQuestionnaireComplete", false);
        return isQuestionnaireComplete;
    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loadLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);
    }

}
