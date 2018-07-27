package com.proesd.erick.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton mainkan;
    ImageButton stop;
    TextView keterangan;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keterangan = (TextView)findViewById(R.id.keterangan);
        keterangan.setText("Silakan klik tombol play");

        mainkan = (ImageButton)findViewById(R.id.mainkan);
        mainkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainkan.setEnabled(false);
                keterangan.setText("Tombol Play Tidak Aktif");
                mp = MediaPlayer.create(MainActivity.this,R.raw.annas);
                try {
                    //cari file itu ada apa tidak, siapkan
                    mp.prepare();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mp.start();
            }
        });

        stop = (ImageButton)findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainkan.setEnabled(true);
                keterangan.setText("Tombol Play Aktif");
                mp.stop();
            }
        });
    }

}
