package com.example.ges2coin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ges2coin.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class SupportFragment extends Fragment {
    LinearLayout layout_content1, layout_content2,layout_content3,layout_content4,layout_content5;
    ExpandableRelativeLayout support_content1,support_content2,support_content3,support_content4,support_content5;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_support, container, false);

        layout_content1 = view.findViewById(R.id.layout_content1);
        layout_content2 = view.findViewById(R.id.layout_content2);
        layout_content3 = view.findViewById(R.id.layout_content3);
        layout_content4 = view.findViewById(R.id.layout_content4);
        layout_content5 = view.findViewById(R.id.layout_content5);

        layout_content1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                support_content1  = view.findViewById(R.id.support_content1);
                support_content1.toggle();
            }
        });
        return view;
    }
}
