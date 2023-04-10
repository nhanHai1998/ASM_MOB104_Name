package com.example.asm_mob104_name.API;

import android.util.Log;

import com.example.asm_mob104_name.Mode.Truyen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Post_LX_LT {
    int id;
    Truyen truyen;

    public Post_LX_LT(int id, Truyen truyen) {
        this.id = id;
        this.truyen = truyen;
    }

    public void PostLX(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        Truyen_interface truyenInterface = retrofit.create(Truyen_interface.class);
        Call<Truyen> LX = truyenInterface.pustLx(id,truyen);
        LX.enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, retrofit2.Response<Truyen> response) {
                if(response.isSuccessful()){
                    Log.e( "onResponse: LXEEEMMM", truyen.luotXem+"" );
                }else {

                }
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t) {
                Log.e( "onResponse: LXEEEMMM rỗi", t.getMessage() );
            }
        });
    }
    public void PostLT(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        Truyen_interface truyenInterface = retrofit.create(Truyen_interface.class);
        Call<Truyen> LT = truyenInterface.pustLT(id,truyen);
        LT.enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, Response<Truyen> response) {
                if(response.isSuccessful()){
                    Log.e( "onResponse: LThích", truyen.luotThich+"" );
                }else {

                }
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t) {
                Log.e( "onResponse: LThích  rỗi", t.getMessage() );
            }
        });
    }
}
