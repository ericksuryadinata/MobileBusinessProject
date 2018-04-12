package com.proesd.bab10_listview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView seleksi; //mendeskripsikan seleksi dengan format textview

    String[] pilihan = {
            "Merbabu", "Merapi", "Lawu", "Rinjani",
            "Sumbing","Sindoro", "Krakatau", "Selat Sunda",
            "Selat Bali","Selat Malaka","Kalimantan",
            "Sulawesi", "Jawa" };
    private ArrayAdapter<String> listAdapter;
            /*Array String[] pilihan merupakan kumpulan data String yang akan ditampilkan pada ListView.
            Array ini dimasukkan ke dalam object dari ArrayAdapter yang bernama adapter.
            Adapter ini merupakan adapter sederhana yang hanya menampilkan sebuah TextView pada item ListView. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pilihan));
        seleksi = (TextView) findViewById(R.id.yangDipilih);
        /* Code seleksi di dapat dari Textview seleksi yang di deklarasikan
        di atas tadi seleksi = (TextView) findViewById(R.id.yangDipilih);
        membuat object dari class Textview dengan menginisiasi object tersebut dengan ListView yang kita buat pada file layout.xml.*/
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        seleksi.setText(pilihan[position]);
        /*maksud dari coding ini adalah ketika kalian mengklik atau memilih data
        yang ada di listview, maka data tersebut akan tampil degan format textview */
    }

    public void setListAdapter(ArrayAdapter<String> listAdapter) {
        this.listAdapter = listAdapter;
    }
}
