package com.example.ges2coin.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ges2coin.Object.SurveyInfo;
import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
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
import com.google.firebase.firestore.QuerySnapshot;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddJobActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Survey");
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText edt_link_survey;
    EditText edt_campaign_name;
    EditText edt_quality;
    EditText edt_description;
    TextView txt_coin_to_pay;
    Button btn_create_survey;
    Chip chip_all, chip_mr, chip_ei, chip_es, chip_js, chip_cs, chip_te, chip_ba, chip_ee, chip_lg;
    RadioButton radio_all_gender, radio_male, radio_female, radio_all_age, radio_18to32, radio_33to47, radio_48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        map();

        if (edt_quality != null){
            edt_quality.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s != null && !s.equals("") && s.length() != 0){
                        txt_coin_to_pay.setText((100* Integer.valueOf(s.toString())) + "");

                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null && !s.equals("") && s.length() != 0){
                        txt_coin_to_pay.setText((100* Integer.valueOf(s.toString())) + "");

                    }
                }
            });

        }

        btn_create_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSurvey();
            }
        });

    }

    private void createSurvey() {

        final String linkSurvey = edt_link_survey.getText().toString().trim();
        final String campaignName = edt_campaign_name.getText().toString().trim();
        final Integer quality = Integer.valueOf(edt_quality.getText().toString().trim());
        final String description = edt_description.getText().toString().trim();

        final ArrayList<String> category = new ArrayList<>();

        if (chip_all.isChecked()){
            category.add("All");
        }else {
            if(chip_mr.isChecked()){
                category.add("Market Research");
            }
            if(chip_ei.isChecked()){
                category.add("Exit Interview");
            }
            if(chip_es.isChecked()){
                category.add("Employee Satisfaction");
            }
            if(chip_js.isChecked()){
                category.add("Job Satisfaction");
            }
            if(chip_cs.isChecked()){
                category.add("Customer Satisfaction");
            }
            if(chip_te.isChecked()){
                category.add("Training Evaluation");
            }
            if(chip_ba.isChecked()){
                category.add("Brand awareness");
            }
            if(chip_ee.isChecked()){
                category.add("Event Evaluation");
            }
            if(chip_lg.isChecked()){
                category.add("Lead generation");
            }
        }

        final FirebaseUser user = mAuth.getCurrentUser();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();

                    if(document.exists()){
                        final UserData userData = document.toObject(UserData.class);
                        String gender;
                        String age;
                        if (Integer.valueOf(txt_coin_to_pay.getText().toString()) <= userData.getCoin()){
                            final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                            final String id = myRef.push().getKey();
                            if(radio_all_gender.isChecked()){
                                gender = "All";
                            }else if(radio_male.isChecked()){
                                gender = "Male";
                            }else{
                                gender = "Female";
                            }

                            if(radio_all_age.isChecked()){
                                age = "All";
                            }else if(radio_18to32.isChecked()){
                                age = "18to32";
                            }else if(radio_33to47.isChecked()){
                                age = "33to47";
                            }else{
                                age = "48";
                            }
                            SurveyInfo surveyInfo = new SurveyInfo(id, linkSurvey, campaignName,description, age, gender, quality, category, "Chờ duyệt");

                            myRef.child("Survey").child(id).setValue(surveyInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddJobActivity.this, "Add Job success", Toast.LENGTH_SHORT).show();

                                    db.collection("users").document(user.getUid()).update("coin", userData.getCoin() - Integer.parseInt(txt_coin_to_pay.getText().toString()));
                                    db.collection("users").document(user.getUid()).collection("yourSurveyData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if(task.isSuccessful()){
                                                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                                            if(snapshot.child("id").getValue().equals(id)){
                                                                Map<String, Object> data = new HashMap<>();
                                                                data.put("id", id);
                                                                data.put("name", snapshot.child("campaignName").getValue().toString());
                                                                data.put("content", snapshot.child("description").getValue().toString());
                                                                data.put("quality", snapshot.child("quality").getValue().toString());

                                                                db.collection("users").document(user.getUid()).collection("yourSurveyData").add(data);

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
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddJobActivity.this, "Fail: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else{
                            Toast.makeText(AddJobActivity.this, "Không đủ tiền", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    void map(){
        edt_link_survey = findViewById(R.id.edt_link_survey);
        edt_campaign_name = findViewById(R.id.edt_campaign_name);
        edt_quality = findViewById(R.id.edt_quality);
        edt_description = findViewById(R.id.edt_description);
        btn_create_survey = findViewById(R.id.btn_create_survey);
        chip_all = findViewById(R.id.chip_all);
        chip_ba = findViewById(R.id.chip_ba);
        chip_cs = findViewById(R.id.chip_cs);
        chip_ee = findViewById(R.id.chip_ee);
        chip_ei = findViewById(R.id.chip_ei);
        chip_es = findViewById(R.id.chip_es);
        chip_js = findViewById(R.id.chip_js);
        chip_lg = findViewById(R.id.chip_lg);
        chip_mr = findViewById(R.id.chip_mr);
        chip_te = findViewById(R.id.chip_te);
        radio_18to32 = findViewById(R.id.radio_18to32);
        radio_33to47 = findViewById(R.id.radio_33to47);
        radio_48 = findViewById(R.id.radio_49);
        radio_all_age = findViewById(R.id.radio_all_age);
        radio_male = findViewById(R.id.radio_female);
        radio_female = findViewById(R.id.radio_female);
        radio_all_gender = findViewById(R.id.radio_all_gender);
        txt_coin_to_pay = findViewById(R.id.txt_coin_to_pay);
    }
}