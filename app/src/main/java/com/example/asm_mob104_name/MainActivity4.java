package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob104_name.API.GetAndPost_BL;
import com.example.asm_mob104_name.API.Post_LX_LT;
import com.example.asm_mob104_name.API.Post_YT;
import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity4 extends AppCompatActivity {

    Button btn_doc,btn_thich;
    ImageButton img_btn;
    ImageView img_bia;
    public EditText  edt_bl;
    TextView tv_tentruyen,tv_tacgia,tv_sx,tv_LT,tv_lx,tv_mt;
    public RecyclerView rcv_bl;
    String tryuen;
    BinhLuan binhLuan;


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
        tv_lx = findViewById(R.id.truyen_LX);
        tv_LT = findViewById(R.id.truyen_LT);
        tv_sx = findViewById(R.id.truyen_XB);
        tv_mt = findViewById(R.id.truyen_MT);
        btn_doc = findViewById(R.id.truyen_btn_doc);
        rcv_bl = findViewById(R.id.rcv_cmt);
        img_bia = findViewById(R.id.truyen_img);
        btn_thich = findViewById(R.id.truyen_btn_yeuthich);
        img_btn =findViewById(R.id.truyen_btn_cmt);
        edt_bl = findViewById(R.id.edt_cmt);


        SharedPreferences sharedPreferences = getSharedPreferences("Truyen", Context.MODE_PRIVATE);
        tryuen = sharedPreferences.getString("in4","");
        Log.e("onCreate: lấy về   ",tryuen );
        Gson gson = new Gson();
        Truyen truyen1 = gson.fromJson(tryuen,Truyen.class);


        tv_tentruyen.setText(truyen1.tenTruyen);
        tv_tacgia.setText("TG: "+truyen1.tacGia);
        tv_sx.setText("NămXB: "+truyen1.namXB);
        tv_LT.setText("Lượt thích: "+ truyen1.luotThich);
        tv_lx.setText("Lượt xem: "+truyen1.luotXem);
        tv_mt.setText("Mô Tả: " + truyen1.moTa);
        Picasso.get().load(truyen1.anhBia).into(img_bia);

        CheckYT();








        GetAndPost_BL getAndPost_bl = new GetAndPost_BL(this,rcv_bl,binhLuan,truyen1.id);
        getAndPost_bl.getBL();




        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences1 = getSharedPreferences("INFOR_USER", Context.MODE_PRIVATE);
                String name = sharedPreferences1.getString("USER","");

                Gson gson1 = new Gson();
                User user = gson1.fromJson(name,User.class);

                binhLuan = new BinhLuan();
                binhLuan.noidung =edt_bl.getText().toString();
                binhLuan.name = String.valueOf(user.username);
                binhLuan.idTruyen = String.valueOf(truyen1.id);
                Date date = new Date();

                String ngay = DateFormat.getDateTimeInstance(DateFormat.LONG,
                        DateFormat.SHORT).format(date);

                binhLuan.ngay = ngay;
                String nd = gson1.toJson(binhLuan);
                Log.e("onClick: ", nd );
                GetAndPost_BL getAndPost_bl1 = new GetAndPost_BL(MainActivity4.this,rcv_bl,binhLuan,truyen1.id);
                getAndPost_bl1.postBL();
                getAndPost_bl1.getBL();


            }
        });







        btn_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckYT()== 0){
                Post_YT post_yt = new Post_YT(truyen1.id +"",MainActivity4.this);
                post_yt.yeuThich();
                Truyen truyen3 = truyen1;
                truyen3.luotThich = truyen1.luotThich + 1;

                Post_LX_LT post_lx_lt = new Post_LX_LT(truyen3.id,truyen3);
                post_lx_lt.PostLT();
                }else {
                    Toast.makeText(MainActivity4.this, "Truyện ĐÃ Thích", Toast.LENGTH_SHORT).show();
                }
                


            }
        });



        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity4.this,MainActivity5.class));
                Truyen truyen2 = truyen1;
                truyen2.luotXem = truyen1.luotXem + 1;

                Post_LX_LT post_lx_lt = new Post_LX_LT(truyen2.id,truyen2);
                post_lx_lt.PostLX();


            }
        });







    }

    public int CheckYT(){
        SharedPreferences sharedPreferences = getSharedPreferences("Truyen", Context.MODE_PRIVATE);
        tryuen = sharedPreferences.getString("in4","");
        Gson gson = new Gson();
        Truyen truyen1 = gson.fromJson(tryuen,Truyen.class);
        SharedPreferences sharedPreferences1 = getSharedPreferences("INFOR_USER", Context.MODE_PRIVATE);
        String name = sharedPreferences1.getString("USER","");

        Gson gson1 = new Gson();
        User user = gson1.fromJson(name,User.class);
        Log.e("CheckYT: ","user thích "+user.yeuThich.length );




        for (int i = 0; i < user.yeuThich.length; i++) {
            if(user.yeuThich[i].equals(String.valueOf(truyen1.id))){
                Log.e("CheckYT: ","user thích vào............ " );
                btn_thich.setText("Đã Thích");
                btn_thich.setBackground(getDrawable(R.drawable.buttom_goid));
                return 1;
            }else {
                btn_thich.setText("Thích");
                btn_thich.setBackground(getDrawable(R.drawable.buttom_bg));
                Log.e("CheckYT: ","user tcút oy" );
            }
        }
        return 0;
    }


}