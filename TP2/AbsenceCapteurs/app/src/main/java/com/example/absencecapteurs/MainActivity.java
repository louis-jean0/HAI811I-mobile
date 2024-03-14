package com.example.absencecapteurs;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {
    private SensorManager sensorManager;
    private TextView sensorStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorStatusTextView = findViewById(R.id.tvSensorStatus);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkSensorAvailability();
            }
        }, 3000);
    }

    private void checkSensorAvailability() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //if (sensor == null) {
        sensorStatusTextView.setText("Capteur d'accéléromètre non disponible. Certaines fonctionnalités sont limitées.");
        //} else {
        //sensorStatusTextView.setText("Capteur d'accéléromètre disponible. Toutes les fonctionnalités sont actives.");
        //}
    }
}