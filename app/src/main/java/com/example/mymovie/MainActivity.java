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

    TextView thumbUp, thumbDown, ratingText;
    CheckBox thumbUpBtn, thumbDownBtn;
    RatingBar ratingbar;
    TextView writeBtn;
    Button showAllBtn;
    ReviewAdapter adapter;
    ArrayList<ReviewitemData> items;
    ListView reviewListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbUp = (TextView)findViewById(R.id.thumb_up_text);
        thumbDown = (TextView)findViewById(R.id.thumb_down_text);
        thumbUpBtn = (CheckBox)findViewById(R.id.thumb_up_btn);
        thumbDownBtn = (CheckBox)findViewById(R.id.thumb_down_btn);
        ratingbar = (RatingBar)findViewById(R.id.rating_bar);
        ratingText = (TextView)findViewById(R.id.rating_text);
        writeBtn = (TextView)findViewById(R.id.write_btn);
        showAllBtn = (Button)findViewById(R.id.show_all_btn);
        reviewListView = (ListView)findViewById(R.id.review_listview);

        //좋아요,싫어요
        thumbUpBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int up = Integer.parseInt(thumbUp.getText().toString());
                if(b){
                    if(thumbDownBtn.isChecked()){
                        thumbDownBtn.setChecked(false);
                    }
                    up++;
                }
                else{
                    up--;
                }
                thumbUp.setText(""+up);
            }
        });
        thumbDownBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int down = Integer.parseInt(thumbDown.getText().toString());
                if(b){
                    if(thumbUpBtn.isChecked()){
                        thumbUpBtn.setChecked(false);
                    }
                    down++;
                }
                else{
                    down--;
                }
                thumbDown.setText(""+down);
            }
        });

        //영화별점
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                ratingText.setText(""+rating);
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
