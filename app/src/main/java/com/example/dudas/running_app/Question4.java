package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        final Button button8_q4=findViewById(R.id.button8_q4);
        final Button button_q4=findViewById(R.id.button_q4);


        button8_q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q4.getText().toString();
                distance_string.add("Q4: "+buttonText);
                startActivity(new Intent(Question4.this,Question5.class));
            }
        });



        button_q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button_q4.getText().toString();
                distance_string.add("Q4: "+buttonText);
                startActivity(new Intent(Question4.this,Question5.class));
            }
        });



    }

}
