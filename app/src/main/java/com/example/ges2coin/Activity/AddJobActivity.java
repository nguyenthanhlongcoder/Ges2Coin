package com.example.ges2coin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ges2coin.Object.SurveyInfo;
import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddJobActivity extends AppCompatActivity {

    EditText edt_link_survey;
    EditText edt_campaign_name;
    EditText edt_quality;
    Button btn_create_survey;
    Chip chip_all, chip_mr, chip_ei, chip_es, chip_js, chip_cs, chip_te, chip_ba, chip_ee, chip_lg;
    RadioButton radio_all_gender, radio_male, radio_female, radio_all_age, radio_18to32, radio_33to47, radio_48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        map();

        btn_create_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSurvey();
            }
        });
    }

    private void createSurvey() {

        String linkSurvey = edt_link_survey.getText().toString().trim();
        String campaignName = edt_campaign_name.getText().toString().trim();
        Integer quality = Integer.valueOf(edt_quality.getText().toString().trim());
        String gender = "";
        String age = "";
        ArrayList<String> category = new ArrayList<>();


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


        SurveyInfo surveyInfo = new SurveyInfo(linkSurvey, campaignName, age, gender, quality, category);
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        String id = myRef.push().getKey();
        myRef.child("Survey").child(id).setValue(surveyInfo);

    }

    void map(){
        edt_link_survey = findViewById(R.id.edt_link_survey);
        edt_campaign_name = findViewById(R.id.edt_campaign_name);
        edt_quality = findViewById(R.id.edt_quality);
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
    }
}