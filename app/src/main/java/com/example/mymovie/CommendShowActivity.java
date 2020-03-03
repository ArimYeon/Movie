package com.example.mymovie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.mymovie.CommendWriteActivity.COMMEND;
import static com.example.mymovie.CommendWriteActivity.RATE;
import static com.example.mymovie.ui.detail.DetailFragment.ITEMS;


public class CommendShowActivity extends AppCompatActivity {

    private TextView writeBtn;
    private ArrayList<ReviewitemData> items;
    private ReviewAdapter adapter;
    private ListView listview;

    private final static int WRITE_REQ_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commend_show);

        listview = findViewById(R.id.listview);
        writeBtn = findViewById(R.id.write_btn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommendWriteActivity();
            }
        });

        Intent passedIntent = getIntent();
        items = passedIntent.getParcelableArrayListExtra(ITEMS);
        adapter = new ReviewAdapter(this, items);
        listview.setAdapter(adapter);

    }

    //작성하기로 넘어가기
    private void showCommendWriteActivity(){
        Intent intent = new Intent(getApplicationContext(), CommendWriteActivity.class);
        startActivityForResult(intent, WRITE_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //작성하기에서 돌아왔을 때
        if(requestCode==WRITE_REQ_CODE){
            if(resultCode == Activity.RESULT_OK){
                String commendText = intent.getStringExtra(COMMEND);
                float commendRate = intent.getFloatExtra(RATE, 0.0f);
                adapter.addItem(new ReviewitemData(R.drawable.user1, commendRate, "hello**", "1분전",commendText, "7"));
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {
        returnToMain();
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                returnToMain();
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //뒤로가기 눌렀을때 items main 으로 전달
    private void returnToMain(){
        items = adapter.getItems();
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(ITEMS, items);
        setResult(RESULT_OK, intent);
    }
}

