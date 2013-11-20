package jp.keipro2013.grame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class RouteView extends SurfaceView implements SurfaceHolder.Callback,Camera.PictureCallback {
    private SurfaceHolder holder;
    static Camera        camera;
    static int a=0;
 
    public RouteView(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    
    public void surfaceCreated(SurfaceHolder holder) {
    	camera=Camera.open();
    	try {
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
        }
    }


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
    
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera=null;
    }

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
            drawdialog();
        } catch (Exception e) {
            if (out!=null) out.close();
            throw e;
        }
    }
    
    
    public void drawdialog(){
    	Intent intent = new Intent();
    	intent.setClassName("com.example.graffitimessage", "com.example.graffitimessage.RoutePreview");
    	getContext().startActivity(intent);
    	}


    }
