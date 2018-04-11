package com.proesd.bab4_togglebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (ToggleButton) findViewById(R.id.togglebutton);
        tb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Toast.makeText(getBaseContext(),
                        "Button is "+tb.getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
