package com.example.asm_mob104_name.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.asm_mob104_name.MainActivity5;
import com.example.asm_mob104_name.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LV_adapter extends BaseAdapter {
    String[] noiDung;;
MainActivity5 mainActivity5;

    public LV_adapter(String[] noiDung, MainActivity5 mainActivity5) {
        this.noiDung = noiDung;
        this.mainActivity5 = mainActivity5;
    }

    @Override
    public int getCount() {
        return noiDung.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView  =  LayoutInflater.from(mainActivity5).inflate(R.layout.iteam_trangtruyen,null);
        ImageView img = (ImageView) convertView.findViewById(R.id.iteam_trangtruyen_img);
        Picasso.get().load(noiDung[position]).into(img);

        return convertView;
    }
}
