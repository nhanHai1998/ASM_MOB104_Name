package com.example.asm_mob104_name.API;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob104_name.Adapter.BL_adapter;
import com.example.asm_mob104_name.MainActivity4;
import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.Mode.Truyen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAndPost_BL {
    MainActivity4 activity4;
    RecyclerView recyclerView;
    BinhLuan binhLuan;
    int truyen;

    public GetAndPost_BL(MainActivity4 activity4, RecyclerView recyclerView, BinhLuan binhLuan, int truyen) {
        this.activity4 = activity4;
        this.recyclerView = recyclerView;
        this.binhLuan = binhLuan;
        this.truyen = truyen;
    }

    public void getBL(){

    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://c9m63d-8080.csb.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    //sử dụng interface
    BL_interface bl_interface = retrofit.create(BL_interface.class);

    Call<List<BinhLuan>> objCall2 = bl_interface.getbl(truyen);


        objCall2.enqueue(new Callback<List<BinhLuan>>() {
            @Override
            public void onResponse(Call<List<BinhLuan>> call, retrofit2.Response<List<BinhLuan>> response) {
                if (response.isSuccessful()){
                    List<BinhLuan> list = response.body();
                    BL_adapter bl_adapter = new BL_adapter(list,activity4);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity4));
                    recyclerView.setAdapter(bl_adapter);


                }else {
                    Log.d("danh sachs BL", "onResponse: Lỗi: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<BinhLuan>> call, Throwable t) {

            }
        });




}

public void postBL(){
    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://c9m63d-8080.csb.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    //sử dụng interface
    BL_interface bl_interface = retrofit.create(BL_interface.class);

    Call<BinhLuan> sendbl = bl_interface.them_moi_bl(binhLuan);
    sendbl.enqueue(new Callback<BinhLuan>() {
        @Override
        public void onResponse(Call<BinhLuan> call, Response<BinhLuan> response) {
            if(response.isSuccessful()){
                activity4.edt_bl.setText("");
                Log.e("onFailure: BL", "okkkkkkkkk"+ binhLuan.noidung);
            }
        }

        @Override
        public void onFailure(Call<BinhLuan> call, Throwable t) {
            Log.e("onFailure: BL", t.getMessage());
        }
    });
}

}
