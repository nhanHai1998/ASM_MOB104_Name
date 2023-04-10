package com.example.asm_mob104_name.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Adapter.LV_adapter;
import com.example.asm_mob104_name.HomeFragment;
import com.example.asm_mob104_name.MainActivity5;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetTruyen {

    HomeFragment homeFragment;
    MainActivity5 mainActivity5;
    int id;

    public GetTruyen(HomeFragment homeFragment, MainActivity5 mainActivity5, int id) {
        this.homeFragment = homeFragment;
        this.mainActivity5 = mainActivity5;
        this.id = id;
    }

    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://c9m63d-8080.csb.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    //sử dụng interface
     Truyen_interface truyenInterface = retrofit.create(Truyen_interface.class);

    public void getAll(){
        Call<List<Truyen>> objCall = truyenInterface.lay_danh_sach();



            objCall.enqueue(new Callback<List<Truyen>>() {

                @Override
                public void onResponse(Call<List<Truyen>> call, retrofit2.Response<List<Truyen>> response) {
                    if (response.isSuccessful()){
                        List<Truyen> list = response.body();
                        Home_Adapter home_adapter = new Home_Adapter(list,homeFragment.getContext());
                        homeFragment.gridView.setAdapter(home_adapter);


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


    public void getruyenId() {
        Call<List<Truyen>> objCall2 = truyenInterface.getnoiDung(id);


            objCall2.enqueue(new Callback<List<Truyen>>() {
                @Override
                public void onResponse(Call<List<Truyen>> call, retrofit2.Response<List<Truyen>> response) {
                    List<Truyen> list = response.body();
                    Log.e("onResponse: main5555555", list.size()+"");
                    if (list.size()==0){
                        Toast.makeText(mainActivity5, "K có nd", Toast.LENGTH_SHORT).show();
                    }else {
                        String[] a = list.get(0).noiDung;
                        LV_adapter lv_adapter = new LV_adapter(a,mainActivity5);

                        mainActivity5.lv_truyen.setAdapter(lv_adapter);
                    }



                }

                @Override
                public void onFailure(Call<List<Truyen>> call, Throwable t) {
                    Log.e("onResponse: main5555555", t.getMessage()+"");
                }
            });


    }


//    public void PustTruen(){
//        Truyen truyen = new Truyen();
//
//        truyen.tenTruyen="Naruto";
//        truyen.moTa ="Truyện hay vl";
//        truyen.tacGia="Nhân Hải";
//        truyen.luotXem= 30;
//        truyen.luotThich =50;
//        truyen.anhBia="https://1.bp.blogspot.com/-bLeGEx5eLBM/YDX8cW5J_DI/AAAAAAAA8og/IdScpkBDYN8BCSmqtnb9hlVAKHiKbO3DgCLcBGAsYHQ/s0/Hinh-nen-anime-nu%2B%252844%2529.jpg";
//        truyen.namXB=2000;
//        truyen.noiDung = new  String[]{ "https://img4.thuthuatphanmem.vn/uploads/2020/05/16/anh-anime-nu-toc-trang-ngau_043845747.jpg",
//                "https://img4.thuthuatphanmem.vn/uploads/2020/05/16/anh-anime-nu-toc-trang-ngau_043845747.jpg"};
//
//        Call<Truyen> pusss = truyenInterface.pustTruyen(truyen);
//        pusss.enqueue(new Callback<Truyen>() {
//            @Override
//            public void onResponse(Call<Truyen> call, Response<Truyen> response) {
//                if(response.isSuccessful()){
//                    Truyen truyen1 = response.body();
//                    Log.e("pust", truyen1.tenTruyen);
//                }else {
//                    Log.e("pust", "cút");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Truyen> call, Throwable t) {
//                Log.e("pust", "cút   "+t.getMessage());
//            }
//        });
//
//    }

    public void GetTruyenYT(){

    }


}
