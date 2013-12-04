package jp.keipro2013.grame;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements SensorEventListener{
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private Sensor orientation;
	private OverLay ov;
	
	public double latitude;
	public double longitude;
	public double altitude;

	static float gx, gy, gz = 0;
	static float direct, pitch, rall = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		FrameLayout ct = (FrameLayout) findViewById(R.id.container);
		ViewTest vt = new ViewTest(this);
		CameraUkete cv = new CameraUkete(this);

		//AR
		//ct.addView(vt);
		//camera
		ct.addView(cv);
		
		//mirai AR(OverLay)
		ct.addView(new OverLay(this), new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
		
		//setContentView(ct);
		
		sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

	    List<Sensor> list;
	    list=sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
	    if (list.size()>0) accelerometer = list.get(0);
	    list=sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
	    if (list.size()>0) orientation = list.get(0);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_BACK:
				Intent intent1 = new Intent(MainActivity.this, Title.class);
				try {
					startActivity(intent1);
				} catch (Exception e) {

				}
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	   protected void onResume(){
	      super.onResume();

	      if (accelerometer!=null)
	    	  sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
	      if (orientation!=null)
	    	  sensorManager.registerListener(this,orientation,SensorManager.SENSOR_DELAY_FASTEST);
	   }

	   protected void onStop(){
	      sensorManager.unregisterListener(this);
	 
	      super.onStop();
	   }   
	 
	   public void onSensorChanged(SensorEvent event){
		   if (event.sensor==accelerometer){
		    	  gx = -event.values[0];
		    	  gy = event.values[1];
		    	  gz = event.values[2];
		      }
		   if (event.sensor==orientation){
		    	  direct = event.values[0];
		    	  pitch = -event.values[1];
		    	  rall = -event.values[2];
		      }
	   }

	   public void onAccuracyChanged(Sensor sensor,int accuracy){
		   
	   }
	 
	   public void onDestroy(){
	      super.onDestroy();
	   }
}