package com.example.mymovie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommendShowActivity extends AppCompatActivity {

    private TextView writeBtn;
    private ArrayList<ReviewitemData> items;
    private ReviewAdapter adapter;
    private ListView listview;

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
        items = passedIntent.getParcelableArrayListExtra("items");
        adapter = new ReviewAdapter(this, items);
        listview.setAdapter(adapter);

    }

    //작성하기로 넘어가기
    private void showCommendWriteActivity(){
        Intent intent = new Intent(getApplicationContext(), CommendWriteActivity.class);
        intent.putExtra("code", 102);
        startActivityForResult(intent, 102);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //작성하기에서 돌아왔을 때
        if(requestCode==102){
            if(intent != null){
                String commendText = intent.getStringExtra("commend");
                float commendRate = intent.getFloatExtra("rate", 0.0f);
                adapter.addItem(new ReviewitemData(R.drawable.user1, commendRate, "hello**", "1분전",commendText, "7"));
                adapter.notifyDataSetChanged();
            }
        }
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putParcelableArrayListExtra("items", items);
        setResult(RESULT_OK, intent);
    }
}
