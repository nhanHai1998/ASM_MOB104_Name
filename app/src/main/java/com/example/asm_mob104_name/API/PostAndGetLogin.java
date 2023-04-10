package com.example.asm_mob104_name.API;

import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostAndGetLogin {
    @GET("/users")
    Call<List<User>> check(@Query("username") String username, @Query("password") String pass);

    @POST("/users")
    Call<User> post_use(@Body User user);

    @GET("/users")
    Call<List<User>> checkmail(@Query("email") String mail);

    @GET("/users")
    Call<List<User>> checkUsername(@Query("username") String username);

    @PUT("/users/{id}")
    Call<User> timtheo(@Path("id") int nd,@Body User user);

    @GET("/users")
    Call<List<User>> checkid(@Query("id") int id);

    @GET("/users")
    Call<List<Truyen>> getTruyenYT(@Query("id") int id );

}
