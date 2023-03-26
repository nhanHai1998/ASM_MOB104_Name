package com.example.asm_mob104_name.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm_mob104_name.LogInFragment;
import com.example.asm_mob104_name.SignUpFragment;

public class Viewpage_adapter extends FragmentStateAdapter {
    public Viewpage_adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0) {
            return new LogInFragment();
        }
        else {
            return new SignUpFragment();
    }


}

    @Override
    public int getItemCount() {
        return 2;
    };
}
