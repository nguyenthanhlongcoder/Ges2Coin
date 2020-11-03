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
import com.example.ges2coin.R;
import com.google.android.material.tabs.TabLayout;

public class JobFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private JobListFragment jobListFragment;
    private JobWorkedFragment jobWorkedFragment;
    private FragmentActivity myContext;
    private ViewPagerAdapter viewPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);

        jobListFragment = new JobListFragment();
        jobWorkedFragment = new JobWorkedFragment();

        viewPagerAdapter = new ViewPagerAdapter(myContext.getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(jobListFragment, "Job List");
        viewPagerAdapter.addFragment(jobWorkedFragment, "Job Worked");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_list_alt_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_playlist_add_check_24);

        Log.d("TAG", "Render");
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
