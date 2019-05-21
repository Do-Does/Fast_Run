package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class Question7cd extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question7cd);

        final Button button6_q7cd=findViewById(R.id.button6_q7cd);
        final EditText choroba_edit=findViewById(R.id.choroba_edit_q7cd);

        button6_q7cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button6_q7cd.getText().toString();
                distance_string.add("Q7cd: "+buttonText);
                String choroba = choroba_edit.getText().toString();
                distance_string.add("Q7: "+choroba);
                startActivity(new Intent(Question7cd.this,Question8.class));
            }
        });



    }

}
