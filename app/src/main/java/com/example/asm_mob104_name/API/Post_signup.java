package com.example.asm_mob104_name.API;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.Mode.User;
import com.example.asm_mob104_name.SignUpFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Post_signup {
   int check = 0;
   int check1 = 0;
    SignUpFragment signUpFragment;
    User users;

    public Post_signup(SignUpFragment signUpFragment, User users) {
        this.signUpFragment = signUpFragment;
        this.users = users;
    }

    public void post(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://c9m63d-8080.csb.app/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);


            User userDTO = new User();

            userDTO.password = users.password;
            userDTO.username = users.username;
            userDTO.email = users.email;
            userDTO.fullName = users.fullName;
            userDTO.yeuThich = new String[]{""};



                Call<User> post_user = postAndGetLogin.post_use(userDTO);
        TimeOut timeOut = new TimeOut();
        timeOut.execute("");

                post_user.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                        if(response.isSuccessful()){
                            User user1 = response.body();


                            signUpFragment.edt_ten.setText("");
                            signUpFragment.edt_mk.setText("");
                            signUpFragment.edt_email.setText("");
                            signUpFragment.edt_fullTen.setText("");

                            Toast.makeText(signUpFragment.getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Log.d("TAG", "onResponse: "+ response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("loi", "onFailure: "+t.getMessage());
                    }
                });





    }



 public void checkMail(){

     Gson gson = new GsonBuilder().setLenient().create();
     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl("https://c9m63d-8080.csb.app/")
             .addConverterFactory(GsonConverterFactory.create(gson))
             .build();
     PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);
     Call<List<User>> checkMail = postAndGetLogin.checkmail(users.email);
     checkMail.enqueue(new Callback<List<User>>() {
         @Override
         public void onResponse(Call<List<User>> call, Response<List<User>> response) {
             if(response.isSuccessful()){
                 List<User> userList = response.body();

                 if(userList.size()==0){
                     signUpFragment.til_em.setError("");
                     checkName();

                 }else {

                     signUpFragment.til_em.setError("Email đã được sử dụng");
                     Log.e("thong bao",check+"  check");



                 }
             }
         }

         @Override
         public void onFailure(Call<List<User>> call, Throwable t) {

         }
     });
     Log.e("thong bao 1",check+"  check");

 }

 public void checkName(){

     Gson gson = new GsonBuilder().setLenient().create();
     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl("https://c9m63d-8080.csb.app/")
             .addConverterFactory(GsonConverterFactory.create(gson))
             .build();
     PostAndGetLogin postAndGetLogin = retrofit.create(PostAndGetLogin.class);
     Call<List<User>> checkname = postAndGetLogin.checkUsername(users.username);
     checkname.enqueue(new Callback<List<User>>() {
         @Override
         public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
             if(response.isSuccessful()){
                 List<User>  userList = response.body();

                 if(userList.size()==0){
                     signUpFragment.til_ten.setError("");
                     post();

                 }else {
                     check1 = 1;
                     signUpFragment.til_ten.setError("Tên đã được sử dụng");




                 }
             }else {

             }
         }

         @Override
         public void onFailure(Call<List<User>> call, Throwable t) {

         }
     });

     Log.e("thong bao 1",check1+"");


 }


 public class TimeOut extends AsyncTask<String,Integer,String>{
     ProgressDialog progressDialog;
     @Override
     protected void onPreExecute() {
         super.onPreExecute();
         progressDialog = ProgressDialog.show(signUpFragment.getContext(),"Thông Báo","Đang Tải......");
     }

     @Override
     protected String doInBackground(String... strings) {
         try {
             Thread.sleep(2000);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
         return null;
     }

     @Override
     protected void onPostExecute(String s) {
         super.onPostExecute(s);
         progressDialog.dismiss();
     }
 }




}
