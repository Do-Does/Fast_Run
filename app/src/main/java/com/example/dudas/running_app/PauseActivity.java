package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import static com.example.dudas.running_app.MainActivity.distance_string;

public class PauseActivity extends AppCompatActivity {

    private TextView timer_text;
    private Button timer_button;
    private EditText tetno_edit;

    private CountDownTimer countDownTimer;
    private static long timeLeftInMilliSeconds = 20000;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        timer_text=findViewById(R.id.timer_text);
        timer_button=findViewById(R.id.timer_button);
        tetno_edit=findViewById(R.id.tetno_edit);

        timer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        Button questions_button=findViewById(R.id.questions_button);

        questions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tetno = tetno_edit.getText().toString();
                distance_string.add("Tetno po: "+tetno);
                startActivity(new Intent(PauseActivity.this,Question1.class));
            }
        });

    }

    public void startStop() {
        if(timerRunning){
            stopTimer();
        }else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliSeconds = millisUntilFinished;
                updateTimer();
            }
            @Override
            public void onFinish() {
                timerRunning=false;
                //timer_button.setText("start");
                timer_text.setText("00:00");

            }
        }.start();
        timer_button.setVisibility(View.INVISIBLE);
        timerRunning=true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timer_button.setText("start");
        timerRunning=false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliSeconds/60000;
        int seconds = (int) (timeLeftInMilliSeconds/1000)%60;

        String timeLeftText=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

      /*  timeLeftText=""+minutes;
        timeLeftText+= ":";
        if(seconds<10) timeLeftText+="0";
        timeLeftText+=seconds; */

        timer_text.setText(timeLeftText);

    }


}