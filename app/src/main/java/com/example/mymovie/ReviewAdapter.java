package com.example.mymovie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {

    Context context;
    ArrayList<ReviewitemData> items;

    public ReviewAdapter(Context context, ArrayList<ReviewitemData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ReviewitemView view = null;
        if(convertView==null){
           view = new ReviewitemView(context);
        }
        else{
            view = (ReviewitemView)convertView;
        }

        ReviewitemData item = items.get(i);

        view.setImage(item.getResId());
        view.setId(item.getId());
        view.setTime(item.getTime());
        view.setReview(item.getReview());
        view.setUp(item.getUp());
        view.setRatingBar(item.getRating());

        return view;
    }
}
