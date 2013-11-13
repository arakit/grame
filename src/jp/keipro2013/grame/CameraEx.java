package jp.keipro2013.grame;

import java.io.FileOutputStream;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
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

public class CameraEx extends Activity implements LocationListener, View.OnClickListener,SurfaceHolder.Callback,Camera.PictureCallback {
	static double latitude,lat; //�ܓx
	static double longtude,lon; //�y�x
	int w,h;
	
	CameraOverlay overlay;
	Camera camera;
	private ImageButton imageButton,imageButton2,imageButton3;
	
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        // WindowManager�擾
     	WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
     	// Display�C���X�^���X����
     	Display dp = wm.getDefaultDisplay();
     	// �f�B�X�v���C�T�C�Y�擾
     	w = dp.getWidth();
     	h = dp.getHeight();
        
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new CameraView(this));
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.modoru);
        image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
        imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.shutter);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.no);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		//imageButton.setOnClickListener((OnClickListener) this);
		// setLLParams(imageButton);
		
	    //lpb.gravity =Gravity.BOTTOM;
	    
		//CameraView.addView(imageButton, new AbsoluteLayout.LayoutParams(dp_w / 3, 150, dp_w/3, dp_h /3+100));
        
        //if(Preview.d!=1){
        	new AlertDialog.Builder(this)
        	    	.setCancelable(false)    	
        	    	.setMessage("���b�Z�[�W�쐬���邽�߂̎ʐ^�B�e���s���܂��B")    	
        	    	.setPositiveButton("�͂�",null)    	
        	    	.show();
        //}
        LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		// PowerRequirement���w��(�����d��)
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		// ���P�[�V�����v���o�C�_�̎擾
		String provider = mLocationManager.getBestProvider(criteria, true);
		mLocationManager.requestLocationUpdates(provider, 0, 0, this);
		
		//overlay = new CameraOverlay(this);
	    //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    //addContentView(overlay, lp);
	 
	    //Button button_shutter = new Button(this);
	    //button_shutter.setText("�B�e");
	    //LayoutParams lpb = new LayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.BOTTOM));
	    //LayoutParams lps = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    //lpb.gravity =Gravity.BOTTOM;
	    //addContentView(button_shutter, lpb);
	    //addContentView(imageButton, lpb);
	    //addContentView(imageButton, lpb);
	    //addContentView(imageButton2, lps);
	    /*button_shutter.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	        ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME);
	        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
	 
	        mCamera.takePicture(mShutterListener, rawListener, jpegListener);
	      }
	    });*/
	    /*
	    RelativeLayout relativeLayout = new RelativeLayout(this);
	    
	    RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	     
	    // �}�[�W�����w��i���A��A�E�A���j
	    param.setMargins(5, 5, 15, 0);
	    param2.setMargins(5, 5, 15, 0);
	    param3.setMargins(5, 5, 15, 0);
	    param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 2);
	    param2.addRule(RelativeLayout.CENTER_HORIZONTAL, 2);
	    param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 2);
	    relativeLayout.addView(imageButton, param);
	    relativeLayout.addView(imageButton2, param2);
	    relativeLayout.addView(imageButton3, param3);
	    */
		AbsoluteLayout layout = new AbsoluteLayout(this);
	    addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    //AbsoluteLayout layout = new AbsoluteLayout(this);
	    layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w / 3, h/10, 0, h-h/10));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3, h-h/10));
		layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3*2, h-h/10));
    }
    
    public void onLocationChanged(Location location) {
		// �ܓx�̕\��
		// TextView tv_lat = (TextView) findViewById(R.id.textView1);
		// tv_lat.setText("�ܓx:"+location.getLatitude());
		latitude = location.getLatitude();
		// �o�x�̕\��
		// TextView tv_lng = (TextView) findViewById(R.id.textView3);
		// tv_lng.setText("�o�x:"+location.getLongitude());
		longtude = location.getLongitude();
	}
    
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(CameraView.a==1){
        if (event.getAction()==MotionEvent.ACTION_DOWN) {
        	// TODO Auto-generated method stub
    			Intent intent1 = new Intent(CameraEx.this, Preview.class);
    			try {
    				startActivity(intent1);
    			} catch (Exception e) {

    			}
        }
    	}
        return true;
    }*/

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
			
			CameraView.camera.takePicture(null,null,this);
			CameraView.a=1;
		}
	}

	@Override
	public void onPictureTaken(byte[] data,Camera camera) {
        try {
        	String path=Environment.getExternalStorageDirectory() + "/drawbm/test.jpg";
            //String path=Environment.getExternalStorageDirectory().getPath() + "/drawbm/test.jpg";
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
    	intent.setClassName("com.example.graffitimessage", "com.example.graffitimessage.Preview");
    	startActivity(intent);
    	}

	@Override
	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder holder,int format,int w,int h) {
    	if(camera != null) {
            camera.stopPreview();
 
            // �c��ʑΉ�
            camera.setDisplayOrientation(90);
 
            // �J�����̏ꍇ�A�c�����t�Ȃ̂œ���ւ�
            int temp = w;
            w = h;
            h = temp;
            //int prevWidth = 100, prevHeight = 200, picWidth = 400, picHeight = 800;
            Camera.Parameters params = camera.getParameters();
 
            //�t���b�V��
            params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
            
            // �v���r���[�T�C�Y�̊m��
            //params.setPreviewFormat(format);
            //params.setPreviewSize(prevWidth, prevHeight);
            //params.setPreviewSize(w,h);
 
            // �ʐ^�T�C�Y�̊m��
            //params.setPictureFormat(format);
            //params.setPictureSize(picWidth, picHeight);
            //params.setPictureSize(720,1280);
 
            // jpg�ۑ����̉�]��Ԏw��
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
				// �_�C�A���O�\���ȂǓ���̏������s�������ꍇ�͂����ɋL�q
				// �e�N���X��dispatchKeyEvent()���Ăяo������true��Ԃ�
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

}