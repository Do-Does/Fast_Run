package com.example.dudas.running_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import static com.example.dudas.running_app.Question5.dream;

public class Agreement extends AppCompatActivity {

    private TextView dream_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);

        dream_text=findViewById(R.id.dream_text);

        String dream_element0 = dream.get(0);
        dream_text.setText(dream_element0);

    }
}
