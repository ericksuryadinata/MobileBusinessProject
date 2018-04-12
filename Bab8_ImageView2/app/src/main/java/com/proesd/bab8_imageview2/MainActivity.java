package com.proesd.bab8_imageview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void kecil (View view)
    {
        image = (ImageView) findViewById(R.id.imageView1); //kode ini digunakan untuk menghubungkan antara activity_main.xml dengan MainActivity.java sehingga kita bisa memanipulasi image yang berada pada xml nya.
        image.getLayoutParams().height = 50; //merubah ukuran image
        image.requestLayout(); //untuk merefresh layout agar layout berubah

        Toast.makeText(this, "kecil", Toast.LENGTH_LONG).show(); //menampilkan alert
    }

    public void sedang (View view)
    {
        image = (ImageView) findViewById(R.id.imageView1); //kode ini digunakan untuk menghubungkan antara activity_main.xml dengan MainActivity.java sehingga kita bisa memanipulasi image yang berada pada xml nya.
        image.getLayoutParams().height = 300; //merubah ukuran image
        image.requestLayout(); //untuk merefresh layout agar layout berubah

        Toast.makeText(this, "sedang", Toast.LENGTH_LONG).show(); //menampilkan alert
    }

    public void besar (View view)
    {
        image = (ImageView) findViewById(R.id.imageView1); //kode ini digunakan untuk menghubungkan antara activity_main.xml dengan MainActivity.java sehingga kita bisa memanipulasi image yang berada pada xml nya.
        image.getLayoutParams().height = 800; //untuk merefresh layot
        image.requestLayout(); //untuk merefresh layout agar layoutberubah

        Toast.makeText(this, "besar", Toast.LENGTH_LONG).show(); //menampilkan alert
    }
}
