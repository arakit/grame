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
	
	
	//緯度
	public double latitude;
	//経度
	public double longitude;
	//標高
	public double altitude;

	static float gx, gy, gz = 0; //x,y,z軸の傾き
	static float direct, pitch, rall = 0; //方位,ピッチ,ロール
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i("System.out" , "メインアクティビティ");

		//カメラ
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		FrameLayout ct = (FrameLayout) findViewById(R.id.container);
		ViewTest vt = new ViewTest(this);
		CameraUkete cv = new CameraUkete(this);

		//vt.getHolder().setFormat( PixelFormat.TRANSLUCENT );

		//AR画面
		ct.addView(vt);
		//カメラ画面
		ct.addView(cv);
		
		//mirai AR(OverLay)
		//ct.addView(new OverLay(this), new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
		
		//setContentView(ct);

		/*
		Gps gps = new Gps();
		//gps.onCreate(savedInstanceState);
		Log.i("System.out" , "緯度" + gps.GetLatitude() );
		Log.i("System.out" , "経度" + gps.GetLongitude() );
		Log.i("System.out" , "高度" + gps.GetAltitude() );

		InclinationSensor is = new InclinationSensor();
		//is.onCreate(savedInstanceState);
		Log.i("System.out" , "方位角" + is.GetDirection() );
		Log.i("System.out" , "傾斜角" + is.GetInclination() );
		Log.i("System.out" , "回転角" + is.GetRotation() );
		*/
		Log.i("System.out" , "終わり");
		
		sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		 
	    //センサーの取得
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
	
	//アプリの開始
	   protected void onResume(){
	      //アプリの開始
	      super.onResume();
	 
	      //センサーの処理の開始
	      if (accelerometer!=null)
	    	  sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
	      if (orientation!=null)
	    	  sensorManager.registerListener(this,orientation,SensorManager.SENSOR_DELAY_FASTEST);
	   }
	 
	   //アプリの停止
	   protected void onStop(){
	      //センサーの処理の停止
	      sensorManager.unregisterListener(this);
	 
	      //アプリの停止
	      super.onStop();
	   }   
	 
	   //センサーリスナーの処理
	   public void onSensorChanged(SensorEvent event){
		  //加速度の取得
		   if (event.sensor==accelerometer){
		    	  gx = -event.values[0];
		    	  gy = event.values[1];
		    	  gz = event.values[2];
		      }
		  //方位の取得
		   if (event.sensor==orientation){
		    	  direct = event.values[0];
		    	  pitch = -event.values[1];
		    	  rall = -event.values[2];
		      }
	   }
	 
	   //精度変更イベントの処理
	   public void onAccuracyChanged(Sensor sensor,int accuracy){}
	 
	   // 破棄の際に実行
	   public void onDestroy(){
	      super.onDestroy();
	   }
}