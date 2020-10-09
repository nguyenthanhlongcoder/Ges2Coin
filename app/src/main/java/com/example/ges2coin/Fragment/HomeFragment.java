package com.example.ges2coin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ges2coin.Adapter.GetMoneyListAdapter;
import com.example.ges2coin.Object.GetMoneyData;
import com.example.ges2coin.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView list_getMoney;
    ArrayList<GetMoneyData> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        data.add(new GetMoneyData("Hoàng Nêu", "1.000.000", 20));
        data.add(new GetMoneyData("Hoàng Nêu", "1.000.000", 20));
        data.add(new GetMoneyData("Hoàng Nêu", "1.000.000", 20));
        list_getMoney = view.findViewById(R.id.list_getMoney);
        GetMoneyListAdapter adapter = new GetMoneyListAdapter(getContext(), R.layout.getmoney_list, data);
        list_getMoney.setAdapter(adapter);
        return view;
    }
}
