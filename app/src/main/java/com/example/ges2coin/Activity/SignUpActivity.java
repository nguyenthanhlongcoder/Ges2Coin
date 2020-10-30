package com.example.ges2coin.Activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity  extends AppCompatActivity {

    ImageView btn_return;
    TextView login_here;
    Button btn_signup;
    TextInputLayout edt_email, edt_password, edt_confirm_password;
    protected FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_return = findViewById(R.id.btn_return);
        login_here = findViewById(R.id.login_here);
        btn_signup = findViewById(R.id.btn_signup);

        edt_email = findViewById(R.id.email);
        edt_password = findViewById(R.id.password);
        edt_confirm_password = findViewById(R.id.confirm_password);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_return.performClick();
            }
        });
    }

    public void register(View view) {

        String password = edt_password.getEditText().getText().toString().trim();
        String email = edt_email.getEditText().getText().toString().trim();
        String confirm_password = edt_confirm_password.getEditText().getText().toString().trim();

        if(isEmail(email) == true){
            edt_email.setError("");
            if(password.length() <= 5){
                edt_password.setError("Password must be more than 6 characters");
            }else {
                edt_password.setError("");
            if (password.equals(confirm_password) == true) {
                edt_confirm_password.setError("");
                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else{
                edt_confirm_password.setError("Password is not correct");
            }}
        }else {
            edt_email.setError("Email is not valid");
        }
    }
    static boolean isEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
