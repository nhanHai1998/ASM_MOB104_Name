package com.example.asm_mob104_name.API;

import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Truyen_interface {
    @GET("/truyen")
    Call<List<Truyen>> lay_danh_sach();

    @POST("/truyen")
    Call<Truyen> pustTruyen(@Body Truyen truyen);

    @GET("/truyen?")
    Call<List<Truyen>> getnoiDung(@Query("id") int idTruyen);

    @GET("/truyen/{id}")
        Call<List<Truyen>> GetTYT(@Query("id") int id , @Body Truyen truyen);

    @PUT("/truyen/{id}")
    Call<Truyen> pustLT(@Path("id") int id, @Body Truyen truyen);

    @PUT("/truyen/{id}")
    Call<Truyen> pustLx(@Path("id") int id, @Body Truyen truyen);


}
