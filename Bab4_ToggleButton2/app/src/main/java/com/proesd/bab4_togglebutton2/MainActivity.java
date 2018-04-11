package com.proesd.bab4_togglebutton2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton button1;
    private ToggleButton button2;
    private EditText textView1;
    private EditText textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (ToggleButton) findViewById(R.id.toggleButton1);
        button2 = (ToggleButton) findViewById(R.id.toggleButton2);
        textView1=(EditText) findViewById(R.id.editText1);
        textView2=(EditText) findViewById(R.id.editText2);
        textView1.setText("Tombol OFF");
        textView2.setText("Tombol OFF");
        button2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.i("info", "Tombol on!");
                    textView2.setText("Tombol ON");
                } else {
                    Log.i("info", "Tombol off!");
                    textView2.setText("Tombol OFF");
                }
            }
        });
    }

    /**
     * Click event of the togglebutton1 is declared in the layout xml file itself.
     * @param view
     */
    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            Log.i("info", "Tombol on!");
            textView1.setText("Tombol ON");
        } else {
            Log.i("info", "Tombol off!");
            textView1.setText("Tombol OFF");
        }
    }
}
