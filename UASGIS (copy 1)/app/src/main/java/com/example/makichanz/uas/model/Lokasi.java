package com.example.makichanz.uas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lokasi {
    @SerializedName("id_lokasi")
    @Expose
    private String id_lokasi;

    @SerializedName("nama_orang")
    @Expose
    private String nama_orang;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;


    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public String getNama_orang() {
        return nama_orang;
    }

    public void setNama_orang(String nama_orang) {
        this.nama_orang = nama_orang;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
