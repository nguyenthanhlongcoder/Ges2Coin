package com.example.ges2coin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ges2coin.Adapter.ViewPagerAdapter;
import com.example.ges2coin.R;
import com.google.android.material.tabs.TabLayout;

public class JobFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private JobListFragment jobListFragment;
    private JobWorkedFragment jobWorkedFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job, container, false);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);

        jobListFragment = new JobListFragment();
        jobWorkedFragment = new JobWorkedFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), 0);

        viewPagerAdapter.addFragment(jobListFragment, "Job List");
        viewPagerAdapter.addFragment(jobWorkedFragment, "Job Worked");
        viewPager.setAdapter(viewPagerAdapter);

        viewPagerAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
    }
}
