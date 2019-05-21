package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question5 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        final Button button8_q5=findViewById(R.id.button8_q5);
        final Button button7_q5=findViewById(R.id.button7_q5);
        final Button button6_q5=findViewById(R.id.button6_q5);


        button8_q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q5.getText().toString();
                distance_string.add("Q5: "+buttonText);
                startActivity(new Intent(Question5.this,Question6.class));
            }
        });

        button7_q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button7_q5.getText().toString();
                distance_string.add("Q5: "+buttonText);
                startActivity(new Intent(Question5.this,Question6.class));
            }
        });

        button6_q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button6_q5.getText().toString();
                distance_string.add("Q5: "+buttonText);
                startActivity(new Intent(Question5.this,Question6.class));
            }
        });



    }

}
