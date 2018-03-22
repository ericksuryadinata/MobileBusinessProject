package com.proesd.inputoutput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityInputOutput2 extends AppCompatActivity {

    Button prosesBtn;
    TextView nbiView, namaView, alamatView;
    EditText inputNbi, inputNama, inputAlamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_output2);

        prosesBtn = (Button)findViewById(R.id.prosesBtn);
        inputNbi  = (EditText) findViewById(R.id.inputNbi);
        inputNama  = (EditText) findViewById(R.id.inputNama);
        inputAlamat  = (EditText) findViewById(R.id.inputAlamat);
        nbiView = (TextView)findViewById(R.id.nbiView);
        namaView = (TextView)findViewById(R.id.namaView);
        alamatView = (TextView)findViewById(R.id.alamatView);
        prosesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nbiView.setText(inputNbi.getText());
                namaView.setText(inputNama.getText());
                alamatView.setText(inputAlamat.getText());

            }
        });


    }
}
