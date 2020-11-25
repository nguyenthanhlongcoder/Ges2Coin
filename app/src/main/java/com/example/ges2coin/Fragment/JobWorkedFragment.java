package com.example.ges2coin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.ges2coin.R;

public class JobWorkedFragment extends Fragment {
    CardView cardview_history;
    ImageView img_filter;
    HorizontalScrollView scrollbar_worked;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobworked, null, true);
        cardview_history = view.findViewById(R.id.cardview_history);
        img_filter = view.findViewById(R.id.img_filter);
        scrollbar_worked = view.findViewById(R.id.scrollbar_worked);

        img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollbar_worked.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_history, new AutoTransition());
                    scrollbar_worked.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_history, new AutoTransition());
                    scrollbar_worked.setVisibility(View.GONE);
                }
            }
        });
        return  view;
    }
}
