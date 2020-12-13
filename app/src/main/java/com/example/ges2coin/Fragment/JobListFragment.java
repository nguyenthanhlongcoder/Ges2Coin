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

import com.example.ges2coin.Activity.DetailJobActivity;
import com.example.ges2coin.Adapter.JobListAdapter;
import com.example.ges2coin.Object.GetMoneyData;
import com.example.ges2coin.Object.JobData;
import com.example.ges2coin.Object.SurveyInfo;
import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ListView list_job;
    ArrayList<JobData> data = new ArrayList<>();
    ArrayList<String> forYouSurvey = new ArrayList<>();
    UserData userData = new UserData();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joblist, null, true);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FirebaseUser user = mAuth.getCurrentUser();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        list_job = view.findViewById(R.id.list_job);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey");

        db.collection("users").document(user.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists() && documentSnapshot != null){
                    userData = documentSnapshot.toObject(UserData.class);
                    if(userData.getStatus().equals("Đã xác minh")){
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    final SurveyInfo surveyInfo = snapshot.getValue(SurveyInfo.class);
                                    Log.d("Survey", surveyInfo.getCampaignName().toString());
                                    boolean isAge = false;
                                    boolean isGender = false;
                                    boolean isCategory = false;
                                    boolean isDuyet = false;
                                    boolean isExist = false;
                                    if(userData.getForYouSurveyData() != null) {
                                        for (String item : userData.getForYouSurveyData()) {
                                            if (surveyInfo.getId().equals(item)) {
                                                isExist = true;
                                                forYouSurvey.add(surveyInfo.getId());
                                                break;
                                            }
                                        }
                                    }
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
                                    Log.d("Check", surveyInfo.getId() +  isAge + "" + isCategory + isDuyet + isExist + isGender + "");
                                    if (isAge && isCategory && isDuyet && isGender && isExist == false){
                                        Log.d("Ok", "ok");
//                                        if(userData.getForYouSurveyData() != null){
//                                            Map<String, Object> data = new HashMap<>();
//                                            data.put(userData.getForYouSurveyData().size() + "", surveyInfo.getId());
//                                            db.collection("users").document(userData.getId() + "/forYouSurveyData").
//                                        }else{
//                                            forYouSurvey.add(0, surveyInfo.getId());
//                                            db.collection("users").document(userData.getId()).update("forYouSurveyData", forYouSurvey);
//                                        }
                                        forYouSurvey.add(0, surveyInfo.getId());
                                        db.collection("users").document(userData.getId()).update("forYouSurveyData", forYouSurvey);

                                    }
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        if(userData.getForYouSurveyData() != null) {
                            for(String item : userData.getForYouSurveyData()){
                                final String itemSurvey = item;
                                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            Log.d("item", itemSurvey);

                                            if (snapshot.child("id").getValue().equals(itemSurvey)) {
                                                SurveyInfo surveyInfo = snapshot.getValue(SurveyInfo.class);
                                                Log.d("SurveyInfo", snapshot.child("id").getValue().toString());

                                                data.add(new JobData(surveyInfo.getId(),surveyInfo.getCampaignName(), surveyInfo.getDescription(), surveyInfo.getQuality(), 100));

                                            }
                                        }

                                        JobListAdapter adapter = new JobListAdapter(getContext(), R.layout.getmoney_list, data);
                                        list_job.setAdapter(adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }

                        }

                    }

                }
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


}