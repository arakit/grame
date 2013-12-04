package jp.keipro2013.grame;

import java.io.FileOutputStream;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Camera.PictureCallback;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class RouteActivity extends Activity implements LocationListener, View.OnClickListener,SurfaceHolder.Callback,Camera.PictureCallback,SensorEventListener{
	static double latitude, lat;
	static double longtude, lon;
	static double altitude,alt;
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private Sensor orientation;
	static float d,p,r,x,y,z;
	static float gx, gy, gz = 0;
	static float direct, pitch, roll = 0;
	Camera camera;
	private ImageButton imageButton,imageButton2,imageButton3;
	int w,h;
	
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
 
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new RouteView(this));
        
     	WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
     	Display dp = wm.getDefaultDisplay();
     	w = dp.getWidth();
     	h = dp.getHeight();
        
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.backt);
        image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
        imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.camerat);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.order);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		imageButton3.setOnClickListener(this);

        	new AlertDialog.Builder(this)
        	    	.setCancelable(false)    	
        	    	.setMessage("メッセージに辿り着くためのルートを作成するために写真を撮ります。")    	
        	    	.setPositiveButton("はい",null)    	
        	    	.show();
        LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = mLocationManager.getBestProvider(criteria, true);
		mLocationManager.requestLocationUpdates(provider, 0, 0, this);
		
		AbsoluteLayout layout = new AbsoluteLayout(this);
	    addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w/3, h/10, 0, h-h/10));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3, h-h/10));
		layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3*2, h-h/10));
		
		sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> list;
	    list=sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
	    if (list.size()>0) accelerometer = list.get(0);
	    list=sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
	    if (list.size()>0) orientation = list.get(0);
    }
    
    public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longtude = location.getLongitude();
		altitude = location.getAltitude();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
	
	public void onClick(View v) {
		if (v == imageButton){
			Intent intent1 = new Intent(RouteActivity.this, SakuseiMenu.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}else if (v == imageButton2){
			lat = latitude;
			lon = longtude;
			alt = altitude;
			d = direct;
			p = pitch;
			r = roll;
			x = gx;
			y = gy;
			z = gz;
			
			RouteView.camera.takePicture(null,null,this);
			RouteView.a=1;
		}else if (v == imageButton3){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setMessage("ルートの順番を決めますか？");
			alertDialogBuilder.setNegativeButton("はい",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							
						}
			});
			alertDialogBuilder.setNeutralButton("いいえ",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int which) {

				}
			});
			alertDialogBuilder.setCancelable(false);
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			}
		}

	@Override
	public void onPictureTaken(byte[] data,Camera camera) {
        try {
        	String path=Environment.getExternalStorageDirectory() + "/drawbm/route.jpg";
            data2file(data,path);
            System.out.println("");
        } catch (Exception e) {
        	System.out.println("" + e.getMessage());
        }
        camera.startPreview();
    }
	
	private void data2file(byte[] w,String fileName) 
	        throws Exception {
	        FileOutputStream out=null;
	        try {
	            out=new FileOutputStream(fileName);
	            out.write(w);
	            out.close();
	            Intentto();
	        } catch (Exception e) {
	            if (out!=null) out.close();
	            throw e;
	        }
	    }
	
	public void Intentto(){
    	Intent intent = new Intent();
    	intent.setClassName("jp.keipro2013.grame", "jp.keipro2013.grame.RoutePreview");
    	startActivity(intent);
    	}

	@Override
	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder holder,int format,int w,int h) {
    	if(camera != null) {
            camera.stopPreview();

            camera.setDisplayOrientation(90);
            
            int temp = w;
            w = h;
            h = temp;
            Camera.Parameters params = camera.getParameters();
 
            params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);

            params.setRotation(90);
 
            camera.setParameters(params);
            camera.startPreview();
    	}
    }

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
    	camera=Camera.open();
    	try {
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
        }
    }

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera=null;
    }
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_BACK:
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

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor==accelerometer){
	    	  gx = -event.values[0];
	    	  gy = event.values[1];
	    	  gz = event.values[2];
	      }
	   if (event.sensor==orientation){
	    	  direct = event.values[0];
	    	  pitch = -event.values[1];
	    	  roll = -event.values[2];
	      }
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}