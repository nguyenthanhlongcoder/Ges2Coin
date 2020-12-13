package com.example.ges2coin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ges2coin.Activity.AccountManagerActivity;
import com.example.ges2coin.Adapter.ViewPagerJobAdapter;
import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class JobFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;
    private ViewPagerJobAdapter viewPagerJobAdapter;
    private LinearLayout frame_confirm;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private TextView text_clickHere;

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
        frame_confirm = view.findViewById(R.id.frame_confirm);
        text_clickHere = view.findViewById(R.id.text_click_here);

        viewPagerJobAdapter = new ViewPagerJobAdapter(myContext.getSupportFragmentManager(), 0);

        viewPager.setAdapter(viewPagerJobAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_list_alt_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_playlist_add_check_24);


        text_clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AccountManagerActivity.class));
            }
        });
        setContent();

    }

    private void setContent() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        if(account != null){
            db.collection("users").document(user.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w("TAG", "Listen failed.", e);
                        return;
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        UserData userData = documentSnapshot.toObject(UserData.class);
                        if (userData.getStatus().equals("Chưa xác minh")) {
                            frame_confirm.setVisibility(View.VISIBLE);
                            viewPager.setVisibility(View.INVISIBLE);
                        } else {
                            frame_confirm.setVisibility(View.INVISIBLE);
                            viewPager.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Log.d("TAG", "Current data: null");
                    }
                }
            });
        }
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
