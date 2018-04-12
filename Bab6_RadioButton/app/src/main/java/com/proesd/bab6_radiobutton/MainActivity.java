package com.proesd.bab6_radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Deklarasi Radio Group, Radio Button, Button
    private RadioGroup radioGroup;
    private RadioButton radio_jenis;
    private Button btn_tampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //akses fungsi addListenerOnButton
        addListenerOnButton();
    }

    //Fungsi addListenerOnButton
    public void addListenerOnButton(){
        //Menghubungkan Radio Group dengan radio group di activity main
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        //Menghubungkan button dengan button di activity main
        btn_tampil = (Button)findViewById(R.id.tampil);
        //memberikan event pada button
        btn_tampil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int pilihId  =  radioGroup.getCheckedRadioButtonId();
                radio_jenis = (RadioButton)findViewById(pilihId);
                //Menampilkan Toast dengan radio button yang di pilih
                Toast.makeText(MainActivity.this, radio_jenis.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
