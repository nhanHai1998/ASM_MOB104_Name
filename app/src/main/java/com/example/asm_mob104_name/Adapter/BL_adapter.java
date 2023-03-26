package com.example.asm_mob104_name.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob104_name.MainActivity4;
import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.R;

import java.util.List;

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

            holder.tv_time.setText(""+binhLuan.time);
            holder.tv_nd.setText(""+binhLuan.ND);
            holder.tv_name.setText(""+binhLuan.idND);

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
}
