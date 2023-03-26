package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asm_mob104_name.Adapter.LV_adapter;
import com.example.asm_mob104_name.Adapter.Trang_truyen_Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    ListView rcv_truyen;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        rcv_truyen = findViewById(R.id.trangtruyen_rcv);

        List<String> stringList = new ArrayList<>();



        stringList.add("https://salt.tikicdn.com/cache/w1200/media/catalog/product/i/m/img038_1_1.jpg");
        stringList.add("https://salt.tikicdn.com/cache/w1200/media/catalog/product/i/m/img038_1_1.jpg");
        stringList.add("https://cdn0.fahasa.com/media/flashmagazine/images/page_images/truyen_co_tich_viet_nam_danh_cho_thieu_nhi___thanh_giong/2020_05_30_10_37_33_6-390x510.JPG");
//        Trang_truyen_Adapter trang_truyen_adapter = new Trang_truyen_Adapter(stringList,getApplicationContext());
        LV_adapter lv_adapter = new LV_adapter(stringList,MainActivity5.this);
        rcv_truyen.setAdapter(lv_adapter);


//        LinearLayoutManager linearLayoutManagerTopSP = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        rcv_truyen.setLayoutManager(linearLayoutManagerTopSP);
//
//        rcv_truyen.setAdapter(trang_truyen_adapter);

    }
}