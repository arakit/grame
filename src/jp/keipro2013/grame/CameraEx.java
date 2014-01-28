package jp.keipro2013.grame;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
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

public class CameraEx extends Activity implements LocationListener, View.OnClickListener,SurfaceHolder.Callback,Camera.PictureCallback, SensorEventListener{
	static double latitude,lat;
	static double longtude,lon;
	static double altitude,alt;
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private Sensor orientation;
	int w,h;
	static float d,p,r,x,y,z;
	static float gx, gy, gz = 0;
	static float direct, pitch, roll = 0;
	
	HttpResponse httpResponse;
	static String ALT;
	
	CameraOverlay overlay;
	Camera camera;
	private ImageButton imageButton,imageButton2,imageButton3;
	
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

     	WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
     	Display dp = wm.getDefaultDisplay();
     	w = dp.getWidth();
     	h = dp.getHeight();
        
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new CameraView(this));
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
		image = BitmapFactory.decodeResource(getResources(), R.drawable.nont);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		
        	new AlertDialog.Builder(this)
        	    	.setCancelable(false)    	
        	    	.setMessage("メッセージ作成するための写真撮影を行います。")    	
        	    	.setPositiveButton("はい",null)    	
        	    	.show();
        	
        LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		//criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		//criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = mLocationManager.getBestProvider(criteria, true);
		mLocationManager.requestLocationUpdates(provider, 0, 0, this);
		//mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == imageButton){
			Intent intent1 = new Intent(CameraEx.this, SakuseiMenu.class);
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
			
			getAltitude(lat,lon);
			
			CameraView.camera.takePicture(null,null,this);
			CameraView.a=1;
		}
	}

	@Override
	public void onPictureTaken(byte[] data,Camera camera) {
		
		File file = new File(Environment.getExternalStorageDirectory() + "/drawbm/");
		try {
			if (!file.exists()) {
				file.mkdir();
			}
		}catch (SecurityException e) {
			}
		
        try {
        	String path=file.getAbsolutePath();
        	path += "/" + "test.jpg";
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
    	intent.setClassName("jp.keipro2013.grame", "jp.keipro2013.grame.Preview");
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
	
private void getAltitude(double lat,double lon){
        
        HttpClient httpClient = new DefaultHttpClient();
        //Google Elevation APIサービスにアクセスして標高取得
    //ここではJSONではなくXMLで結果を返すようにしています
        String uri = "http://maps.googleapis.com/maps/api/elevation/xml?locations=" + lat + "," + lon + "&sensor=true";
        HttpGet request = new HttpGet(uri);
        
        
        try {
            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
        	
        }
        
        //int status = httpResponse.getStatusLine().getStatusCode();
         
        //if (HttpStatus.SC_OK == status) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(outputStream);
        //パーサーに渡す
                xml_parse(outputStream.toString());
            } catch (Exception e) {

            }
       // } else {

        //}
    }
    
    //XMLパーサーで解析して標高値を取り出す
    private void xml_parse(String str) {
        try{
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(new StringReader(str));
     
            int eventType;
            while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && "elevation".equals(xmlPullParser.getName())) {
                    //Log.d("HttpSampleActivity", xmlPullParser.nextText());
                    ALT = xmlPullParser.nextText();
            //標高値の桁数調整
                double temp = Double.parseDouble(ALT);
                temp = temp * 100;
                temp = Math.ceil(temp);
                temp = temp / 100;
                ALT = String.valueOf(temp);
                    }
            }
        } catch (Exception e){

        }
    }

}