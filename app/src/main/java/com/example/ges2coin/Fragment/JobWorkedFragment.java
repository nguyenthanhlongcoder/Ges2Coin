package com.example.ges2coin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.ges2coin.Adapter.JobListAdapter;
import com.example.ges2coin.Object.JobData;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class JobWorkedFragment extends Fragment {
    CardView cardview_history;
    ImageView img_filter;
    HorizontalScrollView scrollbar_worked;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<JobData> data = new ArrayList<>();
    ListView list_job;
    SwipeRefreshLayout swipeRefreshLayout;
    final FirebaseUser user = mAuth.getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobworked, container, false);

        cardview_history = view.findViewById(R.id.cardview_history);
        img_filter = view.findViewById(R.id.img_filter);
        scrollbar_worked = view.findViewById(R.id.scrollbar_worked);
        list_job = view.findViewById(R.id.list_worked_job);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollbar_worked.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_history, new AutoTransition());
                    scrollbar_worked.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_history, new AutoTransition());
                    scrollbar_worked.setVisibility(View.GONE);
                }
            }
        });


        db.collection("users").document(user.getUid()).collection("workedSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document: task.getResult()){
                        data.add(new JobData(document.getData().get("id").toString(),
                                document.getData().get("name").toString(),
                                document.getData().get("content").toString(),
                                Integer.parseInt(document.getData().get("quality").toString()),
                                100));
                    }

                    JobListAdapter adapter = new JobListAdapter(getContext(), R.layout.jobitem_list, data);
                    list_job.setAdapter(adapter);

                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                refresh();

                swipeRefreshLayout.setRefreshing(false);

            }
        });
        return  view;


    }
    public void refresh(){
        db.collection("users").document(user.getUid()).collection("workedSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document: task.getResult()){
                        data.add(new JobData(document.getData().get("id").toString(),
                                document.getData().get("name").toString(),
                                document.getData().get("content").toString(),
                                Integer.parseInt(document.getData().get("quality").toString()),
                                100));
                    }

                    JobListAdapter adapter = new JobListAdapter(getContext(), R.layout.jobitem_list, data);
                    list_job.setAdapter(adapter);

                }
            }
        });

    }
}
