package com.jamjhot.dailylife.jamjhot;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class HomeActivity extends AppCompatActivity {

    Button securityPress,traffic,btn2,aboutus,addCont;

    DbHelperVab dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        securityPress = findViewById(R.id.securityPress);
        traffic = findViewById(R.id.traffic);
        aboutus = findViewById(R.id.aboutus);
        addCont = findViewById(R.id.addCont);
        securityPress.setTransformationMethod(null);
        addCont.setTransformationMethod(null);


        dbHelper= new DbHelperVab(this);

        traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(HomeActivity.this, CheckLocation.class);
                startActivity(intent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(HomeActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        securityPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(HomeActivity.this, SecurityIssue.class);
                startActivity(intent);

            }
        });

        addCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(HomeActivity.this, AddContactList.class);
                startActivity(intent);
            }
        });



    }

    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
         /*Log.e("yyyyyyyyyyyyy","Text");*/
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
