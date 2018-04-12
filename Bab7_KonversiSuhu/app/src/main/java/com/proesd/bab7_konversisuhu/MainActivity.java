package com.proesd.bab7_konversisuhu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Deklarasi EditText
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan edit text di activity main
        text = (EditText) findViewById(R.id.EditText01);
    }

    // Membuat fungsi di bagian radio button
    public void myClickHandler(View view) {
        switch (view.getId()) {
            case R.id.Button01:
                // Deklarasi radio button
                RadioButton reamurButton = (RadioButton)findViewById(R.id.RadioButton01);
                RadioButton fahrenheitButton = (RadioButton)findViewById(R.id.RadioButton02);
                RadioButton kelvinButton = (RadioButton)findViewById(R.id.RadioButton03);
                // Toast akan muncul ketika belum mengisi angka
                if (text.getText().length() == 0) {
                    Toast.makeText(this,"Silahkan isi Angka yang benar!", Toast.LENGTH_LONG).show();
                    return;
                }
                // Mengambil nilai celsius dan di jadikan float
                float inputValue = Float.parseFloat(text.getText().toString());
                // Mengambil nilai radio button mana yang di pilih
                if (reamurButton.isChecked()) {
                    // Memanggil fungsi convertSuhuToR
                    text.setText(String.valueOf(convertSuhuToR(inputValue)));
                } else if (fahrenheitButton.isChecked()){
                    // Memanggil fungsi convertSuhuToF
                    text.setText(String.valueOf(convertToF(inputValue)));
                } else if (kelvinButton.isChecked()) {
                    // Memanggil fungsi convertToK
                    text.setText(String.valueOf(convertToK(inputValue)));
                }
        }
    }

    // Konversi ke REAMUR dengan Toast
    private float convertSuhuToR(float awal1) {
        // Menampilkan dalam bentuk Toast
        Toast.makeText(getApplicationContext(),"Hasil Konversi Suhu Celcius Ke Reamur Adalah = "+(awal1*0.8),Toast.LENGTH_LONG).show();
        return (float)(awal1);
    }

    // Konversi ke FAHRENHEIT dengan Toast
    private float convertToF(float awal2) {
        // Memanggil fungsi convertToF
        Toast.makeText(getApplicationContext(),"Hasil Konversi Suhu Celcius Ke Fahrenheit Adalah = "+((awal2*1.8)+32),Toast.LENGTH_LONG).show();
        return (float)(awal2);
    }

    // Konversi ke KELVIN Toast
    private float convertToK(float awal3) {
        // Memanggil fungsi convertToK
        Toast.makeText(getApplicationContext(),"Hasil Konversi Suhu Celcius Ke Kelvin Adalah = "+(awal3+273),Toast.LENGTH_LONG).show();
        return (awal3);
    }
}
