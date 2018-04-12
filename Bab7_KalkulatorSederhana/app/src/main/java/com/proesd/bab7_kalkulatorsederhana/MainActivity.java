package com.proesd.bab7_kalkulatorsederhana;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText angka1, angka2;
    TextView hasil;
    Editable isiangka1, isiangka2;
    Button reset;
    Button tambah,kurang,kali,bagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mengisi variable dengan id widget
        hasil =  (TextView) findViewById(R.id.hasil);
        angka1 = (EditText) findViewById(R.id.angka1);
        angka2 = (EditText) findViewById(R.id.angka2);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new reset());
        tambah = (Button) findViewById(R.id.tambah);
        tambah.setOnClickListener(new tambah());
        kurang = (Button) findViewById(R.id.kurang);
        kurang.setOnClickListener(new kurang());
        bagi = (Button) findViewById(R.id.bagi);
        bagi.setOnClickListener(new bagi());
        kali = (Button) findViewById(R.id.kali);
        kali.setOnClickListener(new kali());
    }

    // ini adalah event ketika tombol reset di klik
    private class reset implements View.OnClickListener {
        public void onClick(View v) {
            try {
                angka1.setText(""); // untuk mereset text pada textview angka1
                angka2.setText(""); // untuk mereset text pada textview angka2
                hasil.setText(""); // untuk mereset text pada textedit hasil
            } catch (Exception e) {
            };
        }
    }

    // ini adalah event ketika tombol tambah di klik
    private class tambah implements View.OnClickListener {
        public void onClick(View v) {
            try {
                Double h = Double.parseDouble(angka1.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                Double d = Double.parseDouble(angka2.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                double hsl = h + d; // menjumlahkan kedua data yang telah diambil
                DecimalFormat df = new DecimalFormat("@@##");
                hasil.setText(df.format(hsl)); // mengeluarkan hasil penjumlahan
            } catch (Exception e) {
            };
        }
    }

    // ini adalah event ketika tombol kurang di klik
    private class kurang implements View.OnClickListener {
        public void onClick(View v) {
            try {
                Double h = Double.parseDouble(angka1.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                Double d = Double.parseDouble(angka2.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                double hsl = h - d; // mengurangi kedua data yang telah diambil
                DecimalFormat df = new DecimalFormat("@@##");
                hasil.setText(df.format(hsl)); // mengeluarkan hasil pengurangan
            } catch (Exception e) {
            };
        }
    }

    // ini adalah event ketika tombol kali di klik
    private class kali implements View.OnClickListener {
        public void onClick(View v) {
            try {
                Double h = Double.parseDouble(angka1.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                Double d = Double.parseDouble(angka2.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                double hsl = h * d; // mengalikan kedua data yang telah diambil
                DecimalFormat df = new DecimalFormat("@@##");
                hasil.setText(df.format(hsl)); // mengeluarkan hasil perkalian
            } catch (Exception e) {
            };
        }
    }

    // ini adalah event ketika tombol bagi di klik
    private class bagi implements View.OnClickListener {
        public void onClick(View v) {
            try {
                Double h = Double.parseDouble(angka1.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                Double d = Double.parseDouble(angka2.getText().toString()); // mengambil data dari textfield lalu diubah ke double
                double hsl = h / d; // membagi kedua data yang telah diambil
                DecimalFormat df = new DecimalFormat("@@##");
                hasil.setText(df.format(hsl)); // mengeluarkan hasil pembagian
            } catch (Exception e) {
            };
        }
    }
}
