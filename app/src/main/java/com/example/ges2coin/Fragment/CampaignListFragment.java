package com.example.ges2coin.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ges2coin.Adapter.CampaignListAdapter;
import com.example.ges2coin.Object.CampaignData;
import com.example.ges2coin.Object.JobData;
import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

import java.util.ArrayList;

public class CampaignListFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    ListView list_job;
    ArrayList<CampaignData> data = new ArrayList<>();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = mAuth.getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campagin_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list_job = view.findViewById(R.id.list_campaign);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        db.collection("users").document(user.getUid()).collection("yourSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot document : task.getResult()){
                        final String id = document.getData().get("id").toString();
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    if(snapshot.child("status").getValue().equals("Đã duyệt")){
                                        if(snapshot.child("id").getValue().equals(id)){
                                            data.add(new CampaignData(snapshot.child("id").getValue().toString(),
                                                                        snapshot.child("campaignName").getValue().toString(),
                                                                        snapshot.child("description").getValue().toString(),
                                                                        Integer.parseInt(String.valueOf(snapshot.child("email").getChildrenCount())),
                                                                        Integer.parseInt(snapshot.child("quality").getValue().toString())));

                                        }
                                    }
                                }
                                CampaignListAdapter adapter = new CampaignListAdapter(getContext(), R.layout.campaign_list, data);
                                list_job.setAdapter(adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}