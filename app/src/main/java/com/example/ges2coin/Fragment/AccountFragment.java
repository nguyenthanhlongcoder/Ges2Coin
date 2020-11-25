package com.example.ges2coin.Fragment;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ges2coin.Activity.AccountManagerActivity;
import com.example.ges2coin.Activity.AccountSettingActivity;
import com.example.ges2coin.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.concurrent.Executor;

public class AccountFragment extends Fragment {
    ImageView img_profile;
    TextView text_username;
    LinearLayout btn_signout;
    GoogleSignInClient mGoogleSignInClient;
    LinearLayout section_account_management, section_account_settings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        img_profile = view.findViewById(R.id.img_profile);
        text_username = view.findViewById(R.id.txt_username);
        btn_signout = view.findViewById(R.id.btn_signout);
        section_account_management = view.findViewById(R.id.section_account_management);
        section_account_settings = view.findViewById(R.id.section_account_settings);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        if(account != null){
            Glide.with(getActivity()).load(account.getPhotoUrl()).into(img_profile);
            text_username.setText(account.getDisplayName());
        }

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn) {
            Glide.with(getActivity()).load(getActivity().getIntent().getBundleExtra("profile").getString("photoUrl")).into(img_profile);
            String displayName = getActivity().getIntent().getBundleExtra("profile").getString("displayName");

            Log.d("ID", getActivity().getIntent().getBundleExtra("profile").getString("photoUrl"));
            text_username.setText(displayName);

        }
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        section_account_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AccountManagerActivity.class));
            }
        });
        section_account_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AccountSettingActivity.class));
            }
        });
        return view;
    }
    private void signOut() {

        LoginManager.getInstance().logOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mGoogleSignInClient.signOut().addOnCompleteListener((Executor) this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                getActivity().finish();
            }
        });
    }

}
