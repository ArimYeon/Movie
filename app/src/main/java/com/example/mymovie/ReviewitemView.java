package com.example.mymovie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ReviewitemView extends LinearLayout {

    ImageView image;
    TextView id, time, review, up;
    RatingBar ratingBar;

    public ReviewitemView(Context context) {
        super(context);
        init(context);
    }

    public ReviewitemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.review_item, this, true);

        image = (ImageView)findViewById(R.id.review_image);
        id = (TextView)findViewById(R.id.review_id);
        time = (TextView)findViewById(R.id.review_time);
        review = (TextView)findViewById(R.id.review_text);
        up = (TextView)findViewById(R.id.review_up);
        ratingBar = (RatingBar)findViewById(R.id.review_rating_bar);
    }
    public void setImage(int resId){
        image.setImageResource(resId);
    }
    public void setId(String id){
        this.id.setText(id);
    }
    public void setTime(String time){
        this.time.setText(time);
    }
    public void setReview(String review){
        this.review.setText(review);
    }
    public void setUp(String up){
        this.up.setText(up);
    }
    public void setRatingBar(float rating){
        ratingBar.setRating(rating);
    }
}
