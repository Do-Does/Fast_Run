package com.example.dudas.running_app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {




    private Button bstart;
    private Button bstop;
    private Button bpause;
    private TextView tDystans;
    private TextView text_wysokosc;
    private TextView time;
    private TextView twysokosc;
    private TextView tpredkosc;
    private LocationManager locationManager;
    private LocationListener listener;
    public double current_Lon=0;
    public double current_Lat=0;
    public double current_Alt=0;
    public double previous_Lon=0;
    public double previous_Lat=0;

    private long startTime = 0L;
    private long stopTime = 0L;
    private long timeMilliSecond = 0L;
    private long timeSwapBuff = 0L;
    private long updateTime = 0L;
    private boolean running;
    private Chronometer chronometer;
    private long pauseOffset;
    private long timeWhenStopped = 0;


    Handler customHandler = new Handler();
    private final long start=0;

    // public final double[] distance = new double[1000];
   ArrayList<Double> distance = new ArrayList<Double>();
    public static ArrayList<String> distance_string = new ArrayList<String>();
    ArrayList<Double> distance_lap = new ArrayList<Double>();
    ArrayList<String> distance_time = new ArrayList<String>();
    ArrayList<String> distance_time1 = new ArrayList<String>();
    ArrayList<Double> distance_height = new ArrayList<Double>();
    ArrayList<Double> distance_height_sum = new ArrayList<Double>();

    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bstart = (Button) findViewById(R.id.bstart);
        bstop = (Button) findViewById(R.id.bstop);
        //Qbstop.setOnClickListener(this);
       // bstop.setVisibility(View.INVISIBLE);

        bpause = (Button) findViewById(R.id.bpause);
        chronometer=findViewById(R.id.chronometer);
        tpredkosc=findViewById(R.id.tpredkosc);
        distance.clear();
        distance_string.clear();
        distance_lap.clear();
        distance_height.clear();
        distance_height_sum.clear();
        double b=0.0;

        distance_lap.add(b);
        distance_height.add(b);
        distance_height_sum.add(b);
        reference = FirebaseDatabase.getInstance().getReference().child("Running-app");

        //1
        final Chronometer chrono  = (Chronometer) findViewById(R.id.chronometer);
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h   = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String t = (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
                chronometer.setText(t);
            }
        });
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.setText("00:00:00");

        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime() -pauseOffset);
                chrono.start();
                configure_button();
                bstart.setVisibility(View.INVISIBLE);
                bpause.setVisibility(View.VISIBLE);

                bstop.setVisibility(View.INVISIBLE);
            }
        });

        bpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                pauseOffset = SystemClock.elapsedRealtime()-chrono.getBase();
                locationManager.removeUpdates(listener);
                bstop.setVisibility(View.VISIBLE);
                bpause.setVisibility(View.INVISIBLE);
                bstart.setVisibility(View.VISIBLE);

            }
        });


        //1


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    public double sumHeight(){

        int i;
        double sum = 0;
        for (i = 1; i < distance_height_sum.size(); i++) {
            sum += distance_height_sum.get(i);
        }
        double sum1=sum-distance_height.get(1);
        twysokosc.setText(new DecimalFormat("###.##").format(sum1));
        return  sum;
    }

    public double sumDistance() {
        int i;
        double sum = 0.00;
        for (i = 1; i < distance.size(); i++) {
            sum += distance.get(i);
        }
        double a = sum-distance_lap.get(distance_lap.size()-1);
        if (a>=1) {
           distance_lap.add(sum);
            String time = chronometer.getText().toString();
            time=time.replace(":",",");
            //double d= Double.parseDouble(time);
           distance_time.add(time);
        }


        //textView.setText(""+sum);
        String aa= String.format("%.2f",sum);
        double sum1= BigDecimal.valueOf(sum)
                .setScale(2,RoundingMode.HALF_UP)
                .doubleValue();
        tDystans.setText(aa);
        //tDystans.setText(new DecimalFormat("##.##").format(sum1));

        String distance = tDystans.getText().toString();
        if (distance.length()==4) {
            String time = chronometer.getText().toString();
            String time1 = time.substring(0, 2);
            String time2 = time.substring(3, 5);
            String time3 = time.substring(6, 8);

            double time1_double = Double.valueOf(time1);
            double time2_double = Double.valueOf(time2);
            double time3_double = Double.valueOf(time3);
            double time1_s = time1_double * 60 * 60;
            double time2_s = time2_double * 60;
            double time_total = time1_s + time2_s + time3_double;

            if (distance_time.size()>0) {
                String time_array = distance_time.get(distance_time.size() - 1);

                String time1_array = time_array.substring(0, 2);
                String time2_array = time_array.substring(3, 5);
                String time3_array = time_array.substring(6, 8);

                double time1_array_double = Double.valueOf(time1_array);
                double time2_array_double = Double.valueOf(time2_array);
                double time3_array_double = Double.valueOf(time3_array);
                double time1_array_s = time1_array_double * 60 * 60;
                double time2_array_s = time2_array_double * 60;
                double time_array_total = time1_array_s + time2_array_s + time3_array_double;

                String distance1 = distance.substring(0,1);
                String distance2 = distance.substring(2,3);
                String distance3 = distance.substring(3,4);
                double distance1_double = Double.valueOf(distance1);
                double distance2_double = Double.valueOf(distance2);
                double distance3_double = Double.valueOf(distance3);
                //double distance_total = distance1_double * 1000 + distance2_double * 100+distance3_double*10;
                double distance_total = distance2_double*100+distance3_double*10;

                double time_total_a=time_total-time_array_total;
                double rate = ((distance_total / time_total_a)*60*60)/1000;
                double rate1=3600/rate;

                String rate_total=(int)(rate1/60)+":"+(int)(rate1%60);
                String rate_total_s=Integer.toString((int)(rate1%60));
                if (rate_total_s.length()==1) {
                    String rate_total1=(int)(rate1/60)+":0"+(int)(rate1%60);
                    tpredkosc.setText(rate_total1);
                }
                else {
                    tpredkosc.setText(rate_total);
                }
            }
            else {

                String distance1 = distance.substring(0, 1);
                String distance2 = distance.substring(2, 3);
                String distance3 = distance.substring(3, 4);
                double distance1_double = Double.valueOf(distance1);
                double distance2_double = Double.valueOf(distance2);
                double distance3_double = Double.valueOf(distance3);
                //double distance_total = distance1_double * 1000 + distance2_double * 100+distance3_double*10;
                double distance_total = distance2_double * 100 + distance3_double * 10;

                double rate = ((distance_total / time_total) * 60 * 60) / 1000;
                double rate1=3600/rate;

                String rate_total=(int)(rate1/60)+":"+(int)(rate1%60);
                String rate_total_s=Integer.toString((int)(rate1%60));
                if (rate_total_s.length()==1) {
                    String rate_total1=(int)(rate1/60)+":0"+(int)(rate1%60);
                    tpredkosc.setText(rate_total1);
                }
                else {
                    tpredkosc.setText(rate_total);
                }
            }
        }

        if (distance.length()==5) {
            String time = chronometer.getText().toString();
            String time1 = time.substring(0, 2);
            String time2 = time.substring(3, 5);
            String time3 = time.substring(6, 8);
            double time1_double = Double.valueOf(time1);
            double time2_double = Double.valueOf(time2);
            double time3_double = Double.valueOf(time3);
            double time1_s = time1_double * 60 * 60;
            double time2_s = time2_double * 60;
            double time_total = time1_s + time2_s + time3_double;

            if (distance_time.size()>0) {
                String time_array = distance_time.get(distance_time.size() - 1);

                String time1_array = time_array.substring(0, 2);
                String time2_array = time_array.substring(3, 5);
                String time3_array = time_array.substring(6, 8);

                double time1_array_double = Double.valueOf(time1_array);
                double time2_array_double = Double.valueOf(time2_array);
                double time3_array_double = Double.valueOf(time3_array);
                double time1_array_s = time1_array_double * 60 * 60;
                double time2_array_s = time2_array_double * 60;
                double time_array_total = time1_array_s + time2_array_s + time3_array_double;

                String distance1 = distance.substring(0,2);
                String distance2 = distance.substring(3,4);
                String distance3 = distance.substring(4,5);
                double distance1_double = Double.valueOf(distance1);
                double distance2_double = Double.valueOf(distance2);
                double distance3_double = Double.valueOf(distance3);
                //double distance_total = distance1_double * 1000 + distance2_double * 100+distance3_double*10;
                double distance_total = distance2_double*100+distance3_double*10;

                double time_total_a=time_total-time_array_total;
                double rate = ((distance_total / time_total_a)*60*60)/1000;
                double rate1=3600/rate;

                String rate_total=(int)(rate1/60)+":"+(int)(rate1%60);

                tpredkosc.setText(rate_total);
            }
            else {

                String distance1 = distance.substring(0,2);
                String distance2 = distance.substring(3,4);
                String distance3 = distance.substring(4,5);
                double distance1_double = Double.valueOf(distance1);
                double distance2_double = Double.valueOf(distance2);
                double distance3_double = Double.valueOf(distance3);
                //double distance_total = distance1_double * 1000 + distance2_double * 100+distance3_double*10;
                double distance_total = distance2_double * 100 + distance3_double * 10;

                double rate = ((distance_total / time_total) * 60 * 60) / 1000;
                double rate1=3600/rate;

                String rate_total=(int)(rate1/60)+":"+(int)(rate1%60);

                tpredkosc.setText(rate_total);
            }
        }
        return sum;

    }

    void configure_button() {

        tDystans = (TextView) findViewById(R.id.tDystans);
        time = (TextView) findViewById(R.id.time);
        twysokosc = (TextView) findViewById(R.id.twysokosc);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //start();
        listener = new LocationListener() {
            double distance_Coordinates;
            @Override
            public void onLocationChanged(Location location) {
                // textView.append("n " + location.getLongitude() + " " + location.getLatitude());
                previous_Lon=current_Lon;
                previous_Lat=current_Lat;
                current_Lon = location.getLongitude();
                current_Lat = location.getLatitude();
                current_Alt=location.getAltitude();

                if (current_Alt>distance_height.get(distance_height.size()-1)) {
                    double c_height=current_Alt-distance_height.get(distance_height.size()-1);

                   // distance_height.add(current_Alt);
                    distance_height_sum.add(c_height);
                    distance_height.add(current_Alt);
                    sumHeight();
                   // twysokosc.setText(new DecimalFormat("###.##").format(current_Alt));
                }

                distance_Coordinates = distanceInKmBetweenEarthCoordinates(previous_Lat, previous_Lon, current_Lat, current_Lon);
                distance.add(distance_Coordinates);
                sumDistance();
            }

            public double degreesToRadians(double degrees) {
                return degrees * Math.PI / 180;
            }

            public double distanceInKmBetweenEarthCoordinates(double lat1,double lon1,double lat2,double lon2) {
                double earthRadiusKm = 6371;

                double dLat = degreesToRadians(lat2-lat1);
                double dLon = degreesToRadians(lon2-lon1);

                lat1 = degreesToRadians(lat1);
                lat2 = degreesToRadians(lat2);

                double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                return earthRadiusKm * c;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 23 ) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}
                        , 10);

            }

            return;
        }

        locationManager.requestLocationUpdates("gps", 0, 0,listener);

    }

    @Override
    public void onBackPressed() {

    }


    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
        time.setText(""+startTime);
    }


    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    //elaspsed time in milliseconds
    public long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.currentTimeMillis() - startTime)/1000;
        } else {
            elapsed = (stopTime - startTime)/1000;
        }
        time.setText(""+elapsed);
        return elapsed;
    }

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeMilliSecond=SystemClock.uptimeMillis()-startTime;
            updateTime=timeSwapBuff+timeMilliSecond;
            int secs=(int)(updateTime/1000);
            int mins=secs/60;
            secs%=60;
            time.setText(""+mins+":"+String.format("%2d",secs));
            customHandler.postDelayed(this,0);
        }
    };

    public void dialogevent(View view) {
        bstop = (Button) findViewById(R.id.bstop);
       // bstop.setVisibility(View.INVISIBLE);
        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(MainActivity.this);
                altdial.setMessage("Czy na pewno chcesz zakończyć trening?").setCancelable(false)
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                postComment();
                                Intent i = new Intent(MainActivity.this, PauseActivity.class);
                                MainActivity.this.startActivity(i);
                            }
                        })
                        .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = altdial.create();
                alert.setTitle("UWAGA!");
                alert.show();
            }
        });
    }

/*
    public void startChronometer(View v) {
        if (!running) {
            configure_button();
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            bstart.setVisibility(View.INVISIBLE);
            bpause.setVisibility(View.VISIBLE);

            bstop.setVisibility(View.INVISIBLE);
            running=true;
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime()-chronometer.getBase();
            locationManager.removeUpdates(listener);
            bstop.setVisibility(View.VISIBLE);
            bpause.setVisibility(View.INVISIBLE);
            bstart.setVisibility(View.VISIBLE);
            running=false;
        }

        android:onClick="startChronometer"
        android:onClick="pauseChronometer"


    }

*/

    private void postComment() {
       String dystans = tDystans.getText().toString();
       String wysokosc = twysokosc.getText().toString();

        distance_string.add(dystans);
        distance_string.add(wysokosc);

       //Post post = new Post(dystans);

        int i;
        /*for (i = 1; i < distance.size(); i++) {
            String strSum = String.valueOf(distance.get(i));
            strSum=strSum.replace(".",",");
            distance_string.add(strSum);
        }

       for(String laps : distance_string) {
           reference.child("training").child(laps).setValue(true);
        } */

        for (i = 0; i < distance_lap.size(); i++) {
            String strLap = String.valueOf(distance_lap.get(i));
            strLap=strLap.replace(".",",");
            distance_string.add(strLap);
        }

        for (i = 0; i < distance_time.size(); i++) {
            String strTime = String.valueOf(distance_time.get(i));

            distance_string.add(strTime);
        }

       //for(String laps : distance_string) {
         //  reference.child("training").child(laps).setValue(true);
       // }
      // reference.push().setValue(post);
       // reference.child("training").setValue(post);

    }


}
