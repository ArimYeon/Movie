package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView thumbUp, thumbDown, ratingText;
    private CheckBox thumbUpBtn, thumbDownBtn;
    private RatingBar ratingbar;
    private TextView writeBtn;
    private Button showAllBtn;
    private ReviewAdapter adapter;
    private ArrayList<ReviewitemData> items;
    private ListView reviewListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbUp = findViewById(R.id.thumb_up_text);
        thumbDown = findViewById(R.id.thumb_down_text);
        thumbUpBtn = findViewById(R.id.thumb_up_btn);
        thumbDownBtn = findViewById(R.id.thumb_down_btn);
        ratingbar = findViewById(R.id.rating_bar);
        ratingText = findViewById(R.id.rating_text);
        writeBtn = findViewById(R.id.write_btn);
        showAllBtn = findViewById(R.id.show_all_btn);
        reviewListView = findViewById(R.id.review_listview);

        //좋아요,싫어요
        thumbUpBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int upCount = Integer.parseInt(thumbUp.getText().toString());
                if(b){
                    if(thumbDownBtn.isChecked()){
                        thumbDownBtn.setChecked(false);
                    }
                    upCount++;
                }
                else{
                    upCount--;
                }
                thumbUp.setText(String.valueOf(upCount));
            }
        });
        thumbDownBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int downCount = Integer.parseInt(thumbDown.getText().toString());
                if(b){
                    if(thumbUpBtn.isChecked()){
                        thumbUpBtn.setChecked(false);
                    }
                    downCount++;
                }
                else{
                    downCount--;
                }
                thumbDown.setText(String.valueOf(downCount));
            }
        });

        //영화별점
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                ratingText.setText(String.valueOf(rating));
            }
        });

        //한줄평
        setReviewData();
        adapter = new ReviewAdapter(this, items);
        reviewListView.setAdapter(adapter);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "작성하기 눌렸습니다", Toast.LENGTH_LONG).show();
            }
        });
        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "모두보기 눌렸습니다", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setReviewData(){
        items = new ArrayList<ReviewitemData>();
        items.add(new ReviewitemData(R.drawable.user1, 3.5f, "yeon**", "5분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", "3"));
        items.add(new ReviewitemData(R.drawable.user1, 4.7f, "kim**", "5분전", "적당히 재밌다.", "5"));
        items.add(new ReviewitemData(R.drawable.user1, 2.3f, "cho**", "5분전", "오랜만에 잠 안오는 영화 봤네요.", "8"));
    }

}
