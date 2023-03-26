package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob104_name.Adapter.BL_adapter;
import com.example.asm_mob104_name.Mode.BinhLuan;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    Button btn_doc,btn_thich;
    ImageView img_bia;
    TextView tv_tentruyen,tv_tacgia,tv_sx,tv_LT,tv_CMT,tv_nd;
    RecyclerView rcv_bl;
    String vitri;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main4);

        tv_tentruyen = findViewById(R.id.truyen_ten);
        tv_tacgia = findViewById(R.id.truyen_TG);
        tv_CMT = findViewById(R.id.truyen_LCMT);
        tv_LT = findViewById(R.id.truyen_LT);
        tv_sx = findViewById(R.id.truyen_XB);
        tv_nd = findViewById(R.id.truyen_MT);

        tv_tentruyen.setText("Tấm Cám");
        tv_tacgia.setText("TG: ABC");
        tv_sx.setText("Năm XB: 2004");
        tv_LT.setText("Lượt Thích: 2");
        tv_CMT.setText("Lượt CMT: 4");
        tv_nd.setText("Câu chuyện tấm cám kể lại cuộc đời và số phận của tấm, một cô gái hiền lành, xinh đẹp, mồ côi, sống với dì ghẻ và cô em cùng cha khác mẹ tên là cám. Tấm bị mẹ con cám ngược đãi, hãm hại nhiều lần. Nhờ bụt an ủi và giúp đỡ, tấm trở thành hoàng hậu và sống hạnh phúc. Mẹ con cám bị trừng trị đích đáng.");



        btn_doc = findViewById(R.id.truyen_btn_doc);
        rcv_bl = findViewById(R.id.rcv_cmt);
        List<BinhLuan> binhLuans = new ArrayList<>();

        binhLuans.add(new BinhLuan("nhanhai","hay hay hay","15/8/98..................................."));
        binhLuans.add(new BinhLuan("nhanhai","hay hay hay","15/8/98"));
        binhLuans.add(new BinhLuan("nhanhai","hay hay hay","15/8/98"));
        binhLuans.add(new BinhLuan("nhanhai","hay hay hay","15/8/98"));
        binhLuans.add(new BinhLuan("nhanhai","hay hay hay","15/8/98"));

        BL_adapter bl_adapter = new BL_adapter(binhLuans, this);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_bl.setLayoutManager(layoutManager);
        rcv_bl.setHasFixedSize(true);


        rcv_bl.setAdapter(bl_adapter);



        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity4.this,MainActivity5.class));

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("ITEAM", Context.MODE_PRIVATE);
        vitri = sharedPreferences.getString("VITRI","");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        Toast.makeText(this, "vi trí "+vitri, Toast.LENGTH_SHORT).show();



        Log.d("vị trí ", "onCreate: " + vitri);
    }
}