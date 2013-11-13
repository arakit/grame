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

public class InclinationSensor implements SensorEventListener
{
	private SensorManager manager;

	public float direction = 0.0f;	//方位角
	public float inclination = 0.0f;	//傾斜角
	public float rotation = 1.0f;	//回転角


	//コンストラクタ
	public InclinationSensor(Context context)
	{
		//Log.i("System.out" , "傾きセンサーコンストラクタ");
		//傾きセンサーサービス取得？
		manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
	}

	//傾きセンサー取得
	public void getInclination()
	{
		//Log.i("System.out" , "傾きセンサー取得");
		// Listenerの登録
		List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ORIENTATION);
		if(sensors.size() > 0)
		{
			Sensor s = sensors.get(0);
			manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
		}
	}

	//傾きセンサー解除？
	public void stopInclination()
	{
		Log.i("System.out" , "傾きセンサー解除？");
		// Listenerの登録解除
		manager.unregisterListener(this);
	}

	//センサーの精度が変更される毎に呼ばれる。引数（センサー, センサーの新しい精度）
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		//Log.i("System.out" , "傾きセンサー精度変更");
	}

	//センサーの値が変更される毎に呼ばれる
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		//Log.i("System.out" , "傾きセンサー値変更");
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION)
		{
			direction = event.values[0];
			inclination = event.values[1];
			rotation = event.values[2];
		}
	}

	//方位角
	public float GetDirection()
	{
		return direction;
	}

	//傾斜角
	public float GetInclination()
	{
		return inclination + 90.0f;
	}

	//回転角
	public float GetRotation()
	{
		return rotation;
	}
}