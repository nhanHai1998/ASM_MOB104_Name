package com.example.asm_mob104_name;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob104_name.API.Post_signup;
import com.example.asm_mob104_name.Mode.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpFragment extends Fragment {
    Button btn_dk;
     public EditText edt_ten;
    public EditText edt_mk;
    public EditText edt_email;
    public EditText edt_fullTen;
    public TextInputLayout til_ten,til_mk,til_em,til_fullname;
    int check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        btn_dk = root.findViewById(R.id.btn_dk_signup);
        edt_ten = root.findViewById(R.id.edt_dk_name);
        edt_fullTen = root.findViewById(R.id.edt_dk_fullname);
        edt_email = root.findViewById(R.id.edt_dk_email);
        edt_mk = root.findViewById(R.id.edt_dk_pass);
        til_ten = root.findViewById(R.id.til_dk_name);
        til_mk = root.findViewById(R.id.til_dk_pass);
        til_fullname = root.findViewById(R.id.til_dk_fullname);
        til_em = root.findViewById(R.id.til_dk_email);


        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Check() != 0){
                    Toast.makeText(getContext(), "Chưa Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User();
                    user.username = edt_ten.getText().toString();
                    user.password = edt_mk.getText().toString();
                    user.email = edt_email.getText().toString();
                    user.fullName = edt_fullTen.getText().toString();


                    Post_signup post_signup = new Post_signup(SignUpFragment.this,user);
                    post_signup.checkMail();




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

        if(edt_email.getText().toString().isEmpty()){
            til_em.setError(" Không được để trống");
            check = 1;
        } else{
            til_em.setError("");
            check = 0;
        }

        if(edt_fullTen.getText().toString().isEmpty()){
            til_fullname.setError(" Không được để trống");
            check = 1;
        } else{
            if (!validateLetters(edt_fullTen.getText().toString())){
                til_fullname.setError("Sai Định dạng");
                check = 1;
            }else {
                til_fullname.setError("");
                check = 0;}
        }


        return check;
    }

    public static boolean validateLetters(String txt) {

        String regx = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();

    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    }
