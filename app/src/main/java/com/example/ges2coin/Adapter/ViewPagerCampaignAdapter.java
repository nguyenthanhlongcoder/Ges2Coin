package com.example.ges2coin.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ges2coin.Fragment.CampaignListFragment;
import com.example.ges2coin.Fragment.CampaignCompletedFragment;

public class ViewPagerCampaignAdapter extends ViewPagerAdapter {
    public static final String[] PAGE_TITLES = new String[]{
            "campaign list", "campaign completed"
    };

    public ViewPagerCampaignAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new CampaignListFragment();
            }
            case 1:{
                return  new CampaignCompletedFragment();
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
