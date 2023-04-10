package com.example.asm_mob104_name.API;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.MainActivity2;
import com.example.asm_mob104_name.MainActivity6;
import com.example.asm_mob104_name.Mode.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAndPosd_pass {
    User user;
    MainActivity6 activity6;
    int id;

    public GetAndPosd_pass(User user, MainActivity6 activity6, int id) {
        this.user = user;
        this.activity6 = activity6;
        this.id = id;
    }

    public void Postmk(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);
        Call<User> tim = postAndGetLogin.timtheo(id,user);

        tim.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response.isSuccessful()){
                    User user1 = response.body();
                    Log.e("onResponse: doimk", user1.id+"");
                    Log.e("onResponse: doimk", user1.password+"");
                    Toast.makeText(activity6, "Đổi thành công", Toast.LENGTH_SHORT).show();
                    activity6.startActivity(new Intent(activity6, MainActivity2.class));


                }else {

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void checkid(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);
        Call<List<User>> tim = postAndGetLogin.checkid(id);
        tim.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> userList= response.body();

                    Postmk();


                }else {
                    Log.e("onResponse: timg theo id", "k có");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("cút oy...........", "   "+t.getMessage());
            }
        });

    }


}
