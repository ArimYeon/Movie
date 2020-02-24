package com.example.mymovie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.mymovie.CommendWriteActivity.COMMEND;
import static com.example.mymovie.CommendWriteActivity.RATE;

public class MainActivity extends AppCompatActivity {

    private TextView thumbUp, thumbDown, ratingText;
    private CheckBox thumbUpBtn, thumbDownBtn;
    private RatingBar ratingbar;
    private TextView writeBtn;
    private Button showAllBtn;
    private ReviewAdapter adapter;
    private ArrayList<ReviewitemData> items;
    private ListView reviewListView;
    private ScrollView scrollview;

    public final static String ITEMS  = "items";
    private final static int WRITE_REQ_CODE = 101;
    private final static int SHOW_REQ_CODE = 201;

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
        scrollview = findViewById(R.id.scrollview);

        items = new ArrayList<ReviewitemData>();

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
        adapter = new ReviewAdapter(this, items);
        reviewListView.setAdapter(adapter);
        setReviewData();

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommendWriteActivity();
            }
        });
        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommendShowActivity();
            }
        });

        //리스트뷰 스크롤 가능하게
        reviewListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollview.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    //리뷰초기화
    public void setReviewData(){
        adapter.addItem(new ReviewitemData(R.drawable.user1, 3.5f, "yeon**", "5분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", "3"));
        adapter.addItem(new ReviewitemData(R.drawable.user1, 4.7f, "kim**", "5분전", "적당히 재밌다.", "5"));
        adapter.addItem(new ReviewitemData(R.drawable.user1, 4.7f, "cho**", "5분전", "적당히 재밌다.", "5"));
        adapter.notifyDataSetChanged();
    }
    //작성하기로 넘어가기
    private void showCommendWriteActivity(){
        Intent intent = new Intent(getApplicationContext(), CommendWriteActivity.class);
        startActivityForResult(intent, WRITE_REQ_CODE);
    }
    //모두보기로 넘어가기
    private void showCommendShowActivity(){
        items = adapter.getItems();
        Intent intent = new Intent(getApplicationContext(), CommendShowActivity.class);
        intent.putParcelableArrayListExtra(ITEMS, items);
        startActivityForResult(intent,SHOW_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //작성하기에서 돌아왔을 때
        if(requestCode==WRITE_REQ_CODE){
            if(resultCode == Activity.RESULT_OK){
                String commendText = intent.getStringExtra(COMMEND);
                float commendRate = intent.getFloatExtra(RATE, 0.0f);
                adapter.addItem(new ReviewitemData(R.drawable.user1, commendRate, "hi**", "10분전",commendText, "1"));
                adapter.notifyDataSetChanged();
            }
        }
        //모두보기에서 돌아왔을 때
        else if(requestCode==SHOW_REQ_CODE){
            items = intent.getParcelableArrayListExtra(ITEMS);
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    }

}
