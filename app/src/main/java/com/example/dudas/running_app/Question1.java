package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        final Button button9_q1=findViewById(R.id.button9_q1);
        final Button button8_q1=findViewById(R.id.button8_q1);
        final Button button7_q1=findViewById(R.id.button7_q1);
        final Button button6_q1=findViewById(R.id.button6_q1);
        final Button button5_q1=findViewById(R.id.button5_q1);
        final Button button4_q1=findViewById(R.id.button4_q1);

        button9_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button9_q1.getText().toString();
                distance_string.add("Q1: "+buttonText);
                startActivity(new Intent(Question1.this,Question2.class));
            }
        });


        button8_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q1.getText().toString();
                distance_string.add("Q1: "+buttonText);
                startActivity(new Intent(Question1.this,Question2.class));
            }
        });

        button7_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button7_q1.getText().toString();
                distance_string.add("Q1: "+buttonText);
                startActivity(new Intent(Question1.this,Question2.class));
            }
        });

        button6_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button6_q1.getText().toString();
                distance_string.add("Q1: "+buttonText);
                startActivity(new Intent(Question1.this,Question2.class));
            }
        });

        button5_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button5_q1.getText().toString();
                distance_string.add("Q1: "+buttonText);
                startActivity(new Intent(Question1.this,Question2.class));
            }
        });

        button4_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button4_q1.getText().toString();
                distance_string.add("Q1: "+buttonText);
                startActivity(new Intent(Question1.this,Question2.class));
            }
        });
    }

}
