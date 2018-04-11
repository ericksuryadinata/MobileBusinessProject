package com.proesd.sqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button menuMahasiswa =(Button)findViewById(R.id.menuMahasiswa);
        menuMahasiswa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent =  new Intent(arg0.getContext(), HalamanUtama.class);
                startActivityForResult(myIntent, 0);
            }
        });


        Button menuKeluar =(Button)findViewById(R.id.menuKeluar);
        menuKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                keluar();
            }
        });

    }

    public void keluar() {
        // TODO Auto-generated method stub
        AlertDialog.Builder keluar = new AlertDialog.Builder (this);
        keluar.setTitle("Keluar");
        keluar.setMessage("Apakah anda ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        finish();
                    }
                }).setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.cancel();
            }}).show();
    }
}