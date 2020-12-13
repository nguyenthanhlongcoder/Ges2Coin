package com.example.ges2coin.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ges2coin.Object.UserData;
import com.example.ges2coin.Object.YourSurveyData;
import com.example.ges2coin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


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
        final String email = edt_email.getEditText().getText().toString().trim();
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
                                    mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUpActivity.this);
                                                LayoutInflater inflater = SignUpActivity.this.getLayoutInflater();

                                                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);

                                                dialogBuilder.setView(dialogView);

                                                TextView title = dialogView.findViewById(R.id.txt_dialog_title);
                                                TextView content = dialogView.findViewById(R.id.txt_dialog_content);
                                                Button positive = dialogView.findViewById(R.id.btn_positive);
                                                Button negative = dialogView.findViewById(R.id.btn_negative);

                                                positive.setText("Open Mail");
                                                title.setText("Sent Verification Email");
                                                content.setText("Registering account successful. Please verify your email.");
                                                final AlertDialog alertDialog = dialogBuilder.create();

                                                alertDialog.show();
                                                positive.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        alertDialog.dismiss();

                                                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });

                                                negative.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        alertDialog.dismiss();
                                                        finish();
                                                    }
                                                });
                                            }
                                        }
                                    });
                                    Log.d("TAG", "createUserWithEmail:success");

                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    UserData userData = new UserData(mFirebaseAuth.getCurrentUser().getUid(), email, null, email, 0, 0, null ,null, null,null, "Chưa xác minh");

                                    db.collection("users").document(userData.getId()).set(userData);

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
