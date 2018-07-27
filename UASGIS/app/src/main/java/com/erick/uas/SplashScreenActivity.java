package com.erick.uas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.makichanz.uas.R;

public class SplashScreenActivity extends AppCompatActivity {

    private Thread thread;
    private Handler handler;
    private static final int LONG_MILIS_SCREEN = 3000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                   Thread.sleep(LONG_MILIS_SCREEN);
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           goToNextScreen();
                         //  Toast.makeText(getApplicationContext(),"DONE ! ",Toast.LENGTH_SHORT).show();
                       }
                   });
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    private void goToNextScreen() {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
        finish();
    }
}
