package jp.keipro2013.grame;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class InclinationSensor implements SensorEventListener{
	private SensorManager manager;

	public float direction = 0.0f;
	public float inclination = 0.0f;
	public float rotation = 1.0f;

	public InclinationSensor(Context context){
		manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
	}

	public void getInclination(){
		List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ORIENTATION);
		if(sensors.size() > 0){
			Sensor s = sensors.get(0);
			manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
		}
	}
	
	public void stopInclination(){
		manager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy){
		
	}

	@Override
	public void onSensorChanged(SensorEvent event){
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
			direction = event.values[0];
			inclination = event.values[1];
			rotation = event.values[2];
		}
	}

	public float GetDirection(){
		return direction;
	}

	public float GetInclination(){
		return inclination + 90.0f;
	}

	public float GetRotation(){
		return rotation;
	}
}