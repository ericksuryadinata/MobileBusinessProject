package com.proesd.erick.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity; //untuk mengenali extends list activity
import android.widget.AdapterView;
import android.widget.ArrayAdapter; // untuk widget array adapter
import android.widget.ListView; // untuk widget Listview
import android.widget.TextView; // untuk widget text view
import android.view.View; // untuk event




public class MainActivity extends AppCompatActivity {

    TextView seleksi; //mendeskripsikan seleksi dengan format textview
    ListView listView1;

    String[] pilihan = {
            "Merbabu", "Merapi", "Lawu", "Rinjani",
            "Sumbing","Sindoro", "Krakatau", "Selat Sunda",
            "Selat Bali","Selat Malaka","Kalimantan",
            "Sulawesi", "Jawa" };
/*Array String[] pilihan merupakan kumpulan data String yang akan ditampilkan pada ListView. Array ini dimasukkan ke dalam object dari ArrayAdapter yang bernama adapter. Adapter ini merupakan adapter sederhana yang hanya menampilkan sebuah TextView pada item ListView. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                seleksi = (TextView) findViewById(R.id.seleksi);
        // Code seleksi di dapat dari Textview seleksi yang di deklarasikan di atas tadi seleksi = (TextView) findViewById(R.id.yangDipilih); membuat object dari class Textview dengan menginisiasi object tersebut dengan ListView yang kita buat pada file layout.xml.
        listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, pilihan));


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                seleksi.setText(pilihan[position]);
                //maksud dari coding ini adalah ketika kalian mengklik atau memilih data yang ada di listview, maka data tersebut akan tampil degan format textview
            }
        });

    }
}
