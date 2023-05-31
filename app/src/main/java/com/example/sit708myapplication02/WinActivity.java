package com.example.sit708myapplication02;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WinActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView result, congtsMsg;
    Button btnNewQuiz, btnFinish;

    int correct, wrong;
    String namekey;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        result = findViewById(R.id.presult);
        congtsMsg = findViewById(R.id.congratsMsg);
        btnFinish = findViewById(R.id.btnFinish);
        btnNewQuiz = findViewById(R.id.btnNew);

        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);

        circularProgressBar.setProgress(correct);
        result.setText(correct + " / 5");

        SharedPreferences sp = getSharedPreferences("my_name", Activity.MODE_PRIVATE);
        String name = sp.getString("user_name", "");

        congtsMsg.setText("Congratulations  " + name + " !");

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("my_name", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                finishAffinity();
                System.exit(0);
            }
        });

        btnNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }
        });



    }
}