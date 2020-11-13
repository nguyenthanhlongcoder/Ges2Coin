package com.example.ges2coin.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ges2coin.Adapter.ViewPagerAdapter;
import com.example.ges2coin.Adapter.ViewPagerJobAdapter;
import com.example.ges2coin.R;
import com.google.android.material.tabs.TabLayout;

public class JobFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;
    private ViewPagerJobAdapter viewPagerJobAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager_job);
        tabLayout = view.findViewById(R.id.tab_layout);

        viewPagerJobAdapter = new ViewPagerJobAdapter(myContext.getSupportFragmentManager(), 0);

        viewPager.setAdapter(viewPagerJobAdapter);
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
