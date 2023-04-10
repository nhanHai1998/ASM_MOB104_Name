package com.example.asm_mob104_name.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob104_name.MainActivity4;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_Adapter extends BaseAdapter {
    List<Truyen> truyens;
    Context context;
    TextView tv_tenTruyen,tv_luotXem,tv_luotCMT;
    ImageView img_truyen;

    public Home_Adapter(List<Truyen> truyens, Context context) {
        this.truyens = truyens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return truyens.size();
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
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.iteam_home, null);


        }

        tv_tenTruyen = view.findViewById(R.id.iteam_tenTruyen);
        tv_luotXem = view.findViewById(R.id.iteam_luotXem);
        tv_luotCMT = view.findViewById(R.id.iteam_cmt);
        img_truyen = view.findViewById(R.id.iteam_img);

        tv_tenTruyen.setText(""+truyens.get(i).tenTruyen);
        tv_luotXem.setText(truyens.get(i).luotXem+"");
        tv_luotCMT.setText(""+truyens.get(i).luotThich);
        Picasso.get().load(truyens.get(i).anhBia).into(img_truyen);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("Truyen",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String a = gson.toJson(truyens.get(i));
                editor.putString("in4",a);

                editor.apply();
                context.startActivity(new Intent(context, MainActivity4.class));
            }
        });



        return view;
    }
}
