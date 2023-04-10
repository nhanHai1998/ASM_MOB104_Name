package com.example.asm_mob104_name.API;

import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BL_interface {



    @POST("/cmt")
    Call<BinhLuan> them_moi_bl(@Body BinhLuan binhLuan);

    @GET("/cmt?")
    Call<List<BinhLuan>> getbl(@Query("idTruyen") int idTruyen);

    @PUT("/cmt/{id}")
    Call<BinhLuan> suabl(@Path("id") int bl, @Body BinhLuan binhLuan);
    @DELETE("/cmt/{id}")
    Call<BinhLuan> xoabl(@Path("id") int bl);
}
