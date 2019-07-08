package com.example.dudas.running_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.dudas.running_app.MainActivity.distance_string;
import static com.example.dudas.running_app.PauseActivity.summary;

public class Summary extends AppCompatActivity {

    private TextView fatigue_text;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        fatigue_text=findViewById(R.id.fatigue_text);
        final Button button_summary_yes=findViewById(R.id.button_summary_yes);
        final Button button_summary_no=findViewById(R.id.button_summary_no);

        String summary_element0 = summary.get(0);

        fatigue_text.setText(summary_element0);
        writeFile(summary_element0);
        distance_string.add("Poziom zmeczenia: " + summary_element0);

        button_summary_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Summary.this,NameActivity.class));
            }
        });

        button_summary_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Summary.this,Question3.class));
            }
        });

    }

    public void writeFile(String buttonText){
        File file=new File(getApplicationContext().getFilesDir(),"Fast Runq3.txt");
        try{
            FileOutputStream fileOutputStream = openFileOutput("Fast Runq3.txt",MODE_PRIVATE);
            fileOutputStream.write(buttonText.getBytes());
            fileOutputStream.close();

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
