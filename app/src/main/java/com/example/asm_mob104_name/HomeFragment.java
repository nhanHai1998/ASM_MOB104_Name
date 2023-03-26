package com.example.asm_mob104_name;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Mode.Truyen;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
GridView gridView;
ImageSlider imageSlider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = view.findViewById(R.id.gv_home);
        imageSlider = view.findViewById(R.id.home_image);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.mot, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hai, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ba, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        List<Truyen> truyens = new ArrayList<>();

        Truyen truyen = new Truyen("Tấm Cám",null,40,new String[]{"1","2"});
        Truyen truyen1 = new Truyen("Tấm Cám",null,40,new String[]{"1","2"});

        Truyen truyen2 = new Truyen("Tấm Cám",null,40,new String[]{"1","2","4"});

        Truyen truyen3 = new Truyen("Tấm Cám",null,40,new String[]{"1","2","6"});
        truyens.add(truyen);
        truyens.add(truyen1);
        truyens.add(truyen2);
        truyens.add(truyen3);



        Home_Adapter home_adapter = new Home_Adapter(truyens,getContext());




        gridView.setAdapter(home_adapter);



        return view;

    }
}