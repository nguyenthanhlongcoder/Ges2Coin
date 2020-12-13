package com.example.ges2coin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ges2coin.Activity.DetailJobActivity;
import com.example.ges2coin.Object.GetMoneyData;
import com.example.ges2coin.Object.JobData;
import com.example.ges2coin.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class JobListAdapter extends BaseAdapter {
    private Context context;
    ArrayList<JobData> data;
    int layout;
    TextView coin;
    TextView name;

    TextView content;
    TextView count;
    TextView id;
    public JobListAdapter(Context context, int layout, ArrayList<JobData> data) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.jobitem_list, null, true);

        name = view.findViewById(R.id.text_jobname);
        content = view.findViewById(R.id.text_jobContent);
        coin = view.findViewById(R.id.text_coin);
        count = view.findViewById(R.id.text_jobCount);
        id = view.findViewById(R.id.txt_surveyid);

        name.setText(data.get(i).getName());
        content.setText(data.get(i).getContent());
        coin.setText(data.get(i).getCoins() + "");
        count.setText(data.get(i).getQuality() + "");
        id.setText(data.get(i).getId());


        return view;
    }
}
