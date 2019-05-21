package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question6 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6);

        final Button button8_q6=findViewById(R.id.button8_q6);
        final Button button7_q6=findViewById(R.id.button7_q6);


        button8_q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q6.getText().toString();
                distance_string.add("Q6: "+buttonText);
                startActivity(new Intent(Question6.this,Question7.class));
            }
        });

        button7_q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button7_q6.getText().toString();
                distance_string.add("Q6: "+buttonText);
                startActivity(new Intent(Question6.this,Question7.class));
            }
        });




    }

}
