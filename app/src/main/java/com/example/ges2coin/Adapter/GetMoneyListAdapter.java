package com.example.ges2coin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ges2coin.Object.GetMoneyData;
import com.example.ges2coin.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GetMoneyListAdapter extends BaseAdapter {
    private Context context;
    ArrayList<GetMoneyData> data;
    int layout;
    TextView money;
    TextView time;
    TextView name;

    public GetMoneyListAdapter (Context context,int layout, ArrayList<GetMoneyData> data){
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
        final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.getmoney_list, null, true);

        money = view.findViewById(R.id.money);
        time = view.findViewById(R.id.time);
        name = view.findViewById(R.id.name);

        name.setText(data.get(i).getName().toString());
        money.setText(data.get(i).getMoney().toString());
        time.setText(data.get(i).getTime() + "");

        return view;
    }
}

