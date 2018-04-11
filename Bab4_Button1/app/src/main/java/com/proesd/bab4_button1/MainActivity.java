package com.proesd.bab4_button1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.Button1);
        //widgets button dengan nama button1 di tujukan di idbutton1 yang anda buat pada layout
        button1.setOnClickListener(new View.OnClickListener() {
            //memberikan event onclick pada  button1 yang anda buat
            public void onClick(View V) {
                Toast.makeText(getApplicationContext(), "BUTTON 1 DITEKAN", Toast.LENGTH_SHORT).show();
            }
        });

        // membuat action pada button ketika menekan tombol 2 akan muncul tulisan (button 2 di tekan)
        button2 = (Button) findViewById(R.id.Button2);
        //widgets button dengan nama button2 di tujukan di idbutton2 yang anda buat pada layout
        button2.setOnClickListener(new View.OnClickListener() {
            //memberikan event onclick pada  button2 yang anda buat
            public void onClick(View V) {
                //widgets toast di gunakan untuk menampilkan pesan yang anda buat jika menekan button 2
                Toast.makeText(getApplicationContext(), "BUTTON 2 DITEKAN", Toast.LENGTH_SHORT).show();
            }
        });

        // membuat action pada button ketika menekan tombol 3 akan muncul tulisan (button 3 di tekan)
        button3 = (Button) findViewById(R.id.Button3);
        //widgets button dengan nama button3 di tujukan di idbutton3 yang anda buat pada layout
        button3.setOnClickListener(new View.OnClickListener() {
            //memberikan event onclick pada  button3 yag anda buat
            public void onClick(View V) {
                //widgets toast di gunakan untuk menampilkan pesan yang anda buat jika menekan button 3
                Toast.makeText(getApplicationContext(), "BUTTON 3 DITEKAN", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
