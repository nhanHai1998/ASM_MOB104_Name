package com.example.asm_mob104_name.API;

import android.util.Log;
import android.widget.GridView;

import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;
import com.example.asm_mob104_name.YT_Fragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Get_TruyenYT {

    YT_Fragment yt_fragment;
    GridView gridView;
    User user;
    User user1;

    public Get_TruyenYT(YT_Fragment yt_fragment, GridView gridView, User user) {
        this.yt_fragment = yt_fragment;
        this.gridView = gridView;
        this.user = user;
    }

    public void GetTruyenYT(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);
        Call<List<User>> tim = postAndGetLogin.checkid(user.id);
        tim.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> userList= response.body();

                    user1 = new User();
                    user1 = userList.get(0);
                    truyenYTTT();



                }else {
                    Log.e("onResponse: timg theo id", "k có");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("cút oy...........", "   "+t.getMessage());
            }
        });
        //sử dụng interface



    }

    public void truyenYTTT(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Truyen_interface truyenInterface = retrofit.create(Truyen_interface.class);
        Call<List<Truyen>> objCall = truyenInterface.lay_danh_sach();



        objCall.enqueue(new Callback<List<Truyen>>() {

            @Override
            public void onResponse(Call<List<Truyen>> call, retrofit2.Response<List<Truyen>> response) {
                if (response.isSuccessful()){
                    List<Truyen> truyens = new ArrayList<>();
                    List<Truyen> list = response.body();
                    for (int i = 0; i < list.size(); i++) {
                        for (int j = 0; j < user1.yeuThich.length; j++) {
                            if(user1.yeuThich[j].equals(String.valueOf(list.get(i).id))){
                                truyens.add(list.get(i));
                            }
                        }
                    }
                    Home_Adapter home_adapter = new Home_Adapter(truyens,yt_fragment.getContext());
                    gridView.setAdapter(home_adapter);
                    Log.e("truyen yt",truyens.size()+" ytttttttttttt" );


                }else {
                    Log.d("danh sachs truyeenj", "onResponse: Lỗi: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Log.d("chết                    ", "onResponse: Lỗi: "+ t.getMessage());
            }
        });

    }
}
