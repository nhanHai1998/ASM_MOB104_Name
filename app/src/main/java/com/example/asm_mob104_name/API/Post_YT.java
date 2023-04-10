package com.example.asm_mob104_name.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.asm_mob104_name.MainActivity4;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Post_YT {
    String a;
    MainActivity4 activity4;

    public Post_YT(String a, MainActivity4 activity4) {
        this.a = a;
        this.activity4 = activity4;
    }

    public void yeuThich(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);
        SharedPreferences sharedPreferences = activity4.getSharedPreferences("INFOR_USER", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("USER","");

        Gson gson1 = new Gson();
        User user = gson1.fromJson(name,User.class);


        Call<List<User>> listCall = postAndGetLogin.checkid(user.id);
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                List<User> userList = response.body();
                User user1 = userList.get(0);
                    ArrayList<String> list = new ArrayList<String>(Arrays.asList(user1.yeuThich));
                    list.add(a);
                    user1.yeuThich = list.toArray(new String[list.size()]);
                    Call<User> yeuThich = postAndGetLogin.timtheo(user1.id, user1);
                    yeuThich.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                            if (response.isSuccessful()){
                                User user2 = response.body();
                                Gson gson2 = new Gson();
                                String a = gson2.toJson(user2.yeuThich);


                            }else {
                                Log.e("YTTTTTTTTTTTT: ","cutsss" );
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("YTTTTTTTTTTTT: ","cutsss"+t.getMessage() );
                        }
                    });

                }else {
                    Log.e("loi yt","loi");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });



    }
}
