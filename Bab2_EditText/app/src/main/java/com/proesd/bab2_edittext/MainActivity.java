package com.proesd.bab2_edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextA, editTextB, editTextHasil;
    Button btnTambah, btnKurang, btnKali, btnBagi;
    float A, B, hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextA = (EditText) findViewById(R.id.editText_A);
        editTextB = (EditText) findViewById(R.id.editText_B);
        editTextHasil = (EditText) findViewById(R.id.editText_hasil);
        btnTambah = (Button) findViewById(R.id.button_tambah);
        btnKurang = (Button) findViewById(R.id.button_kurang);
        btnKali = (Button) findViewById(R.id.button_kali);
        btnBagi = (Button) findViewById(R.id.button_bagi);

        // memberikan listener pada button
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A = Float.parseFloat(editTextA.getText().toString());
                B = Float.parseFloat(editTextB.getText().toString());
                editTextHasil.setText(Float.toString(A + B));
            }
        });

        btnKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A = Float.parseFloat(editTextA.getText().toString());
                B = Float.parseFloat(editTextB.getText().toString());
                editTextHasil.setText(Float.toString(A - B));
            }
        });

        btnKali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A = Float.parseFloat(editTextA.getText().toString());
                B = Float.parseFloat(editTextB.getText().toString());
                editTextHasil.setText(Float.toString(A * B));
            }
        });

        btnBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A = Float.parseFloat(editTextA.getText().toString());
                B = Float.parseFloat(editTextB.getText().toString());
                editTextHasil.setText(Float.toString(A / B));
            }
        });
    }
}
