package com.proesd.bab8_imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    static boolean orang_a=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ganti(View view)
    {
        image = (ImageView) findViewById(R.id.imageView1); //kode ini digunakan untuk menghubungkan antara activity_main.xml dengan MainActivity.java sehingga kita bisa memanipulasi image yang berada pada xml nya.
        if(orang_a==true)
        {
            image.setImageResource(R.drawable.orang_a); //kode ini digunakan untuk mengganti resource image pada imageView1, kita tinggal memanggil image lain yang ada pada folder drawable proect kita jika ingin mengganti image yang ada. .
            orang_a=false;
        }
        else if(orang_a==false)
        {
            image.setImageResource(R.drawable.orang_b);
            orang_a=true;
        }
    }
}
