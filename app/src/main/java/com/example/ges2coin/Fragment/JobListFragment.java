package com.example.ges2coin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ges2coin.Activity.DetailJobActivity;
import com.example.ges2coin.Adapter.JobListAdapter;
import com.example.ges2coin.Object.GetMoneyData;
import com.example.ges2coin.Object.JobData;
import com.example.ges2coin.Object.SurveyInfo;
import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobListFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    ListView list_job;
    ArrayList<JobData> data = new ArrayList<>();
    UserData userData = new UserData();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = mAuth.getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joblist, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list_job = view.findViewById(R.id.list_job);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        userData = document.toObject(UserData.class);
                        if(userData.getStatus().equals("Đã xác minh")){

                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        final SurveyInfo surveyInfo = snapshot.getValue(SurveyInfo.class);


                                        db.collection("users").document(user.getUid()).collection("workedSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()){
                                                    for(DocumentSnapshot document : task.getResult()){
                                                        if(!document.getData().get("id").equals(surveyInfo.getId())){

                                                            boolean isAge = false;
                                                            boolean isGender = false;
                                                            boolean isCategory = false;
                                                            boolean isDuyet = false;
                                                            if (surveyInfo.getStatus().equals("Đã duyệt")){
                                                                isDuyet = true;
                                                            }
                                                            if(surveyInfo.getAge().equals("All")){
                                                                isAge = true;
                                                            }else if(surveyInfo.getAge().equals("18to32")){
                                                                if (userData.getAge() >= 18 && userData.getAge() <= 32) {
                                                                    isAge = true;
                                                                }}else if(surveyInfo.getAge().equals("33to47")) {
                                                                if (userData.getAge() >= 33 && userData.getAge() <= 47){
                                                                    isAge = true;
                                                                }
                                                            }else{
                                                                if (userData.getAge() >= 48){
                                                                    isAge = true;
                                                                }
                                                            }

                                                            if(surveyInfo.getGender().equals("All")){
                                                                isGender = true;
                                                            }else if(surveyInfo.getGender().equals("Male")){
                                                                if (userData.getGender().equals("Male")){
                                                                    isGender = true;
                                                                }else{
                                                                    if (userData.getGender().equals("Female")){
                                                                        isGender = true;
                                                                    }
                                                                }
                                                            }
                                                            for(String surveyCategory : surveyInfo.getCategory()){
                                                                if(surveyCategory.equals("All")){
                                                                    isCategory = true;
                                                                    break;
                                                                }else{
                                                                    for (String userCategory : userData.getCategories()) {
                                                                        if (surveyCategory.equals(userCategory)) {
                                                                            isCategory = true;
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                            if(userData.getCategories().get(0).equals("All")){
                                                                isCategory = true;
                                                            }
                                                            if (isAge && isCategory && isDuyet && isGender){

                                                                db.collection("users").document(userData.getId()).collection("forYouSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                        if(task.isSuccessful()){
                                                                            boolean isExist = false;
                                                                            for(QueryDocumentSnapshot document: task.getResult()){
                                                                                if(document.getData().get("id").toString().equals(surveyInfo.getId())){
                                                                                    isExist = true;
                                                                                    break;
                                                                                }
                                                                            }

                                                                            if(task.getResult().size() == 0){
                                                                                Map<String, Object> data = new HashMap<>();
                                                                                data.put("id", surveyInfo.getId());
                                                                                data.put("name", surveyInfo.getCampaignName());
                                                                                data.put("content", surveyInfo.getDescription());
                                                                                data.put("quality", surveyInfo.getQuality());

                                                                                db.collection("users").document(userData.getId()).collection("forYouSurveyData").add(data);

                                                                            }else{
                                                                                if(!isExist){
                                                                                    Map<String, Object> data = new HashMap<>();
                                                                                    data.put("id", surveyInfo.getId());
                                                                                    data.put("name", surveyInfo.getCampaignName());
                                                                                    data.put("content", surveyInfo.getDescription());
                                                                                    data.put("quality", surveyInfo.getQuality());

                                                                                    db.collection("users").document(userData.getId()).collection("forYouSurveyData").add(data);


                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        });

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }
                    }
                }
            }
        });

        db.collection("users").document(user.getUid()).collection("forYouSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
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
        list_job.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txt_id = view.findViewById(R.id.txt_surveyid);
                String id = txt_id.getText().toString();
                Intent intent = new Intent(getContext(), DetailJobActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }

    public void refresh(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey");
        db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        userData = document.toObject(UserData.class);
                        if(userData.getStatus().equals("Đã xác minh")){

                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        final SurveyInfo surveyInfo = snapshot.getValue(SurveyInfo.class);

                                        boolean isAge = false;
                                        boolean isGender = false;
                                        boolean isCategory = false;
                                        boolean isDuyet = false;

                                        if (surveyInfo.getStatus().equals("Đã duyệt")){
                                            isDuyet = true;
                                        }
                                        if(surveyInfo.getAge().equals("All")){
                                            isAge = true;
                                        }else if(surveyInfo.getAge().equals("18to32")){
                                            if (userData.getAge() >= 18 && userData.getAge() <= 32) {
                                                isAge = true;
                                            }}else if(surveyInfo.getAge().equals("33to47")) {
                                            if (userData.getAge() >= 33 && userData.getAge() <= 47){
                                                isAge = true;
                                            }
                                        }else{
                                            if (userData.getAge() >= 48){
                                                isAge = true;
                                            }
                                        }

                                        if(surveyInfo.getGender().equals("All")){
                                            isGender = true;
                                        }else if(surveyInfo.getGender().equals("Male")){
                                            if (userData.getGender().equals("Male")){
                                                isGender = true;
                                            }else{
                                                if (userData.getGender().equals("Female")){
                                                    isGender = true;
                                                }
                                            }
                                        }
                                        for(String surveyCategory : surveyInfo.getCategory()){
                                            if(surveyCategory.equals("All")){
                                                isCategory = true;
                                                break;
                                            }else{
                                                for (String userCategory : userData.getCategories()) {
                                                    if (surveyCategory.equals(userCategory)) {
                                                        isCategory = true;
                                                        break;
                                                    }
                                                }
                                            }
                                        }

                                        if(userData.getCategories().get(0).equals("All")){
                                            isCategory = true;
                                        }
                                        if (isAge && isCategory && isDuyet && isGender){

                                            db.collection("users").document(userData.getId()).collection("forYouSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        boolean isExsit = false;
                                                        for(QueryDocumentSnapshot document: task.getResult()){
                                                            if(!document.getData().get("id").toString().equals(surveyInfo.getId())){
                                                                isExsit = true;
                                                                break;
                                                            }
                                                        }

                                                        if(task.getResult().size() == 0){
                                                            Map<String, Object> data = new HashMap<>();
                                                            data.put("id", surveyInfo.getId());
                                                            data.put("name", surveyInfo.getCampaignName());
                                                            data.put("content", surveyInfo.getDescription());
                                                            data.put("quality", surveyInfo.getQuality());

                                                            db.collection("users").document(userData.getId()).collection("forYouSurveyData").add(data);

                                                        }else{
                                                            if(!isExsit){
                                                                Map<String, Object> data = new HashMap<>();
                                                                data.put("id", surveyInfo.getId());
                                                                data.put("name", surveyInfo.getCampaignName());
                                                                data.put("content", surveyInfo.getDescription());
                                                                data.put("quality", surveyInfo.getQuality());

                                                                db.collection("users").document(userData.getId()).collection("forYouSurveyData").add(data);

                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }
                    }
                }
            }
        });

        db.collection("users").document(user.getUid()).collection("forYouSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
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