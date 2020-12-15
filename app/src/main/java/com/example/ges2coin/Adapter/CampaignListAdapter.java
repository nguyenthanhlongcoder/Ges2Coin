package com.example.ges2coin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ges2coin.Object.CampaignData;
import com.example.ges2coin.Object.JobData;
import com.example.ges2coin.R;

import java.util.ArrayList;

public class CampaignListAdapter extends BaseAdapter {
    private Context context;
    ArrayList<CampaignData> data;
    int layout;
    TextView name;
    TextView content;
    TextView progressStatus;
    ProgressBar progressBar;

    public CampaignListAdapter(Context context, int layout, ArrayList<CampaignData> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.campaign_list, null, true);

        name = convertView.findViewById(R.id.text_jobname);
        content = convertView.findViewById(R.id.text_jobContent);
        progressBar = convertView.findViewById(R.id.progress_bar);
        progressStatus = convertView.findViewById(R.id.text_progress);

        name.setText(data.get(position).getName());
        content.setText(data.get(position).getDescription());
        progressBar.setMax(100);
        int progress = Math.round(100/data.get(position).getQuanlity()* data.get(position).getCount());
        Log.d("progress", progress + "");
        progressBar.setProgress(progress);
        if (progress != 100){
            progressStatus.setText("In progress: " + data.get(position).getCount() + "/" + data.get(position).getQuanlity());
        }else{
            progressStatus.setText("Completed: " + data.get(position).getCount() + "/" + data.get(position).getQuanlity());
            progressStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        return convertView;
    }
}
