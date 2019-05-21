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

public class NameActivity extends AppCompatActivity {

    DatabaseReference reference;
    private EditText name_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        final Button button_zakoncz=findViewById(R.id.button_zakoncz);
        final EditText name_edit=findViewById(R.id.name_edit);

        reference = FirebaseDatabase.getInstance().getReference().child("Running-app");


        button_zakoncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = button_zakoncz.getText().toString();
                distance_string.add(buttonText);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String name = name_edit.getText().toString();
                for(String laps : distance_string) {
                //reference.child("training").child(laps).setValue(true);
                 reference.child(name).child(dateFormat.format(date)).child(laps).setValue(true);
                }
                startActivity(new Intent(NameActivity.this,PulseActivity.class));
            }
        });
    }

}
