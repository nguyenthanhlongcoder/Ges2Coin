package com.example.ges2coin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ges2coin.Adapter.ViewPagerJobAdapter;
import com.example.ges2coin.Fragment.AccountFragment;
import com.example.ges2coin.Fragment.CampaignFragment;
import com.example.ges2coin.Fragment.HomeFragment;
import com.example.ges2coin.Fragment.JobFragment;
import com.example.ges2coin.Fragment.SupportFragment;
import com.example.ges2coin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerJobAdapter viewPagerJobAdapter;
    List<Integer> bottomNavItemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavItemId = new ArrayList<>();

        bottomNavItemId.add(R.id.page_home);
        bottomNavItemId.add(R.id.page_job);
        bottomNavItemId.add(R.id.page_campaign);
        bottomNavItemId.add(R.id.page_support);
        bottomNavItemId.add(R.id.page_account);

        viewPager = findViewById(R.id.view_pager_job);
        viewPagerJobAdapter = new ViewPagerJobAdapter(getSupportFragmentManager(), 0);

        viewPager.setAdapter(viewPagerJobAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                viewPager.setCurrentItem(bottomNavItemId.indexOf(item.getItemId()), true);
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(bottomNavItemId.get(0));

    }


}