package com.example.ges2coin.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ges2coin.R;
import com.example.ges2coin.databinding.ActivityAccountManagerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AccountManagerActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ActivityAccountManagerBinding B;
    public int age = 0;
    public String gender = "male";
    public ArrayList<String> category = new ArrayList<>();
    public String displayName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = DataBindingUtil.setContentView(this, R.layout.activity_account_manager);


        B.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                FirebaseUser user = mAuth.getCurrentUser();

                String id = user.getUid();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                DocumentReference userReference = db.collection("users").document(id);

                userReference.update("age", age);
                userReference.update("categories", category);
                userReference.update("displayName", displayName);
                userReference.update("gender", gender);
                userReference.update("status", "Đã xác minh");


            }
        });
    }

    public void getData(){
        displayName = B.edtDisplayName.getEditText().getText().toString().trim();
        if (B.radioMale.isChecked()) {
            gender = "male";
        } else {
            gender = "female";
        }
        if(!B.edtAge.getEditText().getText().equals("")) {
            age = Integer.parseInt(String.valueOf(B.edtAge.getEditText().getText()));
        }

        if (B.chipAll.isChecked()) {
            category.add("All");
        } else {
            if (B.chipMr.isChecked()) {
                category.add("Market Research");
            }
            if (B.chipEi.isChecked()) {
                category.add("Exit Interview");
            }
            if (B.chipEs.isChecked()) {
                category.add("Employee Satisfaction");
            }
            if (B.chipJs.isChecked()) {
                category.add("Job Satisfaction");
            }
            if (B.chipCs.isChecked()) {
                category.add("Customer Satisfaction");
            }
            if (B.chipTe.isChecked()) {
                category.add("Training Evaluation");
            }
            if (B.chipBa.isChecked()) {
                category.add("Brand awareness");
            }
            if (B.chipEe.isChecked()) {
                category.add("Event Evaluation");
            }
            if (B.chipLg.isChecked()) {
                category.add("Lead generation");
            }
        }
    }
}
