package com.aston.basketballapp.utils.motionsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope {

    //Interface to allow values of dx,dy and dz to be returned outside of this class.
    public interface GyroscopeListener {
        void onRotation(float dx, float dy, float dz);
    }

    private GyroscopeListener listener;
    public void setGyroscopeListener(GyroscopeListener _listener) {
        listener = _listener;
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    //Constructor for Gyroscope
    public Gyroscope(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(listener != null) {
                    listener.onRotation(event.values[0],event.values[1], event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    //Register the Gyroscope
    public void register() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //Remove the Gyroscope register
    public void unregister() {
        sensorManager.unregisterListener(sensorEventListener);
    }
}
