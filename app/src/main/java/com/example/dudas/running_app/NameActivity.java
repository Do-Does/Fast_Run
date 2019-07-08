package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.example.dudas.running_app.Question5.dream;
import static com.example.dudas.running_app.Question6.hydration;
import static com.example.dudas.running_app.PauseActivity.pulse;

import static com.example.dudas.running_app.MainActivity.distance_string;

public class NameActivity extends AppCompatActivity {

    DatabaseReference reference;
    private EditText name_edit;
    private TextView text_dh;
    private TextView text_recomendation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        final Button button_zakoncz=findViewById(R.id.button_zakoncz);
        final EditText name_edit=findViewById(R.id.name_edit);
        text_dh=findViewById(R.id.text_dh);
        text_recomendation=findViewById(R.id.text_recomendation);

        String dream_element0 = dream.get(0);
        String hydration_element0 = hydration.get(0);
        String s_pulse_element0=pulse.get(0);
        double d_pulse_element0=Double.valueOf(s_pulse_element0);

        reference = FirebaseDatabase.getInstance().getReference().child("Running-app");

         if(d_pulse_element0<50){
             text_recomendation.setText("Nastepny trening pobiegnij w II zakresie tetna");
        }
        else if(d_pulse_element0>=50&&d_pulse_element0<=53){
             text_recomendation.setText("Nastepny trening pobiegnij w I zakresie tetna");
        }
        else if(d_pulse_element0>=54&&d_pulse_element0<=57){
             text_recomendation.setText("Nastepny trening pobiegnij w I zakresie tetna");
        }
        else if(d_pulse_element0>57){
             text_recomendation.setText("Nastepny trening pobiegnij w I zakresie tetna");
        }

        if(dream_element0=="3"&&hydration_element0=="3"){
            text_dh.setVisibility(View.VISIBLE);
            text_dh.setText("Zadbaj o sen trwajacy min.7 godzin oraz o dobre nawodnienie aby kolor Twojego moczu był przezroczysty, a Twoje treningi stana sie efektywniejsze.");
            dream.clear();
            hydration.clear();
        }
        else if (dream_element0=="3"){
            text_dh.setVisibility(View.VISIBLE);
            text_dh.setText("Zadbaj o sen trwajacy min.7 godzin, a Twoje treningi stana sie efektywniejsze.");
            dream.clear();
            hydration.clear();
        }
        else if(hydration_element0=="3") {
            text_dh.setVisibility(View.VISIBLE);
            text_dh.setText("Zadbaj o dobre nawodnienie aby kolor Twojego moczu był przezroczysty, a Twoje treningi stana sie efektywniejsze.");
            dream.clear();
            hydration.clear();
        }
        else {
            dream.clear();
            hydration.clear();
        }

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
