package com.example.recipegenius;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.recipegenius.database.DBHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

// import androidx.datastore.preferences.core.Preferences;
// import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
// import androidx.datastore.rxjava2.RxDataStore;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.recipegenius.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_calendar, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // dataStore = new RxPreferenceDataStoreBuilder(this, "filters").build();
        Context context = this;
        SharedPreferences dietFilters = context.getSharedPreferences("dietFilters", Context.MODE_PRIVATE);
        SharedPreferences allergyFilters = context.getSharedPreferences("allergyFilters", Context.MODE_PRIVATE);

//        dbHandler = new DBHandler(MainActivity.this);
//        Cursor cursor = dbHandler.getReadableDatabase().query("dietFilters", new String[] {"name"},  null, null, null, null, null, "100");
//        dbHandler.insertRow("Vegan");
//        dbHandler.insertRow("Vegetarian");
//        dbHandler.insertRow("Keto");
//        dbHandler.insertRow("Paleo");
//        dbHandler.insertRow("Gluten-Free");
//        dbHandler.insertRow("Pescatarian");
//        dbHandler.insertRow("Low FODMAP");

        // see DB contents
//        System.out.println(cursor.getCount());
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do
//                {
//                    String name = cursor.getString(0);
//                    System.out.println(name);
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return navController.navigateUp();
    }
}