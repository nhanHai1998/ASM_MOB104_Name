package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asm_mob104_name.API.GetTruyen;
import com.example.asm_mob104_name.Mode.Truyen;
import com.google.gson.Gson;

public class MainActivity5 extends AppCompatActivity {
    public ListView lv_truyen;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        lv_truyen = findViewById(R.id.trangtruyen_rcv);
        SharedPreferences sharedPreferences = getSharedPreferences("Truyen", Context.MODE_PRIVATE);
        String tryuen = sharedPreferences.getString("in4","");

        Gson gson = new Gson();
        Truyen truyen1 = gson.fromJson(tryuen,Truyen.class);


        GetTruyen getTruyen = new GetTruyen(null,this,truyen1.id);
        getTruyen.getruyenId();






//        LinearLayoutManager linearLayoutManagerTopSP = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        rcv_truyen.setLayoutManager(linearLayoutManagerTopSP);
//
//        rcv_truyen.setAdapter(trang_truyen_adapter);

    }
}