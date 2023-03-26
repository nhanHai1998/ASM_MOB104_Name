package com.example.asm_mob104_name;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class CN_Fragment extends Fragment {
LinearLayout lnlo_dmk,lnlo_dx;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_n_, container, false);
        lnlo_dmk = view.findViewById(R.id.lnlo_canhan_DMK);
        lnlo_dx = view.findViewById(R.id.lnlo_canhan_DX);

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