package com.example.ges2coin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ges2coin.Activity.DetailJobActivity;
import com.example.ges2coin.Adapter.JobListAdapter;
import com.example.ges2coin.Object.GetMoneyData;
import com.example.ges2coin.Object.JobData;
import com.example.ges2coin.R;

import java.util.ArrayList;

public class JobListFragment extends Fragment {

    ListView list_job;
    ArrayList<JobData> data = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joblist, null, true);

        list_job = view.findViewById(R.id.list_job);

        data.add(new JobData("Khao sat", "Kiem tien online", 10, 900, 200));
        data.add(new JobData("Khao sat", "Kiem tien online", 10, 900, 200));
        data.add(new JobData("Khao sat", "Kiem tien online", 10, 900, 200));
        data.add(new JobData("Khao sat", "Kiem tien online", 10, 900, 200));


        list_job.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(), DetailJobActivity.class));
            }
        });
        JobListAdapter adapter = new JobListAdapter(getContext(), R.layout.getmoney_list, data);
        Log.d("TAG", "onCreateView");

        list_job.setAdapter(adapter);
        return  view;
    }
}