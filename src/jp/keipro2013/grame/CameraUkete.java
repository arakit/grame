package jp.keipro2013.grame;

import java.io.FileOutputStream;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Camera;

@SuppressLint("DrawAllocation")
public class CameraUkete extends SurfaceView implements SurfaceHolder.Callback,Camera.PictureCallback {

    private Camera camera;
    private SurfaceHolder holder;
    private Bitmap com;

	public CameraUkete(Context context) {
        super(context);

        Resources r=context.getResources();
        com=BitmapFactory.decodeResource(r, R.drawable.aaa);

        holder=getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);
    }

    @SuppressLint("DrawAllocation")
	@Override
    protected void onDraw(Canvas canvas){
    	canvas.drawBitmap(com, 0, 0, null);

    	int w=com.getWidth();
    	int h=com.getHeight();
    	Rect src=new Rect(0,0,w,h);
    	Rect dst=new Rect(0,200,w*2,200+h*2);
    	canvas.drawBitmap(com, src, dst, null);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
        	camera=Camera.open();
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
            
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    	if(camera != null)
    	{
    		camera.stopPreview();
    		camera.setDisplayOrientation(90);
    		Camera.Parameters params = camera.getParameters();
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

    @Override
    public boolean onTouchEvent(MotionEvent event ){
    	if( event.getAction()==MotionEvent.ACTION_DOWN ){
    		
    	}
    	return true;
    }
    
    public void onPictureTaken(byte[] data, Camera camera){
    	try{
    		String path=Environment.getExternalStorageDirectory()+"/test.jpg";
    		data2file(data,path);
    	}catch(Exception e){
    		
    	}
    		camera.startPreview();
    		}
    private void data2file(byte[] w, String fileName)
    	throws Exception{
    		FileOutputStream out=null;
    		try{
    			out=new FileOutputStream(fileName);
    			out.write(w);
    			out.close();
    		}catch(Exception e){
    			if( out != null) out.close();
    			throw e;
    		}
    }
}