package com.proesd.bab3_edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.edittext);
        final Button buttonTampil = (Button) findViewById(R.id.button);

        // memberikan click listener untuk buttonTampil
        buttonTampil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /* gunakan fungsi getText() untuk memperoleh
           	     * data dari EditText
           	     */
                String str = editText.getText().toString();
                // menampilkan teks yang diambil dari editText di widget Toast
                Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show();
            }
        });
    }
}
