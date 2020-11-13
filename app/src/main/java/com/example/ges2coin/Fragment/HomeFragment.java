package com.example.ges2coin.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ges2coin.Adapter.GetMoneyListAdapter;
import com.example.ges2coin.Object.GetMoneyData;
import com.example.ges2coin.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView list_getMoney;
    ArrayList<GetMoneyData> data = new ArrayList<>();
    ImageView img_arrow_home;
    CardView cardview_home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        img_arrow_home = view.findViewById(R.id.img_arrow_home);
        cardview_home = view.findViewById(R.id.cardview_home);


        data.add(new GetMoneyData("Hoàng Nêu", "1.000.000", 20));
        data.add(new GetMoneyData("Hoàng Nêu", "1.000.000", 20));
        data.add(new GetMoneyData("Hoàng Nêu", "1.000.000", 20));
        list_getMoney = view.findViewById(R.id.list_getMoney);
        GetMoneyListAdapter adapter = new GetMoneyListAdapter(getContext(), R.layout.getmoney_list, data);
        list_getMoney.setAdapter(adapter);

        img_arrow_home.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (list_getMoney.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardview_home, new AutoTransition());
                    img_arrow_home.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    list_getMoney.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(cardview_home, new AutoTransition());
                    img_arrow_home.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_white_24);
                    list_getMoney.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}
