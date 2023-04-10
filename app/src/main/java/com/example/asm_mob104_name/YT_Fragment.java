package com.example.asm_mob104_name;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.asm_mob104_name.API.GetTruyen;
import com.example.asm_mob104_name.API.Get_TruyenYT;
import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class YT_Fragment extends Fragment {

    public GridView gridView;
    User user;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_y_t_, container, false);

        gridView = view.findViewById(R.id.gv_YT);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("INFOR_USER", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("USER","");


        Gson gson1 = new Gson();
        user = gson1.fromJson(name, User.class);
        Get_TruyenYT get_truyenYT = new Get_TruyenYT(YT_Fragment.this,gridView,user);
        get_truyenYT.GetTruyenYT();








        return view;
    }
}