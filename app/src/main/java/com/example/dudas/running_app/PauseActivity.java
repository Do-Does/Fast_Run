package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import static com.example.dudas.running_app.MainActivity.distance_string;

public class PauseActivity extends AppCompatActivity {

    private TextView timer_text;
    private Button timer_button;
    private EditText tetno_edit;

    private CountDownTimer countDownTimer;
    private static long timeLeftInMilliSeconds = 20000;
    private boolean timerRunning;
    public static ArrayList<String> summary = new ArrayList<String>();
    public static ArrayList<String> pulse = new ArrayList<String>();


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
                if(TextUtils.isEmpty(tetno_edit.getText())){
                    Toast.makeText(getApplicationContext(),"Tetno jest wymagane!",Toast.LENGTH_SHORT).show();
                }
                else {
                    String tetno = tetno_edit.getText().toString();
                    distance_string.add("Tetno po: " + tetno);
                    pulse.clear();
                    pulse.add(tetno);
                    //File f=new File("Fast Run.txt");
                    File file= getApplicationContext().getFileStreamPath("Fast Run.txt");
                    //InputStream is;
                    double actual_pulse=Double.valueOf(tetno);
                    if (file.exists()){
                       // Toast.makeText(getApplicationContext(),"tak",Toast.LENGTH_SHORT).show();
                        readFile(tetno);
                    }
                    else {
                        //Toast.makeText(getApplicationContext(),"nie",Toast.LENGTH_SHORT).show();
                        if(actual_pulse<50.0){
                            //Toast.makeText(getApplicationContext(),"<50",Toast.LENGTH_SHORT).show();
                            String b="1";
                            summary.clear();
                            summary.add(b);
                        }
                        else if(actual_pulse>=50.0&&actual_pulse<=53.0){
                            String b="2";
                            summary.clear();
                            summary.add(b);
                        }
                        else if(actual_pulse>=54.0&&actual_pulse<=57.0){
                            String b="3";
                            summary.clear();
                            summary.add(b);
                        }
                        else if(actual_pulse>57.0){
                            String b="4";
                            summary.clear();
                            summary.add(b);
                        }
                    }

                    writeFile(tetno);

                    startActivity(new Intent(PauseActivity.this, Question5.class));
                }
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

        public void writeFile(String buttonText){
        //File file=new File(getApplicationContext().getFilesDir(),"Fast Run.txt");
        try{
            FileOutputStream fileOutputStream = openFileOutput("Fast Run.txt",MODE_PRIVATE);
            // OutputStreamWriter myOutWriter=new OutputStreamWriter(fileOutputStream);

            fileOutputStream.write(buttonText.getBytes());
            fileOutputStream.close();
            //readFile(buttonText);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readFile(String buttonText){
        try{
            FileInputStream fileInputStream = openFileInput("Fast Run.txt");
            InputStreamReader inputStreamReader = new InputStreamReader((fileInputStream));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            /*StringBuilder sb=new StringBuilder();
            String line;
            while((line=bufferedReader.readLine())!=null){
                sb.append(line);

            }*/
            //Toast.makeText(getApplicationContext(),sb,Toast.LENGTH_SHORT).show();
            //readFile_q3();

            String lines;
            while((lines=bufferedReader.readLine())!=null){
                stringBuffer.append(lines+"\n");

                //Toast.makeText(getApplicationContext(),stringBuffer,Toast.LENGTH_SHORT).show();
                String str = stringBuffer.toString();
                //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                //str.toLowerCase();
                //buttonText.toLowerCase();
                double old_pulse =Double.valueOf(str);
                double actual_pulse=Double.valueOf(buttonText);


                readFile_q3(actual_pulse, old_pulse);


            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readFile_q3(double actual_pulse,double old_pulse){
        boolean success=false;
        try {
            FileInputStream fileInputStream = openFileInput("Fast Runq3.txt");
            InputStreamReader inputStreamReader = new InputStreamReader((fileInputStream));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //StringBuffer stringBuffer = new StringBuffer();

            StringBuilder sb_fatigue_level = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb_fatigue_level.append(line);

            }
            String s_fatigue_level = sb_fatigue_level.toString();
            double d_fatigue_level = Double.valueOf(s_fatigue_level);
           // double four=4;
            //double one=1;
            if (actual_pulse > old_pulse){
                if (d_fatigue_level < 4.0) {
                    double d_fatigue_level_increment = d_fatigue_level + 1.0;
                    String s_fatigue_level_increment = Double.toString(d_fatigue_level_increment);
                    String s_fatigue_level_increment1=Character.toString(s_fatigue_level_increment.charAt(0));
                    //Toast.makeText(getApplicationContext(), s_fatigue_level_increment, Toast.LENGTH_SHORT).show();
                    summary.clear();
                    summary.add(s_fatigue_level_increment1);
                } else {
                    String s_fatigue_level_4 = "4";
                    summary.clear();
                    summary.add(s_fatigue_level_4);
                    //Toast.makeText(getApplicationContext(), s_fatigue_level_4, Toast.LENGTH_SHORT).show();
                }
        }
            else if (actual_pulse < old_pulse){
                if (d_fatigue_level > 1.0) {
                    double d_fatigue_level_decrement = d_fatigue_level - 1.0;
                    String s_fatigue_level_decrement = Double.toString(d_fatigue_level_decrement);
                    String s_fatigue_level_decrement1=Character.toString(s_fatigue_level_decrement.charAt(0));
                    //Toast.makeText(getApplicationContext(), s_fatigue_level_decrement, Toast.LENGTH_SHORT).show();
                    summary.clear();
                    summary.add(s_fatigue_level_decrement1);
                } else {
                    String s_fatigue_level_1 = "1";
                    summary.clear();
                    summary.add(s_fatigue_level_1);
                    //Toast.makeText(getApplicationContext(), s_fatigue_level_1, Toast.LENGTH_SHORT).show();
                }
            }
            success=true;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}