package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question7 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question7);

        final Button button8_q7=findViewById(R.id.button8_q7);
        final Button button6_q7=findViewById(R.id.button6_q7);

        button8_q7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q7.getText().toString();
                distance_string.add(buttonText);

                startActivity(new Intent(Question7.this,Question7cd.class));
            }
        });


        button6_q7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button6_q7.getText().toString();
                distance_string.add("Q7: "+buttonText);
                startActivity(new Intent(Question7.this,Question8.class));
            }
        });



    }

}
