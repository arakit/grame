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

	public float direction = 0.0f;	//���ʊp
	public float inclination = 0.0f;	//�X�Ίp
	public float rotation = 1.0f;	//��]�p


	//�R���X�g���N�^
	public InclinationSensor(Context context)
	{
		//Log.i("System.out" , "�X���Z���T�[�R���X�g���N�^");
		//�X���Z���T�[�T�[�r�X�擾�H
		manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
	}

	//�X���Z���T�[�擾
	public void getInclination()
	{
		//Log.i("System.out" , "�X���Z���T�[�擾");
		// Listener�̓o�^
		List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ORIENTATION);
		if(sensors.size() > 0)
		{
			Sensor s = sensors.get(0);
			manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
		}
	}

	//�X���Z���T�[�����H
	public void stopInclination()
	{
		Log.i("System.out" , "�X���Z���T�[�����H");
		// Listener�̓o�^����
		manager.unregisterListener(this);
	}

	//�Z���T�[�̐��x���ύX����閈�ɌĂ΂��B�����i�Z���T�[, �Z���T�[�̐V�������x�j
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		//Log.i("System.out" , "�X���Z���T�[���x�ύX");
	}

	//�Z���T�[�̒l���ύX����閈�ɌĂ΂��
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		//Log.i("System.out" , "�X���Z���T�[�l�ύX");
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION)
		{
			direction = event.values[0];
			inclination = event.values[1];
			rotation = event.values[2];
		}
	}

	//���ʊp
	public float GetDirection()
	{
		return direction;
	}

	//�X�Ίp
	public float GetInclination()
	{
		return inclination + 90.0f;
	}

	//��]�p
	public float GetRotation()
	{
		return rotation;
	}
}