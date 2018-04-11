package com.proesd.bab3_kalkulatorsederhana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        btnTambah.setOnClickListener(this);
        btnKurang.setOnClickListener(this);
        btnKali.setOnClickListener(this);
        btnBagi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
		/* mendapatkan angka dari EditText A menggunakan
		 * fungsi getText(), kemudian angka yang masih berupa string
		 * di parse, diubah menjadi float
		 */
        A = Float.parseFloat(editTextA.getText().toString());
        B = Float.parseFloat(editTextB.getText().toString());

        // Listener event untuk setiap tombol
        switch (view.getId()) {
            case R.id.button_tambah:
                // mengisi EditText Hasil dengan menggunakan
// fungsi setText(), dengan nilai dari hasil
                // kalkulasi variabel A dan B.

                // Sebelum mengisikan nilai ke EditText,
                // ubah tipe data menjadi string terlebih dulu.
                editTextHasil.setText(Float.toString(A + B));
                break;
            case R.id.button_kurang:
                editTextHasil.setText(Float.toString(A - B));
                break;
            case R.id.button_kali:
                editTextHasil.setText(Float.toString(A * B));
                break;
            case R.id.button_bagi:
                editTextHasil.setText(Float.toString(A / B));
                break;
        }
    }
}
