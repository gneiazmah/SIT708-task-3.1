package com.example.sit708myapplication02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ModelClass> list;
    Button start;
    EditText editTextName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.btnStart);
        editTextName = findViewById(R.id.editTextName);

        list = new ArrayList<>();
        list.add(new ModelClass("Which mobile operating system uses Java for its app development?","a) iOS","b) Android","c) Windows Mobile","d) None of the above","b) Android"));
        list.add(new ModelClass("Which is the most popular framework for building hybrid mobile applications?","a) React Native","b) Ionic","c) Xamarin","d) PhoneGap","a) React Native"));
        list.add(new ModelClass("Which component is responsible for managing the app's lifecycle?","a) Content Provider","b) Service","c) Activity","d) Broadcast Receiver","c) Activity"));
        list.add(new ModelClass("Which design pattern is commonly used in mobile app development?","a) Model-View-Controller","b) Observer","c) Singleton","d) Factory","a) Model-View-Controller"));
        list.add(new ModelClass("Which of the following is not a step in the mobile app development process?","A) Planning and strategy","B) User interface design","C) Marketing and advertising","D) Maintenance and updates","C) Marketing and advertising"));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextName.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Enter your Name",Toast.LENGTH_SHORT).show();
                }else{

                    name = editTextName.getText().toString();

                    SharedPreferences my_name = getSharedPreferences("my_name", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = my_name.edit();
                    editor.putString("user_name", name);
                    editor.commit();


                    Intent intent = (new Intent(getApplicationContext(), DashboardActivity.class));
                    startActivity(intent);
                }

            }
        });
    }
}