package com.proesd.bab10_listview2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyCustomAdapter dataAdapter = null; //mendekralasikan MyCustonAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener() { //variable button dengan nama myButton diberi event onclick listener

            @Override
            public void onClick(View v) { // saat button di klik
                StringBuffer responseText = new StringBuffer(); //membuat StringBuffer baru dengan nama responseText
                responseText.append("Pemrograman yang ingin anda pelajari adalah"); //isi dari responseText nanti
                //script digunakan untuk menampilkan tulisan Pemrograman yang ingin anda pelajari adalah
                ArrayList<Menupilihan> pilihanList = dataAdapter.pilihanList;
                for (int i = 0; i < pilihanList.size(); i++) {
                    Menupilihan pilihan = pilihanList.get(i);
                    if (pilihan.isSelected()) {
                        responseText.append("\n" + pilihan.getName());
                    }
                }
                //untuk menampilkan list yang di klik saja
                Toast.makeText(getApplicationContext(), responseText,
                        Toast.LENGTH_SHORT).show();

            }
        });
        displayListView(); // memanggil fungsi displayListView()
    }

    private void displayListView() { //pilihan list yang akan tampil ketika di jalankan
        ArrayList<Menupilihan> pilihanList = new ArrayList<Menupilihan>();
        Menupilihan pilihan = new Menupilihan("1", "Java", false); //mendeklarasikan pilihan di Menupilihan
        //untuk (“1”,”java”,false) adalah kondisi pertama ketika di jlankan. Jadi tidak ada data yang di pilih
        pilihanList.add(pilihan);
        pilihan = new Menupilihan("2", "C++", false);
        pilihanList.add(pilihan);
        pilihan = new Menupilihan("3", "Java Script", false);
        pilihanList.add(pilihan);
        pilihan = new Menupilihan("4", "PHP", false);
        pilihanList.add(pilihan);
        pilihan = new Menupilihan("5", "Phyton", false);
        pilihanList.add(pilihan);
        pilihan = new Menupilihan("6", "Perl", false);
        pilihanList.add(pilihan);
        pilihan = new Menupilihan("7", "Pascal", false);
        pilihanList.add(pilihan);

        // Buat array adapter dari data pilihanList
        dataAdapter = new MyCustomAdapter(this, R.layout.adapter,pilihanList);
        ListView listView = (ListView)findViewById(R.id.listView1);
        // menempatkan adapter di listview
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //ketika di klik, muncul toast yang berupa text (textview)
                Menupilihan pilihan = (Menupilihan) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + pilihan.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<Menupilihan> {

        private ArrayList<Menupilihan> pilihanList;
        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Menupilihan> pilihanList) {
            super(context, textViewResourceId, pilihanList);
            this.pilihanList = new ArrayList<Menupilihan>();
            this.pilihanList.addAll(pilihanList);
        }

        private class ViewHolder {
            TextView id;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.adapter, null);

                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Menupilihan pilihan = (Menupilihan) cb.getTag();
                        Toast.makeText(getApplicationContext(),"Clicked on Checkbox: " + cb.getText() + " is "+ cb.isChecked(), Toast.LENGTH_SHORT).show();
                        pilihan.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Menupilihan pilihan = pilihanList.get(position);
            holder.name.setText(pilihan.getName());
            holder.name.setChecked(pilihan.isSelected());
            holder.name.setTag(pilihan);

            return convertView;
        }
    }
}
