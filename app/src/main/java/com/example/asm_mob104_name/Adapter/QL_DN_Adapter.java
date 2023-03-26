package com.example.asm_mob104_name.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.asm_mob104_name.LogInFragment;
import com.example.asm_mob104_name.SignUpFragment;

public class QL_DN_Adapter  extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public QL_DN_Adapter(@NonNull FragmentManager fm , Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LogInFragment loginTabFragmen = new LogInFragment();
                return loginTabFragmen;
            case 1:
                SignUpFragment signupTabFragmen = new SignUpFragment();
                return signupTabFragmen;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
