package com.example.mymovie.ui.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mymovie.CommendShowActivity;
import com.example.mymovie.CommendWriteActivity;
import com.example.mymovie.MainActivity;
import com.example.mymovie.R;
import com.example.mymovie.ReviewAdapter;
import com.example.mymovie.ReviewitemData;

import java.util.ArrayList;

import static com.example.mymovie.CommendWriteActivity.COMMEND;
import static com.example.mymovie.CommendWriteActivity.RATE;

public class DetailFragment extends Fragment {

    private TextView thumbUp, thumbDown, ratingText, writeBtn, movieName, bookRate;
    private CheckBox thumbUpBtn, thumbDownBtn;
    private RatingBar ratingbar;
    private Button showAllBtn;
    private ReviewAdapter adapter;
    private ArrayList<ReviewitemData> items;
    private ListView reviewListView;
    private ScrollView scrollview;
    private ImageView movieImage;

    private MainActivity activity;

    public final static String ITEMS  = "items";
    private final static int WRITE_REQ_CODE = 101;
    private final static int SHOW_REQ_CODE = 20;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        thumbUp = root.findViewById(R.id.thumb_up_text);
        thumbDown = root.findViewById(R.id.thumb_down_text);
        thumbUpBtn = root.findViewById(R.id.thumb_up_btn);
        thumbDownBtn = root.findViewById(R.id.thumb_down_btn);
        ratingbar = root.findViewById(R.id.rating_bar);
        ratingText = root.findViewById(R.id.rating_text);
        writeBtn = root.findViewById(R.id.write_btn);
        showAllBtn = root.findViewById(R.id.show_all_btn);
        reviewListView = root.findViewById(R.id.review_listview);
        scrollview = root.findViewById(R.id.scrollview);
        movieImage = root.findViewById(R.id.movie_image);
        movieName = root.findViewById(R.id.movie_name);
        bookRate = root.findViewById(R.id.book_rate_text);

        items = new ArrayList<ReviewitemData>();

        if(getArguments() != null){
            Bundle bundle = getArguments();
            movieImage.setImageResource(bundle.getInt("resId"));
            movieName.setText(bundle.getString("name"));
            bookRate.setText("5위  "+bundle.getString("rate")+"%");
        }

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
        adapter = new ReviewAdapter(activity, items);
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
                items = adapter.getItems();
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

        return root;
    }

    //리뷰초기화
    public void setReviewData(){
        adapter.addItem(new ReviewitemData(R.drawable.user1, 3.5f, "yeon**", "5분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", "3"));
        adapter.addItem(new ReviewitemData(R.drawable.user1, 4.7f, "kim**", "5분전", "적당히 재밌다.", "5"));
        adapter.addItem(new ReviewitemData(R.drawable.user1, 4.7f, "cho**", "5분전", "적당히 재밌다.", "5"));
        adapter.notifyDataSetChanged();
    }

    //작성하기로 넘어가기
    public void showCommendWriteActivity(){
        Intent intent = new Intent(activity, CommendWriteActivity.class);
        startActivityForResult(intent, WRITE_REQ_CODE);
    }
    //모두보기로 넘어가기
    public void showCommendShowActivity(){
        Intent intent = new Intent(activity, CommendShowActivity.class);
        intent.putParcelableArrayListExtra(ITEMS, items);
        startActivityForResult(intent,SHOW_REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
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

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }
}
