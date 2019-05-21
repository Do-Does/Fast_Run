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

public class Question3 extends AppCompatActivity {

    DatabaseReference reference;
    private EditText tetno_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        final Button button8_q3=findViewById(R.id.button8_q3);
        final Button button7_q3=findViewById(R.id.button7_q3);
        final Button button6_q3=findViewById(R.id.button6_q3);
        final Button button5_q3=findViewById(R.id.button5_q3);
        //tetno_edit=findViewById(R.id.tetno_edit);

       // reference = FirebaseDatabase.getInstance().getReference().child("Running-app");


        button8_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button8_q3.getText().toString();
                distance_string.add("Q3: "+buttonText);
               // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                //Date date = new Date();
                //String tetno = tetno_edit.getText().toString();
                //for(String laps : distance_string) {
                   //reference.child("training").child(laps).setValue(true);
                  //  reference.child(tetno).child(dateFormat.format(date)).child(laps).setValue(true);
                //}
                startActivity(new Intent(Question3.this,Question4.class));
            }
        });

        button7_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button7_q3.getText().toString();
                distance_string.add("Q3: "+buttonText);
                startActivity(new Intent(Question3.this,Question4.class));
            }
        });

        button6_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button6_q3.getText().toString();
                distance_string.add("Q3: "+buttonText);
                startActivity(new Intent(Question3.this,Question4.class));
            }
        });

        button5_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button5_q3.getText().toString();
                distance_string.add("Q3: "+buttonText);
                startActivity(new Intent(Question3.this,Question4.class));
            }
        });
    }

}
