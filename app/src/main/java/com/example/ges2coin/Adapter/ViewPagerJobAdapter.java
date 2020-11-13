package com.example.ges2coin.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ges2coin.Fragment.AccountFragment;
import com.example.ges2coin.Fragment.CampaignFragment;
import com.example.ges2coin.Fragment.HomeFragment;
import com.example.ges2coin.Fragment.JobFragment;
import com.example.ges2coin.Fragment.JobListFragment;
import com.example.ges2coin.Fragment.JobWorkedFragment;
import com.example.ges2coin.Fragment.SupportFragment;

public class ViewPagerJobAdapter extends ViewPagerAdapter {
    public static final String[] PAGE_TITLES = new String[]{
            "job list", "job worked"
    };

    public ViewPagerJobAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new JobListFragment();
            }
            case 1:{
                return  new JobWorkedFragment();
            }
            default:{
                return null;
            }
        }
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }


}
