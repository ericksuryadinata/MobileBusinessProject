package com.proesd.bab2_textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t;
        t = (TextView)findViewById(R.id.tulisan);
        t.setText("Ini adalah tulisan dengan widget TextView");
    }
}
