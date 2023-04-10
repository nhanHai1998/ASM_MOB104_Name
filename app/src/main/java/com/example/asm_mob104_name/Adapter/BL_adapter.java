package com.example.asm_mob104_name.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob104_name.API.BL_interface;
import com.example.asm_mob104_name.API.GetAndPost_BL;
import com.example.asm_mob104_name.API.PostAndGetLogin;
import com.example.asm_mob104_name.MainActivity4;
import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.Mode.User;
import com.example.asm_mob104_name.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BL_adapter extends RecyclerView.Adapter< BL_adapter.ViewHolder> {

    List<BinhLuan> binhLuans;
    MainActivity4 activity4;

    public BL_adapter(List<BinhLuan> binhLuans, MainActivity4 activity4) {
        this.binhLuans = binhLuans;
        this.activity4 = activity4;
    }

    @NonNull
    @Override
    public BL_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity4).inflate(R.layout.iteam_bl,null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BL_adapter.ViewHolder holder, int position) {
            BinhLuan binhLuan = binhLuans.get(position);

            holder.tv_time.setText(""+binhLuan.ngay);
            holder.tv_nd.setText(""+binhLuan.noidung);
            holder.tv_name.setText(""+binhLuan.name);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences1 = activity4.getSharedPreferences("INFOR_USER", Context.MODE_PRIVATE);
                    String name = sharedPreferences1.getString("USER","");

                    Gson gson1 = new Gson();
                    User user = gson1.fromJson(name,User.class);
                    Log.e("check name",user.username );
                    Log.e("check name adapter", binhLuan.name);
                    if(binhLuan.name.equals(user.username)){
                       Opendialog(binhLuan);
                    }else {
                        Toast.makeText(activity4, "cutttttoyyyyy", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return binhLuans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_time,tv_nd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.iteam_bl_user);
            tv_nd = itemView.findViewById(R.id.iteam_bl_nd);
            tv_time = itemView.findViewById(R.id.iteam_bl_time);

        }
    }

    public void Opendialog(BinhLuan binhLuan){
        Dialog dialog = new Dialog(activity4);
        dialog.setContentView(R.layout.dialog_bl);

        Window window = dialog.getWindow();

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        WindowManager.LayoutParams  layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);

        Button btn_sua,btn_xoa;
        EditText edt_bl;

        btn_sua = dialog.findViewById(R.id.dialog_button_sua);
        btn_xoa = dialog.findViewById(R.id.dialog_button_xoa);
        edt_bl = dialog.findViewById(R.id.dialog_edtBL);
        edt_bl.setText(binhLuan.noidung);

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_bl.getText().toString().isEmpty()){
                    Toast.makeText(activity4, "bạn chưa nhập nội dung", Toast.LENGTH_SHORT).show();
                }else {
                    binhLuan.noidung= edt_bl.getText().toString();
                    Gson gson = new GsonBuilder().setLenient().create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://c9m63d-8080.csb.app/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    //sử dụng interface
                    BL_interface bl_interface = retrofit.create(BL_interface.class);
                    Call<BinhLuan> suabl =  bl_interface.suabl(binhLuan.id,binhLuan);

                    suabl.enqueue(new Callback<BinhLuan>() {
                        @Override
                        public void onResponse(Call<BinhLuan> call, Response<BinhLuan> response) {
                            if(response.isSuccessful()){
                                BinhLuan binhLuan1 = response.body();
                                Log.e( "onResponse: ",binhLuan1.noidung + "--------"+binhLuan1.id );
                                GetAndPost_BL getAndPost_bl = new GetAndPost_BL(activity4,activity4.rcv_bl,null,Integer.parseInt(binhLuan1.idTruyen));
                                getAndPost_bl.getBL();
                                dialog.dismiss();
                            }else {

                            }
                        }

                        @Override
                        public void onFailure(Call<BinhLuan> call, Throwable t) {

                        }
                    });

                }

            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://c9m63d-8080.csb.app/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                //sử dụng interface
                BL_interface bl_interface = retrofit.create(BL_interface.class);
                Call<BinhLuan> xoabl =  bl_interface.xoabl(binhLuan.id);
                xoabl.enqueue(new Callback<BinhLuan>() {
                    @Override
                    public void onResponse(Call<BinhLuan> call, Response<BinhLuan> response) {
                        if(response.isSuccessful()){
                            BinhLuan binhLuan1 = response.body();
                            Toast.makeText(activity4, "đã xoá", Toast.LENGTH_SHORT).show();
//                            GetAndPost_BL getAndPost_bl = new GetAndPost_BL(activity4,activity4.rcv_bl,null,Integer.parseInt(binhLuan1.idTruyen));
                            GetAndPost_BL getAndPost_bl = new GetAndPost_BL(activity4,activity4.rcv_bl,binhLuan1,Integer.parseInt(binhLuan1.idTruyen));
                            getAndPost_bl.getBL();
                            dialog.dismiss();
                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<BinhLuan> call, Throwable t) {

                    }
                });

            }
        });
        dialog.show();

    }
}
