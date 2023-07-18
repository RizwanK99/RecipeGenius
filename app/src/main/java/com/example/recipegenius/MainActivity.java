package com.example.recipegenius;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DBHandler dbHandler;

//    private TextView monthYearText;
//    private RecyclerView calendarRecyclerView;
//    private LocalDate selectedDate;

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

//        setContentView(R.layout.fragment_calendar);
//        initWidgets();
//        selectedDate = LocalDate.now();
//        setMonthView();

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


//    private void initWidgets() {
//        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
//        monthYearText = findViewById(R.id.monthYearTV);
//
//    }
//
//    private void setMonthView() {
//        monthYearText.setText(monthYearFromDate(selectedDate));
//        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
//
//        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
//        calendarRecyclerView.setLayoutManager(layoutManager);
//        calendarRecyclerView.setAdapter(calendarAdapter);
//    }
//
//    private ArrayList<String> daysInMonthArray(LocalDate date) {
//        ArrayList<String> daysInMonthArray = new ArrayList<>();
//        YearMonth yearMonth = YearMonth.from(date);
//
//        int daysInMonth = yearMonth.lengthOfMonth();
//
//        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
//        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
//
//        for(int i =1; i<=42; i++){
//
//            if(i<=dayOfWeek || i>daysInMonth+dayOfWeek){
//                daysInMonthArray.add("");
//            }
//            else{
//                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
//            }
//
//        }
//
//        return daysInMonthArray;
//
//
//    }
//
//    private String monthYearFromDate(LocalDate date){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
//        return date.format(formatter);
//    }
//
//    public void previousMonthAction(View view){
//        selectedDate = selectedDate.minusMonths(1);
//        setMonthView();
//
//    }
//
//    public void nextMonthAction(View view){
//        selectedDate = selectedDate.plusMonths(1);
//        setMonthView();
//    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return navController.navigateUp();
    }
//
//    @Override
//    public void onItemClick(int position, String dayText) {
//
//        if(dayText.equals("")){
//            String message = "Selected Data" + dayText + " " + monthYearFromDate(selectedDate);
//            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
//        }
//
//    }
}
