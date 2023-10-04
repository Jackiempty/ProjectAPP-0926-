package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private TextView acce;
    private TextView gryo;
    private TextView magn;

    private SensorManager mSensorManager;
    private Sensor mAccelerometers;
    private Sensor mGyroscope;
    private Sensor mMagnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acce = (TextView)findViewById(R.id.accelerometers);
        gryo = (TextView)findViewById(R.id.gyroscopes);
        magn = (TextView)findViewById(R.id.magnetometers);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometers = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mAccelerometers,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mMagnetometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        String values = "X-axis:" + String.valueOf(event.values[0]) + "\n"
                + "Y-axis:" + String.valueOf(event.values[1]) + "\n"
                + "Z-axis:" + String.valueOf(event.values[2]);
        if (event.sensor.equals(mAccelerometers))
            acce.setText(values);
        if(event.sensor.equals(mGyroscope))
            gryo.setText(values);
        if(event.sensor.equals(mMagnetometer))
            magn.setText(values);
    }
}