package com.example.ges2coin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ges2coin.Adapter.ViewPagerAdapter;
import com.example.ges2coin.Adapter.ViewPagerJobAdapter;
import com.example.ges2coin.Fragment.AccountFragment;
import com.example.ges2coin.Fragment.CampaignFragment;
import com.example.ges2coin.Fragment.HomeFragment;
import com.example.ges2coin.Fragment.JobFragment;
import com.example.ges2coin.Fragment.SupportFragment;
import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    List<Integer> bottomNavItemId;
    UserData userData = new UserData();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = mAuth.getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);

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