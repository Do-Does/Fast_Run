package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question8 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question8);

        final Button button8_q8=findViewById(R.id.button8_q8);
        final Button button7_q8=findViewById(R.id.button7_q8);



        button8_q8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q8.getText().toString();
                distance_string.add("Q8: "+buttonText);
                startActivity(new Intent(Question8.this,NameActivity.class));
            }
        });

        button7_q8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button7_q8.getText().toString();
                distance_string.add("Q8: "+buttonText);
                startActivity(new Intent(Question8.this,NameActivity.class));
            }
        });


    }

}
