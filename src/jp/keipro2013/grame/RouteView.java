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
    
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera=null;
    }
    
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            camera.takePicture(null,null,this);
            a=1;
        }
        return true;
    }*/

    public void onPictureTaken(byte[] data,Camera camera) {
        try {
        	String path=Environment.getExternalStorageDirectory() + "/drawbm/route.jpg";
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
