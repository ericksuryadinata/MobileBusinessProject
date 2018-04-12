package com.proesd.bab5_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mengisi variable dengan id widget
        cb = (CheckBox) findViewById(R.id.cb1);
        cb.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
    }

    // untuk menentukan apakah checkbox dicentang atau tidak dan event apa yang terjadi
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            cb.setText("Dipilih"); // jika dicheck maka text pada cekbox berubah menjadi “Dipilih”
        } else {
            cb.setText("Tidak Dipilih"); // jika diuncheck maka text pada cekbox kembali menjadi “Tidak Dipilih”

        }
    }
}
