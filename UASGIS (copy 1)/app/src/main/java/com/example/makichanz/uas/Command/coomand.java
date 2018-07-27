package com.example.makichanz.uas.Command;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.makichanz.uas.R;
import com.example.makichanz.uas.loopj.Request;
import com.example.makichanz.uas.loopj.Url;
import com.example.makichanz.uas.model.Lokasi;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class coomand {
    public void getAllData(final Response response){
        Request.get(Url.MAP, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Request.get(Url.MAP, null, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Gson gson = new Gson();
                        ArrayList<Lokasi> lokasis = new ArrayList<Lokasi>();
                        try {
                            JSONArray result = new JSONArray(new String(responseBody));
                            for(int i = 0; i<result.length();i++){
                                Lokasi lokasi = gson.fromJson(result.getJSONObject(i).toString(),Lokasi.class);
                                lokasis.add(lokasi);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        response.sukses(lokasis);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void insertData(String nama,LatLng my_location, final Response_insert response_insert) {
        RequestParams params = new RequestParams();
        params.put("nama_orang",nama);
        params.put("latitude",my_location.latitude);
        params.put("longitude",my_location.longitude);
        Request.post(Url.MAP, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                response_insert.sukses();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void insertDataLain(String nama,String lat, String lng, final Response_insert response_insert) {
        RequestParams params = new RequestParams();
        params.put("nama_orang",nama);
        params.put("latitude",Double.parseDouble(lat));
        params.put("longitude",Double.parseDouble(lat));
        Request.post(Url.MAP, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                response_insert.sukses();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void dialog_add_lokasi(Context context,double end_latitude, double end_longitude, final Response_dialog_add dialog_add){
        final Dialog dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tambah_lokasi);
        dialog.show();
        Button btn_tambah;
        final EditText ed_nama;
        TextView lat;
        TextView lon;
        lat = (TextView)dialog.findViewById(R.id.latitude);
        lon = (TextView)dialog.findViewById(R.id.longitude);
        lat.setText(String.valueOf(end_latitude));
        lon.setText(String.valueOf(end_longitude));
        ed_nama = (EditText)dialog.findViewById(R.id.nama);
        btn_tambah = (Button)dialog.findViewById(R.id.btn_tambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add.sukses(ed_nama.getText().toString());
                dialog.dismiss();
            }
        });

    }

    public void dialog_add_lokasi_lain(Context context,final Response_dialog_add_lain dialog_add){
        final Dialog dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tambah_lokasi_lain);
        dialog.show();
        Button btn_tambah;
        final EditText ed_nama,ed_lat,ed_lng;
        ed_lat = (EditText)dialog.findViewById(R.id.latitude);
        ed_lng = (EditText) dialog.findViewById(R.id.longitude);
        ed_nama = (EditText)dialog.findViewById(R.id.nama);
        btn_tambah = (Button)dialog.findViewById(R.id.btn_tambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add.sukses(ed_nama.getText().toString(),ed_lat.getText().toString(),ed_lng.getText().toString());
                dialog.dismiss();
            }
        });

    }

  public  interface Response{
        void sukses(ArrayList<Lokasi>lokasis);
        void gagal();
    };

    public  interface Response_insert{
        void sukses();
        void gagal();
    };

    public  interface Response_dialog_add{
        void sukses(String nama);
        void gagal();
    };

    public  interface Response_dialog_add_lain{
        void sukses(String nama, String lat, String lng);
        void gagal();
    };
}

