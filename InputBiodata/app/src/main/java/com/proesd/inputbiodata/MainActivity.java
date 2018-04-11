package com.proesd.inputbiodata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btProses,btReset;
    EditText edNbi,edNama,edAlamat,edprintNbi,edprintNama,edprintAlamat,
            edprintJurusan,edprintJeniskelamin,edprintHobi;
    TextView nbi,nama,alamat,prodi,jeniskelamin,hobi,printnbi,printnama,
            printalamat,printprodi,printjeniskelamin,printhobi;
    RadioGroup rgKelamin;
    Spinner spJurusan;
    String[] piljurusan= {"Teknik Industri",
            "Teknik Mesin",
            "Teknik Sipil",
            "Teknik Elektro",
            "Teknik Informatika"};
    String jurusan ="";
    CheckBox cbBasket,cbTraveling,cbMakan,cbProgramming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nbi = (TextView) findViewById(R.id.nbi);
        nama = (TextView) findViewById(R.id.nama);
        alamat = (TextView) findViewById(R.id.alamat);
        prodi = (TextView) findViewById(R.id.prodi);
        jeniskelamin = (TextView) findViewById(R.id.jeniskelamin);
        hobi = (TextView) findViewById(R.id.hobi);
        printnbi = (TextView) findViewById(R.id.printnbi);
        printnama = (TextView) findViewById(R.id.printnama);
        printalamat = (TextView) findViewById(R.id.printalamat);
        printprodi = (TextView) findViewById(R.id.printprodi);
        printjeniskelamin = (TextView) findViewById(R.id.printjeniskelamin);
        printhobi = (TextView) findViewById(R.id.printhobi);
        edNbi = (EditText) findViewById(R.id.edNbi);
        edNama = (EditText) findViewById(R.id.edNama);
        edAlamat = (EditText) findViewById(R.id.edAlamat);
        edprintNbi = (EditText) findViewById(R.id.edprintNbi);
        edprintNama = (EditText) findViewById(R.id.edprintNama);
        edprintAlamat = (EditText) findViewById(R.id.edprintAlamat);
        edprintJurusan = (EditText) findViewById(R.id.edprintJurusan);
        edprintJeniskelamin = (EditText) findViewById(R.id.edprintJeniskelamin);
        edprintHobi = (EditText) findViewById(R.id.edprintHobi);
        spJurusan = (Spinner) findViewById(R.id.spJurusan);
        rgKelamin = (RadioGroup) findViewById(R.id.rgKelamin);
        cbTraveling = (CheckBox) findViewById(R.id.cbTraveling);
        cbBasket = (CheckBox) findViewById(R.id.cbBasket);
        cbMakan = (CheckBox) findViewById(R.id.cbMakan);
        cbProgramming = (CheckBox) findViewById(R.id.cbProgramming);
        btProses = (Button) findViewById(R.id.btProses);
        btReset = (Button) findViewById(R.id.btReset);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, piljurusan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(adapter);

        spJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, View view, int posisi, long id)
            {
                jurusan = piljurusan[posisi];

            }

            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });

        btProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edprintNbi.setText(edNbi.getText());
                edprintNama.setText(edNama.getText());
                edprintAlamat.setText(edAlamat.getText());
                edprintJurusan.setText(jurusan);

                int pilId = rgKelamin .getCheckedRadioButtonId();
                RadioButton rbKelamin = (RadioButton) findViewById(pilId);
                edprintJeniskelamin.setText(rbKelamin.getText());

                String hobi ="";
                if (cbProgramming.isChecked()) {
                    hobi = hobi+" " + cbProgramming.getText();
                }
                if (cbMakan.isChecked()) {
                    hobi = hobi+" " + cbMakan.getText();
                }
                if (cbTraveling.isChecked()) {
                    hobi = hobi +" "+ cbTraveling.getText();
                }
                if (cbBasket.isChecked()) {
                    hobi = hobi +" "+ cbBasket.getText();
                }

                edprintHobi.setText(hobi);

                Toast.makeText(getBaseContext(),"Berhasil",
                        Toast.LENGTH_LONG).show();


            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edNbi.setText("");
                edNama.setText("");
                edAlamat.setText("");
                cbBasket.setChecked(false);
                cbTraveling.setChecked(false);
                cbMakan.setChecked(false);
                cbProgramming.setChecked(false);
                edprintNama.setText("");
                edprintAlamat.setText("");
                edprintHobi.setText("");
                edprintNbi.setText("");
                edprintJeniskelamin.setText("");
                edprintJurusan.setText("");


            }
        });
    }
}
