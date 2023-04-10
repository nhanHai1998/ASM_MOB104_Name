package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob104_name.API.GetAndPosd_pass;
import com.example.asm_mob104_name.Mode.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class MainActivity6 extends AppCompatActivity {
EditText edt_pass,edt_repass,edt_newpass;
TextInputLayout til_pass,til_newpass,til_repass;
Button btn_huy,btn_ok;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        edt_newpass = findViewById(R.id.edt_dmk_newPass);
        edt_pass = findViewById(R.id.edt_dmk_pass);
        edt_repass = findViewById(R.id.edt_dmk_rePass);

        til_newpass = findViewById(R.id.til_dmk_newPass);
        til_pass = findViewById(R.id.til_dmk_pass);
        til_repass = findViewById(R.id.til_dmk_rePass);
        btn_huy = findViewById(R.id.btn_dmk_huy);
        btn_ok = findViewById(R.id.btn_dmk_xn);
        SharedPreferences sharedPreferences = getSharedPreferences("INFOR_USER", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("USER","");
        Log.e( "onClick: main6--------", name );

        Gson gson1 = new Gson();
         user = gson1.fromJson(name,User.class);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick: "+edt_pass.getText().toString(),"======="+user.id );
                if (MyCheck()!=0){
                    Toast.makeText(MainActivity6.this, "lỗi", Toast.LENGTH_SHORT).show();
                }else {

                    user.password =edt_newpass.getText().toString();


                    GetAndPosd_pass getAndPosd_pass = new GetAndPosd_pass(user,MainActivity6.this,user.id);
                    getAndPosd_pass.checkid();
                }
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity6.this, "huỷ", Toast.LENGTH_SHORT).show();
                edt_newpass.setText("");
                edt_repass.setText("");
                til_newpass.setError("");
                edt_pass.setText("");
                til_pass.setError("");
                til_repass.setError("");
            }
        });


    }

    public int MyCheck(){
        int a = 0;
        int b =0;
        int c = 0;
        if(edt_pass.getText().toString().isEmpty()){
            a = 1;
            til_pass.setError("không bỏ trống");
        }else {
             if (!edt_pass.getText().toString().equals(user.password)) {
                a = 1;
                til_pass.setError("mật khẩu không đúng");
             }
             else {
                 a=0;
                 til_pass.setError("");
             }
        }



        if(edt_newpass.getText().toString().isEmpty()){
           b = 1;
            edt_newpass.setError("không bỏ trống");
        } else {
            b=0;
            til_newpass.setError("");
        }

        if(edt_repass.getText().toString().isEmpty()){
            c = 1;
            til_repass.setError("không bỏ trống");
        } else {
             if (!edt_repass.getText().toString().equals(edt_newpass.getText().toString())) {
                c = 1;
                til_repass.setError("pass không trùng nhau");

            }else {
            c =0;
            til_repass.setError("");
             }
        }



        return a+b+c;

    }
}