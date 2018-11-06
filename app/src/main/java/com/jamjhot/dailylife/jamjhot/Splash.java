package com.jamjhot.dailylife.jamjhot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

     ProgressBar progressBar;
     int progress_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.ps_br);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                splash_progress();
            }
        });
        thread.start();

    }

    public void splash_progress(){
        for (progress_value=20; progress_value<=100; progress_value+=20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress_value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent Int = new Intent(Splash.this, HomeActivity.class);
                startActivity(Int);
            }
        });

    }


}
