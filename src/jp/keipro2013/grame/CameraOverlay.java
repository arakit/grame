package jp.keipro2013.grame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.*;
import android.view.*;
import android.graphics.Canvas;
import android.graphics.Color;
 
public class CameraOverlay extends View {
  private Bitmap icon;
  int width;
  int height;
 
  public CameraOverlay(Context context) {
    super(context);
    setDrawingCacheEnabled(true);           
    icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.shutter);
    setFocusable(true);
  }
   
  protected void onSizeChanged(int w, int h, int oldw, int oldh){
    width= w;
   height= h;
  }
 
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.TRANSPARENT);
  }
}