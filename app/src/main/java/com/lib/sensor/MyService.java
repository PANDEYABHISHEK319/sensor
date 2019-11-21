package com.lib.sensor;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private Context abc;
    SensorEventListener eventListener;



    public MyService(Context applicationContext) {
        super();
        abc =  applicationContext;
    }
    public MyService(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);

        if(abc!=null) {
            sensorManager = (SensorManager) abc.getSystemService(SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
            registerCallback(sensorManager);
        }

//
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterCallback(sensorManager);
        super.onDestroy();
    }

    void registerCallback(SensorManager callback){

        if(callback != null){
            callback.registerListener((SensorEventListener) abc,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }



    }
    void unregisterCallback(SensorManager callback){

        if(callback != null){
            callback.unregisterListener((SensorEventListener) abc);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d("HEllo_valuue",""+sensorEvent.values[0]+" "+sensorEvent.values[1]+" "+sensorEvent.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        Log.d("HEllo_valuue",sensor.toString()+ " " +i );

    }
}
