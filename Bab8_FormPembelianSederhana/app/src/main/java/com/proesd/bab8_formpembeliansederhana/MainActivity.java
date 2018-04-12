package com.proesd.bab8_formpembeliansederhana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Deklarasi variable untuk view
    private Button bproses;
    private EditText editText1,editText2,editText3,editText4;
    private CheckBox ch,ch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //untuk mengambil nilai dari layout xml yang telah kita buat pada Button, EditText dan checkbox
        this.bproses = (Button)findViewById(R.id.button);
        this.editText1 = (EditText)findViewById(R.id.editText);
        this.editText2 = (EditText)findViewById(R.id.editText2);
        this.editText3 = (EditText)findViewById(R.id.editText3);
        this.editText4 = (EditText)findViewById(R.id.editText4);
        this.ch = (CheckBox)findViewById(R.id.checkBox);
        this.ch2 = (CheckBox)findViewById(R.id.checkBox2);

        this.bproses.setOnClickListener(this); // memberikan listener kepada button untuk menangkap event pada saat button diklik
    }

    @Override
    public void onClick(View v) {
        double total;
        switch (v.getId()){
            case R.id.button:
                total = Double.parseDouble(this.editText1.getText().toString());
                total = total - ((total * Double.parseDouble(this.editText2.getText().toString())) / 100);
                if(this.ch.isChecked()) // mengembalikan nilai true atau false
                    total = total - 1000;
                if(this.ch2.isChecked())// mengembalikan nilai true atau false
                    total = total - (( total * 2 ) / 100);
                else
                    total = total - (( total * 1 ) / 100);
                total = total + ((total * Double.parseDouble(this.editText3.getText().toString())) / 100); // untuk mengkonversi tipe variable string menjadi double
                this.editText4.setText(String.valueOf(total)); // agar nilai dari variable dapat di taruh pada edit text yang digunakan untuk menyimpan Grand Total
        }
    }
}
