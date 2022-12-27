package com.example.pizzarestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> foodNames;
    ArrayList<String> foodDetails;
    LayoutInflater inflter;
    int flags[];

    public CustomAdapter(Context applicationContext, ArrayList<String> foodNames, ArrayList<String> foodDetails, int[] flags) {
        this.context = context;
        this.foodNames = foodNames;
        this.foodDetails = foodDetails;
        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return foodNames.size();
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
        view = inflter.inflate(R.layout.activity_list_view, null);
        TextView country = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        country.setText("\n"+foodNames.get(i) + "\n\n" + foodDetails.get(i));
        icon.setImageResource(flags[i]);
        return view;
    }
}