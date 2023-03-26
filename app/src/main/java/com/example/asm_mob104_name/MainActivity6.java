package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity6 extends AppCompatActivity {
EditText edt_pass,edt_repass,edt_newpass;
TextInputLayout til_pass,til_newpass,til_repass;
Button btn_huy,btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        edt_newpass = findViewById(R.id.edt_dmk_newPass);
        edt_pass = findViewById(R.id.edt_dmk_newPass);
        edt_repass = findViewById(R.id.edt_dmk_rePass);

        til_newpass = findViewById(R.id.til_dmk_newPass);
        til_pass = findViewById(R.id.til_dmk_pass);
        til_repass = findViewById(R.id.til_dmk_rePass);
        btn_huy = findViewById(R.id.btn_dmk_huy);
        btn_ok = findViewById(R.id.btn_dmk_xn);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyCheck()!=0){
                    Toast.makeText(MainActivity6.this, "lỗi", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity6.this, "ok", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity6.this,MainActivity2.class));
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
        if(edt_pass.getText().toString().isEmpty()){
            a = 1;
            til_pass.setError("không bỏ trống");
        } else if (!edt_pass.getText().toString().equals("1")) {
            a = 1;
            til_pass.setError("mật khẩu không đúng");
        }else {
            a=0;
            til_pass.setError("");
        }

        if(edt_newpass.getText().toString().isEmpty()){
            a = 1;
            edt_newpass.setError("không bỏ trống");
        } else {
            a=0;
            til_newpass.setError("");
        }

        if(edt_repass.getText().toString().isEmpty()){
            a = 1;
            til_repass.setError("không bỏ trống");
        } else if (!edt_repass.getText().toString().equals(edt_newpass.getText().toString())) {
            a = 1;
            til_repass.setError("pass không trùng nhau");
        }else {
            a=0;
            til_repass.setError("");
        }



        return a;

    }
}