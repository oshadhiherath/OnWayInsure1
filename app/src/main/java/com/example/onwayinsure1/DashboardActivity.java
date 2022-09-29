package com.example.onwayinsure1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    //Declare field variables
    EditText currentDate;
    ImageButton btnAddClaim, btnClaimHistory, btnVehicles, btnCreateSOS;
    //DrawerLayout drawerLayout;
    //NavigationView navigationView;
    //Toolbar toolbar;


    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Get the Action bar,its help for customize it
        ActionBar actionBar = getSupportActionBar();
        //remove AB
        actionBar.hide();
        //actionBar.setTitle("Hello, ");

        //currentDate = findViewById(R.id.CurrentDateText);
        btnAddClaim =findViewById(R.id.btnAddClaim);
        //btnClaimHistory =findViewById(R.id.btnClaimHistory);
        //btnVehicles =findViewById(R.id.btnVehicles);
        btnCreateSOS =findViewById(R.id.btnCreateSOS);
        //drawerLayout =findViewById(R.id.drawer_layout);
        //navigationView =findViewById(R.id.nav_view);
        //toolbar =findViewById(R.id.toolbar);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");
        date = dateFormat.format(calendar.getTime());
        currentDate.setText(date);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setSupportActionBar(toolbar);

        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.addDrawerListener(toggle);
        //toggle.syncState();


        //Navigate to Add Claim Activity
        btnAddClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, AddClaimActivity.class));
            }
        });

        //Navigate to Claim History Activity
//        btnClaimHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DashboardActivity.this, ClaimHistroryActivity.class));
//            }
//        });

        //Navigate to Vehicles Activity
//        btnVehicles.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DashboardActivity.this, VehiclesActivity.class));
//            }
//        });

        //Navigate to Create SOS Activity
        btnCreateSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, CreateSOSActivity.class));


                //Activity
                Intent intent = new Intent(DashboardActivity.this,CreateSOSActivity.class);
                startActivity(intent);
            }
        });



    }
}