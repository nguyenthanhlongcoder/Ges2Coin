package com.example.ges2coin.Activity;

import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ges2coin.R;
import com.google.android.material.textfield.TextInputLayout;

public class AccountManagerActivity extends AppCompatActivity {
    TextInputLayout edt_nickname, edt_age;
    RadioButton rd_male, rd_female;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);
    }
}
