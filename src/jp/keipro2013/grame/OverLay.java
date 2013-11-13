package jp.keipro2013.grame;


import java.util.Random;

import android.content.Context; 
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.view.View;

public class OverLay extends View{
	private int width, height; 
	Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.d);
	Bitmap image2;
	int w = image.getWidth();
	int h = image.getHeight();
	int w2 = image.getWidth();
    int h2 = image.getHeight();
	int deg;
	int v=1;
	int d,e;
	Matrix mat = new Matrix();
	Random rnd = new Random();
	
	private float gx, gy, gz = 0; //x,y,z軸の傾き
	private float direct, pitch, rall = 0; //方位,ピッチ,ロール
	 public OverLay(Context context) {  
	  super(context);  
	  setFocusable(true);  
	 }  
	  
	 @Override  
	 protected void onSizeChanged(int w, int h, int oldw, int oldh){  
	  // ビューのサイズを取得  
	  width= w;  
	  height= h;
	 }  
	  
	  
	 /** 
	  * 描画処理 
	  */  
	 @Override  
	 protected void onDraw(Canvas canvas) {  
	  super.onDraw(canvas);  
	  // 背景色を設定  
	  canvas.drawColor(Color.TRANSPARENT);
	  
      int ran = rnd.nextInt(360);
      int ran2 = rnd.nextInt(50);
      
      //画像の遠近
      //image = Bitmap.createScaledBitmap(image, 300-ran2, 300-ran2, false);
      //int w2 = image.getWidth();
      //int h2 = image.getHeight();
      
      int ran3 = rnd.nextInt(720);
      int ran4 = rnd.nextInt(1280);
	  //画像の回転、移動
      d = (int)MainActivity.direct*25;
      e = (int)MainActivity.pitch;
	  deg = (int)MainActivity.rall;
	  //mat.postRotate(0);
	  mat.postRotate(0);
	  //image = Bitmap.createScaledBitmap(image, 300, 300, false);
	  image2 = Bitmap.createBitmap(image, 0, 0, w, h, mat, true);
	  canvas.drawBitmap(image, w*2-d, h+h/2 + e*50 - 90*50, null);
	
	  invalidate();
	 }
}
