package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class CommendWriteActivity extends AppCompatActivity {

    private EditText commend;
    private RatingBar ratingbar;
    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commend_write);

        commend = (EditText)findViewById(R.id.commendText);
        ratingbar = findViewById(R.id.commendRatingbar);

        Intent intent = getIntent();
        code = intent.getIntExtra("code", 0);

        //취소
        Button cancel = findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //저장
        Button save = findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMain();
                finish();
            }
        });
    }

    private void returnToMain() {
        String text = commend.getText().toString();
        float rate = ratingbar.getRating();

        Intent intent = null;
        switch (code){
            case 101:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                break;
            case 102:
                intent = new Intent(getApplicationContext(), CommendShowActivity.class);
                break;
        }
        intent.putExtra("commend", text);
        intent.putExtra("rate", rate);
        setResult(RESULT_OK, intent);
    }
}
