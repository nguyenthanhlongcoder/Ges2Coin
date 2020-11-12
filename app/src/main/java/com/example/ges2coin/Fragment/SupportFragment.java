package com.example.ges2coin.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ges2coin.R;

public class SupportFragment extends Fragment {
    ImageView arrow1,arrow2,arrow3,arrow4,arrow5;
    LinearLayout layout_content1, layout_content2,layout_content3,layout_content4,layout_content5;
    TextView support_content1,support_content2,support_content3,support_content4,support_content5;
    CardView cardview_expandable1, cardview_expandable2, cardview_expandable3, cardview_expandable4, cardview_expandable5;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_support, container, false);

        layout_content1 = view.findViewById(R.id.layout_content1);
        layout_content2 = view.findViewById(R.id.layout_content2);
        layout_content3 = view.findViewById(R.id.layout_content3);
        layout_content4 = view.findViewById(R.id.layout_content4);
        layout_content5 = view.findViewById(R.id.layout_content5);

        arrow1 = view.findViewById(R.id.arrow1);
        arrow2 = view.findViewById(R.id.arrow2);
        arrow3 = view.findViewById(R.id.arrow3);
        arrow4 = view.findViewById(R.id.arrow4);
        arrow5 = view.findViewById(R.id.arrow5);

        cardview_expandable1 = view.findViewById(R.id.cardview_expandable1);
        cardview_expandable2 = view.findViewById(R.id.cardview_expandable2);
        cardview_expandable3 = view.findViewById(R.id.cardview_expandable3);
        cardview_expandable4 = view.findViewById(R.id.cardview_expandable4);
        cardview_expandable5 = view.findViewById(R.id.cardview_expandable5);

        support_content1  = view.findViewById(R.id.support_content1);
        support_content2 = view.findViewById(R.id.support_content2);
        support_content3 = view.findViewById(R.id.support_content3);
        support_content4 = view.findViewById(R.id.support_content4);
        support_content5 = view.findViewById(R.id.support_content5);
        layout_content1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if (support_content1.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_expandable1, new AutoTransition());
                    arrow1.setImageResource(R.drawable.ic_baseline_arrow_gray_down_24);
                    support_content1.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_expandable1, new AutoTransition());
                    arrow1.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                    support_content1.setVisibility(View.GONE);
                }

            }
        });
        layout_content2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if (support_content2.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_expandable2, new AutoTransition());
                    arrow2.setImageResource(R.drawable.ic_baseline_arrow_gray_down_24);
                    support_content2.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_expandable2, new AutoTransition());
                    arrow2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                    support_content2.setVisibility(View.GONE);
                }
            }
        });
        layout_content3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if (support_content3.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_expandable3, new AutoTransition());
                    arrow3.setImageResource(R.drawable.ic_baseline_arrow_gray_down_24);
                    support_content3.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_expandable3, new AutoTransition());
                    arrow3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                    support_content3.setVisibility(View.GONE);
                }
            }
        });
        layout_content4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if (support_content4.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_expandable4, new AutoTransition());
                    arrow4.setImageResource(R.drawable.ic_baseline_arrow_gray_down_24);
                    support_content4.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_expandable3, new AutoTransition());
                    arrow4.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                    support_content4.setVisibility(View.GONE);
                }
            }
        });
        layout_content5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if (support_content5.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_expandable5, new AutoTransition());
                    arrow5.setImageResource(R.drawable.ic_baseline_arrow_gray_down_24);
                    support_content5.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_expandable5, new AutoTransition());
                    arrow5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                    support_content5.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

}
