package com.proesd.sqltest;

/**
 * Created by erick on 05/04/18.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class HalamanUtama extends Activity implements OnClickListener {

    DatabaseManager dm;
    Button tanggal, btFoto, btSimpan, btUbah, btLihat, btHapus, tahunLulus, tahunWisuda;
    EditText edNbi, edNama, edAlamat,edIpk, edJumlahSaudara;
    EditText edFoto;
    TextView edPrint2,dbtxNbi,dbtxNama,dbtxAlamat,dbtxJurusan,dbtxHobi,dbtxTanggal,dbtxNamaFile,dbtxIpk, dbtxTahunLulus, dbtxTahunWisuda, dbtxJumlahSaudara;
    CheckBox cbRenang, cbBalapan, cbBola, cbSilat, cbLukis;
    RadioGroup rgKelamin;
    RadioButton rbLaki, rbPerempuan, rbTidakTahu;
    Spinner spJurusan;
    //	ImageButton imageButton1;
//	ImageView imageView1;
    TableLayout tabel4data;
    final Context context=this;

    String jurusan[] = {"Teknik Informatika"
            ,"Teknik Mesin"
            ,"Teknik Industri"
            ,"Teknik Elektro"
            ,"Teknik Sipil"};

    String jurusanTerpilih = "";
    String kelamin = "";
    String hobi = "";
    Uri nav, uripath;
    String FotoPath;

    //menset tanggal date picker
    DateFormat fmtDate = DateFormat.getDateInstance();
    DateFormat ll = DateFormat.getDateInstance();
    DateFormat lw = DateFormat.getDateInstance();
    final Calendar date = Calendar.getInstance();
    final Calendar tl = Calendar.getInstance();
    final Calendar tw = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, month);
            date.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }

    };

    DatePickerDialog.OnDateSetListener dl = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            tl.set(Calendar.YEAR, year);
            tl.set(Calendar.MONTH, month);
            tl.set(Calendar.DAY_OF_MONTH, day);
            updateLabelLulus();
        }

    };

    DatePickerDialog.OnDateSetListener dw = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            tw.set(Calendar.YEAR, year);
            tw.set(Calendar.MONTH, month);
            tw.set(Calendar.DAY_OF_MONTH, day);
            updateLabelWisuda();
        }

    };




    public void cekHobi() {
        if(cbBalapan.isChecked())
            hobi += "Balapan, ";
        if(cbBola.isChecked())
            hobi += "Sepak Bola, ";
        if(cbRenang.isChecked())
            hobi += "Renang, ";
        if(cbSilat.isChecked())
            hobi += "Silat, ";
        if(cbLukis.isChecked())
            hobi += "Melukis";
    }

    public void cekRadioKelamin() {
        int rgKelaminId = rgKelamin.getCheckedRadioButtonId();

        if(rbLaki.getId() == rgKelaminId)
            kelamin = rbLaki.getText().toString();
        if(rbPerempuan.getId() == rgKelaminId)
            kelamin = rbPerempuan.getText().toString();
        if(rbTidakTahu.getId() == rgKelaminId)
            kelamin = rbTidakTahu.getText().toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        dm = new DatabaseManager(this);

        tabel4data = (TableLayout) findViewById(R.id.tabel4data);
        edNbi = (EditText)findViewById(R.id.edNbi);
        edNama = (EditText)findViewById(R.id.edNama);
        edAlamat = (EditText)findViewById(R.id.edAlamat);
        edFoto = (EditText)findViewById(R.id.edFoto);
        cbRenang = (CheckBox)findViewById(R.id.cbRenang);
        cbBalapan = (CheckBox)findViewById(R.id.cbBalapan);
        cbBola = (CheckBox)findViewById(R.id.cbBola);
        cbSilat = (CheckBox)findViewById(R.id.cbSilat);
        cbLukis = (CheckBox)findViewById(R.id.cbLukis);
        rgKelamin = (RadioGroup)findViewById(R.id.radioGroup1);
        rbLaki = (RadioButton)findViewById(R.id.rbLaki);
        rbPerempuan = (RadioButton)findViewById(R.id.rbPerempuan);
        rbTidakTahu = (RadioButton)findViewById(R.id.rbTidakahu);
        spJurusan = (Spinner)findViewById(R.id.spJurusan);
        edIpk = (EditText) findViewById(R.id.edIPK);
        edJumlahSaudara = (EditText) findViewById(R.id.edJumlahSaudara);
        tanggal = (Button) findViewById(R.id.tanggal);
        tahunLulus = (Button) findViewById(R.id.tanggalLulus);
        tahunWisuda = (Button) findViewById(R.id.tanggalWisuda);
        tahunLulus.setOnClickListener((OnClickListener) this);
        tahunWisuda.setOnClickListener((OnClickListener)this);
        tanggal.setOnClickListener((OnClickListener) this);
        btFoto = (Button) findViewById(R.id.btFoto);
        btFoto.setOnClickListener((OnClickListener) this);
        btFoto.setBackgroundColor(Color.GRAY);
        btFoto.setText("Pilih Foto");
        dbtxNbi = (TextView) findViewById(R.id.dbtxNbi);
        dbtxNama = (TextView) findViewById(R.id.dbtxNama);
        dbtxAlamat = (TextView) findViewById(R.id.dbtxAlamat);
        dbtxJurusan = (TextView) findViewById(R.id.dbtxJurusan);
        dbtxHobi = (TextView) findViewById(R.id.dbtxHobi);
        dbtxTanggal = (TextView) findViewById(R.id.dbtxTanggal);
        dbtxNamaFile=(TextView)findViewById(R.id.dbtxNamaFile);
        btSimpan = (Button) findViewById(R.id.btSimpan);
        btSimpan.setOnClickListener((OnClickListener) this);
        btUbah = (Button) findViewById(R.id.btUbah);
        btUbah.setOnClickListener((OnClickListener) this);
        btLihat = (Button) findViewById(R.id.btLihat);
        btLihat.setOnClickListener((OnClickListener) this);
        btHapus = (Button) findViewById(R.id.btHapus);
        btHapus.setOnClickListener((OnClickListener) this);
        updateLabel();
        updateTable();
        updateLabelWisuda();
        updateLabelLulus();

        btSimpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                simpanData();
                kosongkanField();
            }
        });

        btUbah.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ubahData();
                kosongkanField();
            }
        });

        btLihat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                lihatData();
                //		cekkelamin();
                //ambilBaris();
            }
        });

        btHapus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                hapusData();
                kosongkanField();
            }
        });

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,jurusan);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(aa);
        spJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long arg3) {
                jurusanTerpilih = jurusan[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                jurusanTerpilih = "- Pilih Jurusan -";
            }

        });

//        btReset.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				direset();
//			}
//        });
//
//
//        btProses.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				proses();
//			}
//		});

        tanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                settingTanggal();
            }
        });

        tahunLulus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                settingTahunLulus();
            }
        });

        tahunWisuda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                settingTahunWisuda();
            }
        });

        btFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                pilihFoto();
            }
        });

    }

    public void ubahData() {
        // TODO Auto-generated method stub
        cekRadioKelamin();
        cekHobi();
        dm.updateBaris(edNbi.getText().toString(),
                edNama.getText().toString(),
                edAlamat.getText().toString(),
                jurusanTerpilih,
                kelamin,
                hobi,
                tanggal.getText().toString(),
                edFoto.getText().toString(),
                edIpk.getText().toString(),
                tahunLulus.getText().toString(),
                tahunWisuda.getText().toString(),
                edJumlahSaudara.getText().toString());
        Toast.makeText(getApplicationContext(),edNama.getText().toString() + ", Berhasil diubah",
                Toast.LENGTH_SHORT).show();
        updateTable();
        kosongkanField();

    }

    @SuppressWarnings("deprecation")
    public void lihatData() {
        // TODO Auto-generated method stub
        try {
            ArrayList<Object> baris;
            baris =
                    dm.ambilBaris(Long.parseLong(edNbi.getText().toString()));
            edNama.setText((String) baris.get(1));
            edAlamat.setText((String) baris.get(2));
            String compareValue = ((String) baris.get(3));
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,jurusan);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spJurusan.setAdapter(aa);
            if (!compareValue.equals(null)) {
                int spinnerPosition = aa.getPosition(compareValue);
                spJurusan.setSelection(spinnerPosition);
            }

            String M = ((String) baris.get(4));
            if(M.equalsIgnoreCase("Laki-laki"))
            {
                rbLaki.setChecked(true);
            }
            else if(M.equalsIgnoreCase("Perempuan")){

                rbPerempuan.setChecked(true);
            }
            else if(M.equalsIgnoreCase("TidakTahu"))
            {
                rbTidakTahu.setChecked(true);
            }

            String N = ((String) baris.get(5));
            if(N.equalsIgnoreCase("Renang, "))
            {
                cbRenang.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Renang, Sepak Bola, ")){

                cbRenang.setChecked(true);
                cbBola.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Renang, Balapan, ")){

                cbRenang.setChecked(true);
                cbBalapan.setChecked(true);

            }
            else if(N.equalsIgnoreCase("Renang, Silat, ")){

                cbRenang.setChecked(true);
                cbSilat.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Renang, Melukis")){

                cbRenang.setChecked(true);
                cbLukis.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Sepak Bola, ")){

                cbBola.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Sepak Bola, Renang, ")){

                cbBola.setChecked(true);
                cbRenang.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Sepak Bola, Balapan, ")){

                cbBola.setChecked(true);
                cbBalapan.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Sepak Bola, Silat, ")){

                cbBola.setChecked(true);
                cbSilat.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Sepak Bola, Melukis")){

                cbBola.setChecked(true);
                cbLukis.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Balapan, "))
            {
                cbBalapan.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Balapan, Renang, "))
            {
                cbBalapan.setChecked(true);
                cbRenang.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Balapan, Sepak Bola, "))
            {
                cbBalapan.setChecked(true);
                cbBola.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Balapan, Silat, "))
            {
                cbBalapan.setChecked(true);
                cbSilat.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Balapan, Melukis"))
            {
                cbBalapan.setChecked(true);
                cbLukis.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Silat, "))
            {
                cbSilat.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Silat, Renang, "))
            {
                cbSilat.setChecked(true);
                cbRenang.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Silat, Sepak Bola, "))
            {
                cbSilat.setChecked(true);
                cbBola.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Silat, Balapan, "))
            {
                cbSilat.setChecked(true);
                cbBalapan.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Silat, MElukis"))
            {
                cbSilat.setChecked(true);
                cbLukis.setChecked(true);
            }
            else if(N.equalsIgnoreCase("Melukis"))
            {
                cbLukis.setChecked(true);
            }

            tanggal.setText((String)baris.get(6));
            String convertedPath = ((String)baris.get(7));
            uripath = Uri.fromFile(new File(convertedPath));
            try{
                Bitmap bm = BitmapFactory.decodeStream(
                        getContentResolver().openInputStream(uripath));
                BitmapDrawable bdrawable = new BitmapDrawable(getResources(),bm);
                btFoto.setBackgroundDrawable(bdrawable);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            edFoto.setText((String)baris.get(7));
            tahunLulus.setText((String)baris.get(8));
            tahunWisuda.setText((String)baris.get(9));
            edIpk.setText((String)baris.get(10));
            edJumlahSaudara.setText((String)baris.get(11));
        }

        catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e("DB ERROR", e.toString());
            Toast.makeText(getApplicationContext(), e.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void hapusData() {
        // TODO Auto-generated method stub
        dm.deleteBaris(Long.parseLong(edNbi.getText().toString()));
        Toast.makeText(getApplicationContext(),edNama.getText().toString() + ", Berhasil dihapus",
                Toast.LENGTH_SHORT).show();
        updateTable();
    }

    public void simpanData() {
        // TODO Auto-generated method stub
        try {
            cekRadioKelamin();
            cekHobi();
            dm.TambahRow(edNbi.getText().toString(), edNama.getText().toString(), edAlamat.getText().toString(),
                    jurusanTerpilih, kelamin, hobi, tanggal.getText().toString(), edFoto.getText().toString(),
                    edIpk.getText().toString(),
                    tahunLulus.getText().toString(),
                    tahunWisuda.getText().toString(),
                    edJumlahSaudara.getText().toString());
            Toast.makeText(getApplicationContext(),edNama.getText().toString() + ", Berhasil disimpan", Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Gagal disimpan, " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @SuppressWarnings("deprecation")
    public void kosongkanField() {
        // TODO Auto-generated method stub
        edNbi.setText("");
        edNama.setText("");
        //edPrint2.setText("");
        edAlamat.setText("");
        cbBalapan.setChecked(false);
        cbBola.setChecked(false);
        cbRenang.setChecked(false);
        cbSilat.setChecked(false);
        cbLukis.setChecked(false);
        hobi = ("");
        rbLaki.setChecked(false);
        rbPerempuan.setChecked(false);
        rbTidakTahu.setChecked(false);
        kelamin = ("");

        btFoto.setText("Pilih Foto");
        btFoto.setBackgroundDrawable(null);
        btFoto.setBackgroundColor(Color.GRAY);
//		imageView1.setImageDrawable(null);
//		tanggal.setText("");
//      edJurusan.setText("");
//      edKelamin.setText("");
//      edHobi.setText("");
//		jurusanTerpilih=("");
        updateLabel();
        updateLabelLulus();
        updateLabelWisuda();
        edFoto.setText("");
        edIpk.setText("");
        edJumlahSaudara.setText("");
//		edLongitude.setText("");
//		edLatitude.setText("");
    }

    public void updateTable() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        //menghapus data yang ada
        while (tabel4data.getChildCount() > 1) {
            tabel4data.removeViewAt(1);
        }
//		double aa = tabel4data.getChildCount();
//		String a = String.valueOf(aa);
//		Toast.makeText(getBaseContext(), "tabel data child : " + a,
//				Toast.LENGTH_SHORT).show();

        //ambil data dari sqlite
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();//

        //menampilkan data dari array  sqlite tadi
        //ke layar emulator/hp
        for (int posisi = 0; posisi < data.size(); posisi++) {

            //buat baris baru <TR>
            TableRow tabelBaris = new TableRow(this);
            ArrayList<Object> baris = data.get(posisi);

            //buat <td> dalam hal ini data id
            TextView nbiTxt = new TextView(this);
            nbiTxt.setText(baris.get(0).toString());
            tabelBaris.addView(nbiTxt);

            //buat <td> dalam hal ini data nama
            TextView namaTxt = new TextView(this);
            namaTxt.setText(baris.get(1).toString());
            tabelBaris.addView(namaTxt);

            //buat <td> dalam hal ini data hobi
            TextView alamatTxt = new TextView(this);
            alamatTxt.setText(baris.get(2).toString());
            tabelBaris.addView(alamatTxt);

            TextView jurusanTxt = new TextView(this);
            jurusanTxt.setText(baris.get(3).toString());
            tabelBaris.addView(jurusanTxt);

            TextView kelaminTxt = new TextView(this);
            kelaminTxt.setText(baris.get(4).toString());
            tabelBaris.addView(kelaminTxt);

            TextView hobiTxt = new TextView(this);
            hobiTxt.setText(baris.get(5).toString());
            tabelBaris.addView(hobiTxt);

            TextView tanggalTxt = new TextView(this);
            tanggalTxt.setText(baris.get(6).toString());
            tabelBaris.addView(tanggalTxt);

            TextView fotoTxt = new TextView(this);
            fotoTxt.setText(baris.get(7).toString());
            tabelBaris.addView(fotoTxt);

            TextView tahunLulusTxt = new TextView(this);
            tahunLulusTxt.setText(baris.get(8).toString());
            tabelBaris.addView(tahunLulusTxt);

            TextView tahunWisudaTxt = new TextView(this);
            tahunWisudaTxt.setText(baris.get(9).toString());
            tabelBaris.addView(tahunWisudaTxt);

            TextView ipkTxt = new TextView(this);
            ipkTxt.setText(baris.get(10).toString());
            tabelBaris.addView(ipkTxt);

            TextView jumlahSaudaraTxt = new TextView(this);
            jumlahSaudaraTxt.setText(baris.get(11).toString());
            tabelBaris.addView(jumlahSaudaraTxt);


//			TextView fotoTxt = new TextView(this);
//			fotoTxt.setText(baris.get(7).toString());
//			tabelBaris.addView(fotoTxt);

            //buat akhir baris </TR>
            tabel4data.addView(tabelBaris);

        }
    }

    public void pilihFoto() {
        // TODO Auto-generated method stub
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, 0);
    }
    //update tanggal
    public void updateLabel() {
        tanggal.setText(
                fmtDate.format(date.getTime())
        );
    }

    public void updateLabelLulus(){
        tahunLulus.setText(
                ll.format(tl.getTime())
        );
    }

    public void updateLabelWisuda(){
        tahunWisuda.setText(
                lw.format(tw.getTime())
        );
    }
    //mendapatkan tanggal
    public void settingTanggal() {
        new DatePickerDialog(HalamanUtama.this, d,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void settingTahunLulus() {
        new DatePickerDialog(HalamanUtama.this, dl,
                tl.get(Calendar.YEAR),
                tl.get(Calendar.MONTH),
                tl.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void settingTahunWisuda() {
        new DatePickerDialog(HalamanUtama.this, dw,
                tw.get(Calendar.YEAR),
                tw.get(Calendar.MONTH),
                tw.get(Calendar.DAY_OF_MONTH)).show();
    }


    public static Bitmap convertBitmap(String path) {
        Bitmap bitmap = null;
        BitmapFactory.Options ourOptions = new BitmapFactory.Options();
        ourOptions.inDither = false;
        ourOptions.inPurgeable = true;
        ourOptions.inInputShareable = true;
        ourOptions.inTempStorage = new byte[32 * 1024];
        File file = new File(path);
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (fs != null) {
                bitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(), null,
                        ourOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    public static Bitmap decodeBitmapPath(String path, int width, int height) {
        final BitmapFactory.Options ourOption = new BitmapFactory.Options();
        ourOption.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, ourOption);
        ourOption.inSampleSize = calculateInSampleSize(ourOption, width, height);
        // Decode bitmap with inSampleSize set
        ourOption.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(path, ourOption);
        return bmp;
    }

    public static int calculateInSampleSize(BitmapFactory.Options ourOption,
                                            int imageWidth, int imageHeight) {
        final int height = ourOption.outHeight;
        final int width = ourOption.outWidth;
        int inSampleSize = 1;
        if (height > imageHeight || width > imageWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) imageHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) imageWidth);
            }
        }
        return inSampleSize;
    }

    @SuppressWarnings("deprecation")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            nav = data.getData();
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = managedQuery(nav, projection, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path= cursor.getString(column_index);
            Toast.makeText(getApplicationContext(), "Foto : "+path, Toast.LENGTH_SHORT).show();
            //String namafile = path.substring(path.lastIndexOf("/")+1);
            edFoto.setText(path);

            String uuid = UUID.randomUUID().toString();
            File tempFile = new File(this.getFilesDir().getAbsolutePath(), uuid);
            // Copy Uri contents into temp File.
            try {
                tempFile.createNewFile();
                CopyAndClose(
                        this.getContentResolver().openInputStream(
                                data.getData()), new FileOutputStream(tempFile));
            } catch (IOException e) {
                // Log Error
            }

            FotoPath = tempFile.getAbsolutePath().toString();

            BitmapFactory.Options ourOptions=null;
            ourOptions = new BitmapFactory.Options();
            ourOptions.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeFile(FotoPath, ourOptions);

            BitmapDrawable bdrawable = new BitmapDrawable(getResources(),bitmap);

            btFoto.setBackgroundDrawable(bdrawable);
        }

    }

    public String CopyAndClose(InputStream sourceLocation,
                               FileOutputStream targetLocation) {

        InputStream inStream = null;
        OutputStream outStream = null;

        try {

            inStream = sourceLocation;
            outStream = targetLocation;

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }
}