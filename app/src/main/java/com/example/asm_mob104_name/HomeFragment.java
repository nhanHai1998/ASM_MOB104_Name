package com.example.asm_mob104_name;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.asm_mob104_name.API.GetTruyen;
import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Mode.Truyen;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
public GridView gridView;
ImageSlider imageSlider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = view.findViewById(R.id.gv_home);
        imageSlider = view.findViewById(R.id.home_image);

        GetTruyen getTruyen = new GetTruyen(HomeFragment.this,null,1);
        getTruyen.getAll();
        Log.d("TAG", "onCreateView: vaoif main 3");

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.mot, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hai, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ba, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
















        return view;

    }
}