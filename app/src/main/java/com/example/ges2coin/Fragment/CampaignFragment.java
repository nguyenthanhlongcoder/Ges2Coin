package com.example.ges2coin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ges2coin.Activity.AddJobActivity;
import com.example.ges2coin.Adapter.ViewPagerCampaignAdapter;
import com.example.ges2coin.Adapter.ViewPagerJobAdapter;
import com.example.ges2coin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class CampaignFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;
    private ViewPagerCampaignAdapter viewPagerCampaignAdapter;
    FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_campaign, container, false);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddJobActivity.class));
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager_campaign);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPagerCampaignAdapter = new ViewPagerCampaignAdapter(myContext.getSupportFragmentManager(), 0);

        viewPager.setAdapter(viewPagerCampaignAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_list_alt_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_playlist_add_check_24);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        myContext = (FragmentActivity)context;
        super.onAttach(context);
    }
}