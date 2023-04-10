package com.example.asm_mob104_name.API;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.LogInFragment;
import com.example.asm_mob104_name.MainActivity3;
import com.example.asm_mob104_name.MainActivity4;
import com.example.asm_mob104_name.Mode.User;
import com.example.asm_mob104_name.SignUpFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Check_login {
    LogInFragment logInFragment;
    String name;
    String pass;
    int id;


    public Check_login(LogInFragment logInFragment, String name, String pass) {
        this.logInFragment = logInFragment;
        this.name = name;
        this.pass = pass;
    }

    public void test(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);




        Call<List<User>> objCall2 = postAndGetLogin.check(name,pass);


            objCall2.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                    if (response.isSuccessful()){
                        List<User> userList = response.body();
                        Log.e( "loing: ","  "+userList.size() );
                        if(userList.size() == 0){
                            Toast.makeText(logInFragment.getContext(), "sai tên hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                            logInFragment.til_mk.setError("Sai thông tin");
                            logInFragment.til_ten.setError("Sai thông tin");
                        }else {
                            Toast.makeText(logInFragment.getContext(), "thành công", Toast.LENGTH_SHORT).show();
                              Gson gson1 = new Gson();
                              String TTuser = gson1.toJson(userList.get(0));
                              SharedPreferences pref = logInFragment.getActivity().getSharedPreferences("INFOR_USER", logInFragment.getActivity().MODE_PRIVATE);
                              SharedPreferences.Editor editor = pref.edit();
                              editor.putString("USER", TTuser);
                              editor.apply();
                              Toast.makeText(logInFragment.getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                              logInFragment.til_mk.setError("");
                              logInFragment.til_ten.setError("");
                              logInFragment.getActivity().startActivity(new Intent(logInFragment.getActivity(), MainActivity3.class));
                        }
                    }else {

                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.d("login", "onFailure: "+t.getMessage());
                }
            });



    }





}
