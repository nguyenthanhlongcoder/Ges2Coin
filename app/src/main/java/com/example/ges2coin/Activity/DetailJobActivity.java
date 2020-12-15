package com.example.ges2coin.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ges2coin.Adapter.JobListAdapter;
import com.example.ges2coin.Object.JobData;
import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.Object.WorkedSurveyData;
import com.example.ges2coin.R;
import com.example.ges2coin.databinding.ActivityDetailJobBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailJobActivity extends AppCompatActivity {
    ActivityDetailJobBinding B;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey");
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    UserData userData = new UserData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = DataBindingUtil.setContentView(this, R.layout.activity_detail_job);
        B.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("id").getValue().equals(getIntent().getStringExtra("id"))){
                        B.textJobname.setText(snapshot.child("campaignName").getValue().toString());
                        B.textJobContent.setText(snapshot.child("description").getValue().toString());
                        B.textCoin.setText("100");
                        B.textJobCount.setText(snapshot.child("quality").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        B.browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Click","Đã CLick");
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.child("id").getValue().equals(getIntent().getStringExtra("id"))) {
                                String url = snapshot.child("linkSurvey").getValue().toString();
                                Log.d("LinkSurvey", url);
                                Intent browserIntent =
                                        new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(browserIntent);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        B.finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = mAuth.getCurrentUser();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            userData = document.toObject(UserData.class);
                        }
                    }
                });
                db.collection("users").document(user.getUid()).collection("workedSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        if(snapshot.child("id").getValue().equals(getIntent().getStringExtra("id"))){
                                            Map<String, Object> data = new HashMap<>();
                                            String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                                            data.put("id", getIntent().getStringExtra("id"));
                                            data.put("name", snapshot.child("campaignName").getValue().toString());
                                            data.put("content", snapshot.child("description").getValue().toString());
                                            data.put("quality", snapshot.child("quality").getValue().toString());

                                            db.collection("users").document(user.getUid()).collection("workedSurveyData").add(data);
                                            db.collection("users").document(user.getUid()).collection("forYouSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        for(DocumentSnapshot document : task.getResult()){
                                                            if(document.getData().get("id").equals(getIntent().getStringExtra("id"))){
                                                                db.collection("users").document(user.getUid())
                                                                        .collection("forYouSurveyData")
                                                                        .document(document.getId()).delete();
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
                });
                db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();

                            db.collection("users").document(user.getUid()).collection("workedSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        for(DocumentSnapshot document : task.getResult()){
                                            String id = document.getData().get("id").toString();
                                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey").child(id).child("email");

                                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                                        Log.d("MainActivity", snapshot.toString());
                                                        if (snapshot.getValue().equals(user.getEmail())){
                                                            db.collection("users").document(user.getUid()).update("coin", userData.getCoin() + 100);
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
                            });
                        }
                    }
                });


            }
        });
    }
}