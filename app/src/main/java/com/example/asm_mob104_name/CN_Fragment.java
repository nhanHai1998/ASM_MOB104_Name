package com.example.asm_mob104_name;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asm_mob104_name.Mode.User;
import com.google.gson.Gson;


public class CN_Fragment extends Fragment {
LinearLayout lnlo_dmk,lnlo_dx;
TextView tv_name;
User user;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_n_, container, false);
        lnlo_dmk = view.findViewById(R.id.lnlo_canhan_DMK);
        lnlo_dx = view.findViewById(R.id.lnlo_canhan_DX);
        tv_name = view.findViewById(R.id.cn_name);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("INFOR_USER", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("USER","");
        Gson gson1 = new Gson();

        user = gson1.fromJson(name, User.class);
        tv_name.setText(user.username);


        lnlo_dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        lnlo_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity6.class));
            }
        });

        return view;

    }
}