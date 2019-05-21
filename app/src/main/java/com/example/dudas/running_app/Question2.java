package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        final Button button8_q2=findViewById(R.id.button8_q2);
        final Button button7_q2=findViewById(R.id.button7_q2);
        final Button button6_q2=findViewById(R.id.button6_q2);
        final Button button5_q2=findViewById(R.id.button5_q2);


        button8_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q2.getText().toString();
                distance_string.add("Q2: "+buttonText);
                startActivity(new Intent(Question2.this,Question3.class));
            }
        });

        button7_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button7_q2.getText().toString();
                distance_string.add("Q2: "+buttonText);
                startActivity(new Intent(Question2.this,Question3.class));
            }
        });

        button6_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button6_q2.getText().toString();
                distance_string.add("Q2: "+buttonText);
                startActivity(new Intent(Question2.this,Question3.class));
            }
        });

        button5_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button5_q2.getText().toString();
                distance_string.add("Q2: "+buttonText);
                startActivity(new Intent(Question2.this,Question3.class));
            }
        });


    }

}
