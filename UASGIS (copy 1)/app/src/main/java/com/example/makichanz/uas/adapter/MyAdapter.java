package com.example.makichanz.uas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.makichanz.uas.R;
import com.example.makichanz.uas.model.Lokasi;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList <Lokasi> items ;
    private TextView txt;

    public MyAdapter(Context mContext, ArrayList<Lokasi>items){
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.content_list, parent, false);
        }
        txt = (TextView)convertView.findViewById(R.id.txt);
        Lokasi current_item = (Lokasi) getItem(position);
        txt.setText(current_item.getNama_orang());

        return convertView;
    }
}
