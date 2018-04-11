package com.proesd.sqltest;

/**
 * Created by erick on 05/04/18.
 */
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseManager {

    private static final String ROW_NBI = "nbi";
    private static final String ROW_NAMA = "nama";
    private static final String ROW_ALAMAT = "alamat";
    private static final String ROW_KELAMIN = "kelamin";
    private static final String ROW_JURUSAN = "jurusan";
    private static final String ROW_HOBI = "hobi";
    private static final String ROW_TANGGAL = "tanggal";
    private static final String ROW_NAMAFILE = "namafile";
    private static final String ROW_TAHUN_LULUS = "tahunlulus";
    private static final String ROW_TAHUN_WISUDA = "tahunwisuda";
    private static final String ROW_IPK = "ipk";
    private static final String ROW_JUMLAH_SAUDARA = "jumlahsaudara";


    private static final String NAMA_DB = "databasebiodata";// nama database
    private static final String NAMA_TABEL = "biodata";// nama tabel
    private static final int DB_VERSION =4;
    //CREATE TABLE hobiku (_id integer PRIMARY KEY autoincrement, nama text,hobi text)
    private static final String CREATE_TABLE = "create table "+NAMA_TABEL+" ("+ROW_NBI+" integer PRIMARY KEY, "+ROW_NAMA+" text,"
            +ROW_ALAMAT+" text,"+ROW_KELAMIN+" text,"+ROW_JURUSAN+" text,"+ROW_HOBI+" text,"
            +ROW_TANGGAL+" text,"+ROW_NAMAFILE+" text,"+ROW_TAHUN_LULUS+" text,"+ROW_TAHUN_WISUDA+
            " text,"+ROW_IPK+" double,"+ROW_JUMLAH_SAUDARA+" integer)";

    private final Context context;
    private DatabaseOpenHelper dbHelper;
    private SQLiteDatabase db;

    //jika db nya ada di baca
    //jika db nya belum ada di buat
    //buat database yg di baca dan ditulis (insert update, delete)
    public DatabaseManager(Context ctx) {
        this.context = ctx;
        dbHelper = new DatabaseOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    private static class DatabaseOpenHelper extends SQLiteOpenHelper {
        // disini mulai dibuat database dengan nama itu dan versi itu
        public DatabaseOpenHelper(Context context) {
            super(context, NAMA_DB, null, DB_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            //disini untuk mengeksekusi perintah membuat table
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            // TODO Auto-generated method stub"DROP TABLE IF EXISTS titles"
            // disini tempat untuk mengeksekusi jika tabel ada tapi versinya beda
            //jika versinya sekarang lebih tinggi maka hapus yang lama
            //jika versinya lebih rendah...(maka ditolak...seharusnya)
            db.execSQL("DROP TABLE IF EXISTS "+NAMA_TABEL);
            onCreate(db);
        }
    }

    public void close() {
        dbHelper.close();
    }

    public void TambahRow(String nbi, String nama, String alamat, String kelamin, String jurusan, String hobi,
                          String tanggal, String namafile, String ipk, String tahunlulus, String tahunwisuda, String jumlahsaudara) {
        //masukkan entrian nama dan hobi kedalam array
        ContentValues values = new ContentValues();
        values.put(ROW_NBI, nbi);
        values.put(ROW_NAMA, nama);
        values.put(ROW_ALAMAT, alamat);
        values.put(ROW_KELAMIN, kelamin);
        values.put(ROW_JURUSAN, jurusan);
        values.put(ROW_HOBI, hobi);
        values.put(ROW_TANGGAL, tanggal);
        values.put(ROW_NAMAFILE, namafile);
        values.put(ROW_TAHUN_LULUS, tahunlulus);
        values.put(ROW_TAHUN_WISUDA, tahunwisuda);
        values.put(ROW_IPK, ipk);
        values.put(ROW_JUMLAH_SAUDARA, jumlahsaudara);


        try {
            //	db.delete(NAMA_TABEL, null, null);

            //masukkan array ke dalam tabel
            db.insert(NAMA_TABEL, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }

    }

    public void deleteBaris(long idBaris){
        try {
            db.delete(NAMA_TABEL, ROW_NBI+"="+idBaris, null);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR ", e.toString());

        }
    }


    //disini mengambil semua data dari sqlite
    //dimasukkan ke dalam array
    public ArrayList<ArrayList<Object>> ambilSemuaBaris() {
        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            cur = db.query(NAMA_TABEL,
                    new String[] { ROW_NBI, ROW_NAMA, ROW_ALAMAT,ROW_KELAMIN, ROW_JURUSAN,
                            ROW_HOBI, ROW_TANGGAL, ROW_NAMAFILE, ROW_TAHUN_LULUS,ROW_TAHUN_WISUDA,
                            ROW_IPK,ROW_JUMLAH_SAUDARA}, null, null,	null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getLong(0));   // ambil data id , berikan ke array datalist
                    dataList.add(cur.getString(1)); // ambil data nama , berikan ke array datalist
                    dataList.add(cur.getString(2)); // ambil data hobi , berikan ke array datalist
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataList.add(cur.getString(6));
                    dataList.add(cur.getString(7));
                    dataList.add(cur.getString(8));
                    dataList.add(cur.getString(9));
                    dataList.add(cur.getString(10));
                    dataList.add(cur.getString(11));

                    dataArray.add(dataList);

                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DB ERROR", e.toString());
        }
        return dataArray;

    }

    public ArrayList<Object> ambilBaris(long rowId) {
        // TODO Auto-generated method stub
        //return null;
        ArrayList<Object> arrbaris = new ArrayList<Object>();
        Cursor cursor;
        try {
            cursor = db.query(NAMA_TABEL, new String[] { ROW_NBI, ROW_NAMA, ROW_ALAMAT,ROW_KELAMIN,
                            ROW_JURUSAN, ROW_HOBI, ROW_TANGGAL, ROW_NAMAFILE,ROW_TAHUN_LULUS,
                            ROW_TAHUN_WISUDA,ROW_IPK,ROW_JUMLAH_SAUDARA}, ROW_NBI + "=" + rowId, null, null, null,
                    null,null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    arrbaris.add(cursor.getLong(0));
                    arrbaris.add(cursor.getString(1));
                    arrbaris.add(cursor.getString(2));
                    arrbaris.add(cursor.getString(3));
                    arrbaris.add(cursor.getString(4));
                    arrbaris.add(cursor.getString(5));
                    arrbaris.add(cursor.getString(6));
                    arrbaris.add(cursor.getString(7));
                    arrbaris.add(cursor.getString(8));
                    arrbaris.add(cursor.getString(9));
                    arrbaris.add(cursor.getString(10));
                    arrbaris.add(cursor.getString(11));

                }
                while (cursor.moveToNext());
                String r = String.valueOf(arrbaris);
                Toast.makeText(context, " Berhasil " + r,
                        Toast.LENGTH_SHORT).show();

            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("error", e.toString());
            Toast.makeText(context, " Gagal " + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        return arrbaris;
    }

    public void updateBaris(String nbi, String nama, String alamat, String kelamin, String jurusan,
                            String hobi, String tanggal, String namafile,String ipk,
                            String tahunlulus, String tahunwisuda, String jumlahsaudara) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(ROW_NAMA, nama);
        cv.put(ROW_ALAMAT, alamat);
        cv.put(ROW_KELAMIN, kelamin);
        cv.put(ROW_JURUSAN, jurusan);
        cv.put(ROW_HOBI, hobi);
        cv.put(ROW_TANGGAL, tanggal);
        cv.put(ROW_NAMAFILE, namafile);
        cv.put(ROW_TAHUN_LULUS, tahunlulus);
        cv.put(ROW_TAHUN_WISUDA, tahunwisuda);
        cv.put(ROW_IPK, ipk);
        cv.put(ROW_JUMLAH_SAUDARA, jumlahsaudara);

        try {

            db.update(NAMA_TABEL, cv, ROW_NBI + "=" +nbi, null);

        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("DB ERROR ", e.toString());
        }
    }

//	public SQLiteDatabase getDb() {
//		return db;
//	}
//
//	public void setDb(SQLiteDatabase db) {
//		this.db = db;
//	}
}
