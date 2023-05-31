package com.example.sit708myapplication02;


import static com.example.sit708myapplication02.MainActivity.list;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    List<ModelClass> allQuestionsList;
    ModelClass modelClass;
    int index = 0;

    TextView cardQuestion, optionA, optionB, optionC, optionD;
    CardView cardA,cardB, cardC, cardD;
    Button Next;

    int correctCount = 0;
    int wrongCount = 0;
    int progressCount = 0;

    TextView welcomeMsg;

    ProgressBar progress_bar_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        InitializeFields();

        allQuestionsList = list;
        Collections.shuffle(allQuestionsList);
        modelClass = list.get(index);

        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));

        Next.setClickable(false);
        progress_bar_1.setProgress(progressCount);

        SharedPreferences sp = getSharedPreferences("my_name", Activity.MODE_PRIVATE);
        String name = sp.getString("user_name", "");

        welcomeMsg.setText("Welcome  " + name + " !");

        setAllData();
    }

    public void enableButton(){

        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }

    public void disableButton(){

        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
    }

    private void setAllData() {

        cardQuestion.setText(modelClass.getQuestion());
        optionA.setText(modelClass.getoA());
        optionB.setText(modelClass.getoB());
        optionC.setText(modelClass.getoC());
        optionD.setText(modelClass.getoD());

    }

    private void InitializeFields() {

        cardQuestion = findViewById(R.id.cardQuestion);
        optionA = findViewById(R.id.cardOptionA);
        optionB = findViewById(R.id.cardOptionB);
        optionC = findViewById(R.id.cardOptionC);
        optionD = findViewById(R.id.cardOptionD);

        cardA = findViewById(R.id.cardA);
        cardB = findViewById(R.id.cardB);
        cardC = findViewById(R.id.cardC);
        cardD = findViewById(R.id.cardD);

        Next = findViewById(R.id.btnNext);
        progress_bar_1 = findViewById(R.id.progress_bar_1);
        welcomeMsg = findViewById(R.id.welcomeMsg);
    }

    public void Correct(CardView cardView){

        cardView.setCardBackgroundColor(getResources().getColor(R.color.green));

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                welcomeMsg.setVisibility(View.GONE);
                index++;
                modelClass = list.get(index);
                resetColour();
                enableButton();
                setAllData();
            }
        });

    }

    public void Wrong(CardView cardView){

        cardView.setBackgroundColor(getResources().getColor(R.color.red));

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                welcomeMsg.setVisibility(View.GONE);
                wrongCount++;
                if (index < list.size() -1){
                    index++;
                    modelClass = list.get(index);
                    resetColour();
                    enableButton();
                    setAllData();
                }
                else{
                    GameWin();
                }
            }
        });
    }

    private void GameWin() {
        Intent intent = new Intent(getApplicationContext(), Activity.class);
        intent.putExtra("correct", correctCount);
        intent.putExtra("wrong", wrongCount);
        startActivity(intent);

    }

    public void resetColour(){

        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionAClick(View view) {
        progressCount = progressCount+20;
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (modelClass.getoA().equals(modelClass.getAns())){
            cardA.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;

            if (index < list.size() -1){
                Correct(cardA);
            }else{
                GameWin();
            }
        }else{
            Wrong(cardA);
        }
    }

    public void OptionBClick(View view) {
        progressCount = progressCount+20;
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (modelClass.getoB().equals(modelClass.getAns())){
            cardB.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;

            if (index < list.size() -1){
                Correct(cardB);
            }else{
                GameWin();
            }
        }else{
            Wrong(cardB);
        }
    }


    public void OptionCClick(View view) {
        progressCount = progressCount+20;
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (modelClass.getoC().equals(modelClass.getAns())){
            cardC.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;

            if (index < list.size() -1){
                Correct(cardC);
            }else{
                GameWin();
            }
        }else{
            Wrong(cardC);
        }
    }

    public void OptionDClick(View view) {
        progressCount = progressCount+20;
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (modelClass.getoD().equals(modelClass.getAns())){
            cardD.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;


            if (index < list.size() -1){
                Correct(cardD);
            }else{
                GameWin();
            }
        }else{
            Wrong(cardD);
        }
    }
}