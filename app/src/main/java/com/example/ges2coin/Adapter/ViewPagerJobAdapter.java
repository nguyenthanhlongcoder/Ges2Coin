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
            "Home", "Job", "Campaign", "Support", "Account"
    };


    public ViewPagerJobAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new HomeFragment();
            }
            case 1:{
                return new JobFragment();
            }
            case 2:{
                return new CampaignFragment();
            }
            case 3:{
                return new SupportFragment();
            }
            case 4:{
                return new AccountFragment();
            }
            default:{
                return null;
            }
        }
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }

}
