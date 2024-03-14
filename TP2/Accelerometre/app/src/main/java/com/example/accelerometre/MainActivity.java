package com.example.accelerometre;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ConstraintLayout layoutBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutBackground = findViewById(R.id.layoutBackground);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Pas nécessaire pour cet exemple
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        double magnitude = Math.sqrt(x*x + y*y + z*z);

        final double SEUIL_BAS = 9.81;
        final double SEUIL_HAUT = 12;

        if(magnitude < SEUIL_BAS) {
            layoutBackground.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        } else if (magnitude < SEUIL_HAUT) {
            layoutBackground.setBackgroundColor(getResources().getColor(android.R.color.black));
        } else {
            layoutBackground.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
