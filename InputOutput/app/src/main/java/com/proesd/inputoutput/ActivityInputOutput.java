package com.proesd.inputoutput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityInputOutput extends AppCompatActivity {

    Button prosesBtn;
    TextView tampilText;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_output);

        prosesBtn = (Button)findViewById(R.id.prosesBtn);
        inputText = (EditText)findViewById(R.id.inputText);
        tampilText = (TextView)findViewById(R.id.textView);
        prosesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tampilText.setText(inputText.getText());
                Toast.makeText(getApplicationContext(),"YEAHH",Toast.LENGTH_LONG).show();
            }
        });
    }
}
