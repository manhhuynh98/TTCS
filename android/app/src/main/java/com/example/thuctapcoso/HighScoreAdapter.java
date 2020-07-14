package com.example.thuctapcoso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HighScoreAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<HighScore> highScoreList;

    public HighScoreAdapter(Context context, int layout, ArrayList<HighScore> highScoreList) {
        this.context = context;
        this.layout = layout;
        this.highScoreList = highScoreList;
    }

    @Override
    public int getCount() {
        return highScoreList.size();
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

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(layout,viewGroup,false);

        TextView ten = view.findViewById(R.id.itemName);
        TextView diem = view.findViewById(R.id.itemScore);

        HighScore highScore = highScoreList.get(i);

        ten.setText(highScore.getTen());
        diem.setText(highScore.getDiem()+"");

        return view;
    }
}
