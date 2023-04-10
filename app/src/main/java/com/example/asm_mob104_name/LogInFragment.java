package com.example.asm_mob104_name;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob104_name.API.Check_login;
import com.google.android.material.textfield.TextInputLayout;


public class LogInFragment extends Fragment {
    Button btn_dn;
    public EditText edt_ten,edt_mk;
    public TextInputLayout til_ten,til_mk;
    int check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_log_in, container, false);

        btn_dn = root.findViewById(R.id.btn_dn_login);
        edt_ten = root.findViewById(R.id.edt_dn_name);
        edt_mk = root.findViewById(R.id.edt_dn_pass);
        til_ten = root.findViewById(R.id.til_dn_name);
        til_mk = root.findViewById(R.id.til_dn_pass);
        btn_dn = root.findViewById(R.id.btn_dn_login);

        btn_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Check()!= 0){
                    Toast.makeText(getContext(), "Thông Tin Thiếu", Toast.LENGTH_SHORT).show();
                }else {
                    Check_login checkLogin = new Check_login(LogInFragment.this,edt_ten.getText().toString(),edt_mk.getText().toString());

                    checkLogin.test();

                }
            }
        });


        return root;

    }
    public int Check(){
        check = 0;
        if(edt_ten.getText().toString().isEmpty()){
            til_ten.setError(" Không được để trống");
            check = 1;
        } else{
            til_ten.setError("");
            check = 0;
        }
        if(edt_mk.getText().toString().isEmpty()){
            til_mk.setError(" Không được để trống");
            check = 1;
        } else{
            til_mk.setError("");
            check = 0;
        }
        return check;
    }
    }
