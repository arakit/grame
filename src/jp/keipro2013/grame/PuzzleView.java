package jp.keipro2013.grame;
import java.io.File;
import java.io.FileOutputStream;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

//イメージの描画
public class PuzzleView extends View {
    private Bitmap image;//イメージ
	private Bitmap bmp,bmp2;
    Bitmap p;
    int abc = 0;
    float a;
    int aa;
    int b;   
    int a3=0;
    int w,h,width, height;
    private Canvas bmpCanvas;
    static Paint paint,paint2,paint3,paint4,paint5,paint6,paint7,paint8;
    private Activity _context;
    //コンストラクタ
    public PuzzleView(Context context) {
        super(context);
        
        _context = (Activity)context;
        
        paint = new Paint();
        paint.setColor(Color.RED);
        //paint.setAntiAlias(true);
        //setBackgroundColor(Color.TRANSPARENT);
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        
        paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(6);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        
        paint3 = new Paint();
        paint3.setColor(Color.RED);
        paint3.setStrokeWidth(6);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeCap(Paint.Cap.ROUND);
        paint3.setStrokeJoin(Paint.Join.ROUND);
        
        paint4 = new Paint();
        paint4.setColor(Color.RED);
        paint4.setStrokeWidth(6);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeCap(Paint.Cap.ROUND);
        paint4.setStrokeJoin(Paint.Join.ROUND);
        
        paint5 = new Paint();
        paint5.setColor(Color.RED);
        paint5.setStrokeWidth(6);
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        paint5.setStrokeJoin(Paint.Join.ROUND);
        
        paint6 = new Paint();
        paint6.setColor(Color.RED);
        paint6.setStrokeWidth(6);
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        paint6.setStrokeJoin(Paint.Join.ROUND);
        
        paint7 = new Paint();
        paint7.setColor(Color.RED);
        paint7.setStrokeWidth(6);
        paint7.setStyle(Paint.Style.STROKE);
        paint7.setStrokeCap(Paint.Cap.ROUND);
        paint7.setStrokeJoin(Paint.Join.ROUND);
        
        paint8 = new Paint();
        paint8.setColor(Color.RED);
        paint8.setStrokeWidth(6);
        paint8.setStyle(Paint.Style.STROKE);
        paint8.setStrokeCap(Paint.Cap.ROUND);
        paint8.setStrokeJoin(Paint.Join.ROUND);
        
        //bmp = Bitmap.createBitmap(1196, 720, Bitmap.Config.ARGB_8888);
        
        //bmpCanvas.drawColor(Color.TRANSPARENT);
        
        //ギャラリーの画像読み込み
        image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/test2.jpg");
        
        w = image.getWidth();
        h = image.getHeight();
        bmp = Bitmap.createBitmap(720, 1200, Bitmap.Config.ARGB_8888);
        Resources res = this.getContext().getResources();
        //bmp2 = BitmapFactory.decodeResource(res, R.drawable.ottsu);
        bmpCanvas = new Canvas(bmp);

    }
    
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	 super.onSizeChanged(w,h,oldw,oldh);
    	 //bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    	 //bmpCanvas = new Canvas(bmp);
    	 
    	 image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/test2.jpg");
         
    	/*int deg = 90;
 		Matrix mat = new Matrix();
 		mat.postRotate( deg );
 		int sw = image.getWidth();
 		int sh = image.getHeight();*/

 		//image = Bitmap.createBitmap(image, 0, 0, sw, sh, mat, true);
 		
 		//image = Bitmap.createScaledBitmap(image, 720, 1200, false);
    	 
         width = image.getWidth();
         height = image.getHeight();
    	 
    	 bmpCanvas.drawBitmap(image, 0, 0, null);
    	 bmpCanvas.drawBitmap(bmp, 0, 0, null);
    		 		 
    	 //bmpCanvas.drawColor(Color.TRANSPARENT);
    	 }

    //描画時に呼ばれる
	@Override 
    public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bmp,0,0,null);
		
		if(PuzzleActivity.a2 == 0){
			//Toast.makeText(_context, "分割する数を選択してください", Toast.LENGTH_LONG).show();
			PuzzleActivity.a2=1;		
		}
			if(PuzzleActivity.a2==2){
				//paint.setColor(Color.RED);
				bmpCanvas.drawBitmap(image, 0, 0, null);
		    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
				//bmpCanvas.drawLine(width/2, 0, width/2, height, paint);
				bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
				/*paint2.setColor(Color.TRANSPARENT);
				paint3.setColor(Color.TRANSPARENT);
				paint4.setColor(Color.TRANSPARENT);
				paint5.setColor(Color.TRANSPARENT);
				paint6.setColor(Color.TRANSPARENT);
				paint7.setColor(Color.TRANSPARENT);
				paint8.setColor(Color.TRANSPARENT);*/
			}
			
				
				else if(PuzzleActivity.a2==3){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
					//bmpCanvas.drawLine(width/3, 0, width/3, height, paint2);
			 		//bmpCanvas.drawLine(width/3*2, 0, width/3*2, height, paint2);
			 		bmpCanvas.drawLine(0, height/3, width, height/3, paint8);
			 		bmpCanvas.drawLine(0, height/3*2, width, height/3*2, paint8);
			 		/*paint2.setColor(Color.RED);
					paint.setColor(Color.TRANSPARENT);
					paint3.setColor(Color.TRANSPARENT);
					paint4.setColor(Color.TRANSPARENT);
					paint5.setColor(Color.TRANSPARENT);
					paint6.setColor(Color.TRANSPARENT);
					paint7.setColor(Color.TRANSPARENT);
					paint8.setColor(Color.TRANSPARENT);*/
				}
			
				else if(PuzzleActivity.a2==4){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			    	
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint);
			    	bmpCanvas.drawLine(0, height/2, width, height/2, paint8);

					/*paint.setColor(Color.RED);
					paint8.setColor(Color.RED);
					
					paint2.setColor(Color.TRANSPARENT);
					paint3.setColor(Color.TRANSPARENT);
					paint4.setColor(Color.TRANSPARENT);
					paint5.setColor(Color.TRANSPARENT);
					paint6.setColor(Color.TRANSPARENT);
					paint7.setColor(Color.TRANSPARENT);*/
					
				}
				
				else if(PuzzleActivity.a2==5){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			    	
			    	bmpCanvas.drawLine(width/2, 0, width/2, height/2, paint3);
			 		bmpCanvas.drawLine(width/3, height/2, width/3, height, paint3);
			 		bmpCanvas.drawLine(width/3*2, height/2, width/3*2, height, paint3);
			 		bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
			 		
			 		/*paint3.setColor(Color.RED);
					paint8.setColor(Color.RED);	
					
					paint.setColor(Color.TRANSPARENT);
					paint2.setColor(Color.TRANSPARENT);
					paint4.setColor(Color.TRANSPARENT);
					paint5.setColor(Color.TRANSPARENT);
					paint6.setColor(Color.TRANSPARENT);
					paint7.setColor(Color.TRANSPARENT);*/
				}
			
				else if(PuzzleActivity.a2==6){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
					
			    	//bmpCanvas.drawLine(width/3, 0, width/3, height, paint2);
			 		//bmpCanvas.drawLine(width/3*2, 0, width/3*2, height, paint2);
			    	//bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint2);
			    	bmpCanvas.drawLine(0, height/3, width, height/3, paint8);
			    	bmpCanvas.drawLine(0, height/3*2, width, height/3*2, paint8);
					/*paint2.setColor(Color.RED);
					paint8.setColor(Color.RED);
					
					paint.setColor(Color.TRANSPARENT);
					paint3.setColor(Color.TRANSPARENT);
					paint4.setColor(Color.TRANSPARENT);
					paint5.setColor(Color.TRANSPARENT);
					paint6.setColor(Color.TRANSPARENT);
					paint7.setColor(Color.TRANSPARENT);*/
				}
				
				else if(PuzzleActivity.a2==7){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
					
			    	bmpCanvas.drawLine(width/3, 0, width/3, height/2, paint4);
			 		bmpCanvas.drawLine(width/3*2, 0, width/3*2, height/2, paint4);
			 		bmpCanvas.drawLine(width/4, height/2, width/4, height, paint4);
			 		bmpCanvas.drawLine(width/2, height/2, width/2, height, paint4);
			 		bmpCanvas.drawLine(width/4*3, height/2, width/4*3, height, paint4);
			 		bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
			 		
			 		/*paint4.setColor(Color.RED);	
					paint8.setColor(Color.RED);
					
					paint.setColor(Color.TRANSPARENT);
					paint2.setColor(Color.TRANSPARENT);
					paint3.setColor(Color.TRANSPARENT);
					paint5.setColor(Color.TRANSPARENT);
					paint6.setColor(Color.TRANSPARENT);
					paint7.setColor(Color.TRANSPARENT);*/
				}
			
				else if(PuzzleActivity.a2==8){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
					
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint);
			    	//bmpCanvas.drawLine(width/4, 0, width/4, height, paint7);
			 		//bmpCanvas.drawLine(width/4*3, 0, width/4*3, height, paint7);
			    	bmpCanvas.drawLine(0, height/4, width, height/4, paint8);
			    	bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
			    	bmpCanvas.drawLine(0, height/4*3, width, height/4*3, paint8);
			    	
					/*paint.setColor(Color.RED);
					paint7.setColor(Color.RED);
					paint8.setColor(Color.RED);	
					
					paint2.setColor(Color.TRANSPARENT);
					paint3.setColor(Color.TRANSPARENT);
					paint4.setColor(Color.TRANSPARENT);
					paint5.setColor(Color.TRANSPARENT);
					paint6.setColor(Color.TRANSPARENT);*/
				}
				
				else if(PuzzleActivity.a2==9){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
					
			 		/*bmpCanvas.drawLine(width/4, 0, width/4, height/2, paint5);
			 		bmpCanvas.drawLine(width/4*2, 0, width/4*2, height/2, paint5);
			 		bmpCanvas.drawLine(width/4*3, 0, width/4*3, height/2, paint5);
			 		bmpCanvas.drawLine(width/5, height/2, width/5, height, paint5);
			 		bmpCanvas.drawLine(width/5*2, height/2, width/5*2, height, paint5);
			 		bmpCanvas.drawLine(width/5*3, height/2, width/5*3, height, paint5);
			 		bmpCanvas.drawLine(width/5*4, height/2, width/5*4, height, paint5);
			    	bmpCanvas.drawLine(0, height/2, width, height/2, paint8);*/
			    	bmpCanvas.drawLine(width/3, 0, width/3, height, paint5);
			    	bmpCanvas.drawLine(width/3*2, 0, width/3*2, height, paint5);
			 		bmpCanvas.drawLine(0, height/3, width, height/3, paint5);
			 		bmpCanvas.drawLine(0, height/3*2, width, height/3*2, paint5);
					/*paint5.setColor(Color.RED);
					paint8.setColor(Color.RED);
					
					paint.setColor(Color.TRANSPARENT);
					paint2.setColor(Color.TRANSPARENT);
					paint3.setColor(Color.TRANSPARENT);
					paint4.setColor(Color.TRANSPARENT);
					paint6.setColor(Color.TRANSPARENT);
					paint7.setColor(Color.TRANSPARENT);*/
				}
								
				else if(PuzzleActivity.a2==10){
					bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
					
			    	/*bmpCanvas.drawLine(width/5, 0, width/5, height, paint6);
			 		bmpCanvas.drawLine(width/5*2, 0, width/5*2, height, paint6);
			 		bmpCanvas.drawLine(width/5*3, 0, width/5*3, height, paint6);
			 		bmpCanvas.drawLine(width/5*4, 0, width/5*4, height, paint6);
			    	bmpCanvas.drawLine(0, height/2, width, height/2, paint8);*/
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint6);
			    	bmpCanvas.drawLine(0, height/5, width, height/5, paint8);
			    	bmpCanvas.drawLine(0, height/5*2, width, height/5*2, paint8);
			    	bmpCanvas.drawLine(0, height/5*3, width, height/5*3, paint8);
			    	bmpCanvas.drawLine(0, height/5*4, width, height/5*4, paint8);
					/*paint6.setColor(Color.RED);
					paint8.setColor(Color.RED);
					
					paint.setColor(Color.TRANSPARENT);
					paint2.setColor(Color.TRANSPARENT);
					paint3.setColor(Color.TRANSPARENT);
					paint4.setColor(Color.TRANSPARENT);
					paint5.setColor(Color.TRANSPARENT);
					paint7.setColor(Color.TRANSPARENT);*/
				}				
		
        //canvas.drawBitmap(image,0,0,null);
			invalidate();
    }
	
	/*public void DrawLine(int a2) {
		bmpCanvas.drawBitmap(image,0,0,null);
		
		if(a2==2||a2==4||a2==8)
		bmpCanvas.drawLine(w/2, 0, w/2, h, paint);
		
		else if(a2==3||a2==6){
			bmpCanvas.drawLine(w/3, 0, w/3, h, paint);
			bmpCanvas.drawLine(w/3*2, 0, w/3*2, h, paint);
		}
		
		else if(a2==5){
			bmpCanvas.drawLine(w/2, 0, w/2, h/2, paint);
			bmpCanvas.drawLine(w/3, h/2, w/3, h, paint);
			bmpCanvas.drawLine(w/3*2, h/2, w/3*2, h, paint);
		}
		
		else if(a2==7){
			bmpCanvas.drawLine(w/3, 0, w/3, h/2, paint);
			bmpCanvas.drawLine(w/3*2, 0, w/3*2, h/2, paint);
			bmpCanvas.drawLine(w/4, h/2, w/4, h, paint);
			bmpCanvas.drawLine(w/2, h/2, w/2, h, paint);
			bmpCanvas.drawLine(w/4*3, h/2, w/4*3, h, paint);
		}
		
		else if(a2==9){
			bmpCanvas.drawLine(w/4, 0, w/4, h/2, paint);
			bmpCanvas.drawLine(w/4*2, 0, w/4*2, h/2, paint);
			bmpCanvas.drawLine(w/4*3, 0, w/4*3, h/2, paint);
			bmpCanvas.drawLine(w/5, h/2, w/5, h, paint);
			bmpCanvas.drawLine(w/5*2, h/2, w/5*2, h, paint);
			bmpCanvas.drawLine(w/5*3, h/2, w/5*3, h, paint);
			bmpCanvas.drawLine(w/5*4, h/2, w/5*4, h, paint);
		}
		
		else if(a2==10){
			bmpCanvas.drawLine(w/5, 0, w/5, h, paint);
			bmpCanvas.drawLine(w/5*2, 0, w/5*2, h, paint);
			bmpCanvas.drawLine(w/5*3, 0, w/5*3, h, paint);
			bmpCanvas.drawLine(w/5*4, 0, w/5*4, h, paint);
		}
		
		if(a2==8){
			bmpCanvas.drawLine(w/4, 0, w/4, h, paint);
			bmpCanvas.drawLine(w/4*3, 0, w/4*3, h, paint);
		}
		
		if(a2>3)
		bmpCanvas.drawLine(0, h/2, w, h/2, paint);
		
		if(a2==99)
			bmpCanvas.drawBitmap(bmp2,-100,-100,null);			
		
		//Toast.makeText(_context, +a2+"分割", Toast.LENGTH_SHORT).show();
	}*/
	
	public void saveToFile(int a2, int s, int g){
		 if(!sdcardWriteReady()){
		 Toast.makeText(_context, "SDcardが認識されません。", Toast.LENGTH_SHORT).show();
		 return;
		 }
		 
		 
		 File file = new File(Environment.getExternalStorageDirectory().getPath()+"/drawbm/puzzle"+a2+"/");
		 try{
			 
		 if(!file.exists()){
			 file.mkdir();
		 }
			 
		 }catch(SecurityException e){
			 Toast.makeText(_context, "例外発生", Toast.LENGTH_SHORT).show();
		 }
		 for(int i=s; i<g; i++){
			 
			 //画像作成
			 if(PuzzleActivity.a2==2){
				    //２分割
			        a = h/2;
			        aa = (int)a;
			        b = w;
			        if(i==0)
			        p = Bitmap.createBitmap(image, 0, 0, b, aa);
			        else
			        p = Bitmap.createBitmap(image, 0, aa, b, aa);
				 }else if(PuzzleActivity.a2==3){
			        //３分割
			        a = h/3;
			        aa = (int)a;
			        b = w;
			        if(i==2)
			        p = Bitmap.createBitmap(image, 0, 0, b, aa);
			        if(i==3)
			        p = Bitmap.createBitmap(image, 0, aa, b, aa);
			        if(i==4)
			        p = Bitmap.createBitmap(image, 0, aa*2, b, aa);
				 }else if(PuzzleActivity.a2==4){
			        //４分割
			        a = h/2;
			        aa = (int)a;
			        b = w/2;
			        if(i==5)
			        p = Bitmap.createBitmap(image, 0, 0, b, aa);
			        if(i==6)
			        p = Bitmap.createBitmap(image, b, 0, b, aa);
			        if(i==7)
			        p = Bitmap.createBitmap(image, 0, aa, b, aa);
			        if(i==8)
			        p = Bitmap.createBitmap(image, b, aa, b, aa);
				 }else if(PuzzleActivity.a2==5){
			        //５分割
			        a = w/3;
			        aa = (int)a;
			        b = h/2;
			        if(i==9)
			        p = Bitmap.createBitmap(image, 0, 0, w/2, b);
			        if(i==10)
			        p = Bitmap.createBitmap(image, w/2, 0, w/2, b);
			        if(i==11)
			        p = Bitmap.createBitmap(image, 0, b, aa, b);
			        if(i==12)
			        p = Bitmap.createBitmap(image, aa, b, aa, b);
			        if(i==13)
			        p = Bitmap.createBitmap(image, aa*2, b, aa, b);
				 }else if(PuzzleActivity.a2==6){ 
			        //６分割
			        a = h/3;
			        aa = (int)a;
			        b = w/2;
			        if(i==14)
			        p = Bitmap.createBitmap(image, 0, 0, b, aa);
			        if(i==15)
			        p = Bitmap.createBitmap(image, b, 0, b, aa);
			        if(i==16)
			        p = Bitmap.createBitmap(image, 0, aa, b, aa);
			        if(i==17)
			        p = Bitmap.createBitmap(image, b, aa, b, aa);
			        if(i==18)
			        p = Bitmap.createBitmap(image, 0, aa*2, b, aa);
			        if(i==19)
			        p = Bitmap.createBitmap(image, b, aa*2, b, aa);
				 }else if(PuzzleActivity.a2==7){  
			        //７分割
			        a = w/4;
			        aa = (int)a;
			        b = h/2;
			        if(i==20)
			        p = Bitmap.createBitmap(image, 0, 0, w/3, b);
			        if(i==21)
			        p = Bitmap.createBitmap(image, w/3, 0, w/3, b);
			        if(i==22)
			        p = Bitmap.createBitmap(image, w/3*2, 0, w/3, b);
			        if(i==23)
			        p = Bitmap.createBitmap(image, 0, b, aa, b);
			        if(i==24)
			        p = Bitmap.createBitmap(image, aa, b, aa, b);
			        if(i==25)
			        p = Bitmap.createBitmap(image, aa*2, b, aa, b);
			        if(i==26)
			        p = Bitmap.createBitmap(image, aa*3, b, aa, b);
				 }else if(PuzzleActivity.a2==8){  
			        //８分割
			        a = h/4;
			        aa = (int)a;
			        b = w/2;
			        if(i==27)
			        p = Bitmap.createBitmap(image, 0, 0, b, aa);
			        if(i==28)
			        p = Bitmap.createBitmap(image, b, 0, b, aa);
			        if(i==29)
			        p = Bitmap.createBitmap(image, 0, aa, b, aa);
			        if(i==30)
			        p = Bitmap.createBitmap(image, b, aa, b, aa);
			        if(i==31)
			        p = Bitmap.createBitmap(image, 0, aa*2, b, aa);
			        if(i==32)
			        p = Bitmap.createBitmap(image, b, aa*2, b, aa);
			        if(i==33)
			        p = Bitmap.createBitmap(image, 0, aa*3, b, aa);
			        if(i==34)
			        p = Bitmap.createBitmap(image, b, aa*3, b, aa);
				 }else if(PuzzleActivity.a2==9){   
			        //９分割
			        a = w/3;
			        aa = (int)a;
			        b = h/3;
			        if(i==35)
			        p = Bitmap.createBitmap(image, 0, 0, aa, b);
			        if(i==36)
			        p = Bitmap.createBitmap(image, aa, 0, aa, b);
			        if(i==37)
			        p = Bitmap.createBitmap(image, aa*2, 0, aa, b);
			        if(i==38)
			        p = Bitmap.createBitmap(image, 0, b, aa, b);
			        if(i==39)
			        p = Bitmap.createBitmap(image, aa, b, aa, b);
			        if(i==40)
			        p = Bitmap.createBitmap(image, aa*2, b, aa, b);
			        if(i==41)
			        p = Bitmap.createBitmap(image, 0, b*2, aa, b);
			        if(i==42)
			        p = Bitmap.createBitmap(image, aa, b*2, aa, b);
			        if(i==43)
			        p = Bitmap.createBitmap(image, aa*2, b*2, aa, b);
				 }else if(PuzzleActivity.a2==10){   
			        //１０分割
			        a = h/5;
			        aa = (int)a;
			        b = w/2;
			        if(i==44)
			        p = Bitmap.createBitmap(image, 0, 0, b, aa);
			        if(i==45)
			        p = Bitmap.createBitmap(image, b, 0, b, aa);
			        if(i==46)
			        p = Bitmap.createBitmap(image, 0, aa, b, aa);
			        if(i==47)
			        p = Bitmap.createBitmap(image, b, aa, b, aa);
			        if(i==48)
			        p = Bitmap.createBitmap(image, 0, aa*2, b, aa);
			        if(i==49)
			        p = Bitmap.createBitmap(image, b, aa*2, b, aa);
			        if(i==50)
			        p = Bitmap.createBitmap(image, 0, aa*3, b, aa);
			        if(i==51)
			        p = Bitmap.createBitmap(image, b, aa*3, b, aa);
			        if(i==52)
			        p = Bitmap.createBitmap(image, 0, aa*4, b, aa);
			        if(i==53)
			        p = Bitmap.createBitmap(image, b, aa*4, b, aa);
				 }
			 
			 
		 String AttachName = file.getAbsolutePath() + "/";
		 AttachName += "puzzle"+a3+".jpg";
		 a3++;
		 try {
		 FileOutputStream out = new FileOutputStream(AttachName);
		 p.compress(CompressFormat.JPEG, 100, out);
		 out.flush();
		 out.close();
		 } catch(Exception e) {
		 Toast.makeText(_context, "例外発生", Toast.LENGTH_SHORT).show();
		 }
		 if(a2==a3){//Toast.makeText(_context, "保存されました。", Toast.LENGTH_SHORT).show();
		 a3=0;
		 }
		 }
		 }

		 private boolean sdcardWriteReady(){
		 String state = Environment.getExternalStorageState();
		 return (Environment.MEDIA_MOUNTED.equals(state));
		 }
}