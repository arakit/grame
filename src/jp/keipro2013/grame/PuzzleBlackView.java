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
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

//ÉCÉÅÅ[ÉWÇÃï`âÊ
public class PuzzleBlackView extends View {
    static Bitmap image,image1,image2,image3;
	private Bitmap bmp;
    Bitmap p;
    int abc = 0;
    int []br=new int[54];
    float a;
    int aa;
    int b;   
    int a3=0;
    int w,h,width, height,w2,h2;
    private Canvas bmpCanvas;
    static Paint paint,paint2,paint3,paint4,paint5,paint6,paint7,paint8;
    private Activity _context;
	float x,y;
	//int br,br2=0;
    //ÉRÉìÉXÉgÉâÉNÉ^
    public PuzzleBlackView(Context context) {
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
        
        //ÉMÉÉÉâÉäÅ[ÇÃâÊëúì«Ç›çûÇ›
        image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/test2.jpg");
        
        w = image.getWidth();
        h = image.getHeight();
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bmpCanvas = new Canvas(bmp);
        image1 = BitmapFactory.decodeResource(getResources(), R.drawable.brack);
		image1 = Bitmap.createScaledBitmap(image1, w, h, false);
		//image0 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/test2.jpg");
		w2 = image1.getWidth();
        h2 = image1.getHeight();
    }
    
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	 super.onSizeChanged(w,h,oldw,oldh);
    	 
    	 image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/test2.jpg");
    	 image1 = BitmapFactory.decodeResource(getResources(), R.drawable.brack);
		 image1 = Bitmap.createScaledBitmap(image1, w, h, false);
    	 
         width = Title.w;
         height = Title.h;
    	 
    	 bmpCanvas.drawBitmap(image, 0, 0, null);
    	 bmpCanvas.drawBitmap(bmp, 0, 0, null);

    	 }

    //ï`âÊéûÇ…åƒÇŒÇÍÇÈ
	@Override 
    public void onDraw(Canvas canvas) {
		//canvas.drawBitmap(image, 0, 0, null);
		canvas.drawBitmap(bmp,0,0,null);
		
			if(PuzzleBlack.a2==2){
		    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
				bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
			}
			
				
				else if(PuzzleBlack.a2==3){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			 		bmpCanvas.drawLine(0, height/3, width, height/3, paint8);
			 		bmpCanvas.drawLine(0, height/3*2, width, height/3*2, paint8);
				}
			
				else if(PuzzleBlack.a2==4){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);			    	
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint);
			    	bmpCanvas.drawLine(0, height/2, width, height/2, paint8);					
				}
				
				else if(PuzzleBlack.a2==5){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			    	bmpCanvas.drawLine(width/2, 0, width/2, height/2, paint3);
			 		bmpCanvas.drawLine(width/3, height/2, width/3, height, paint3);
			 		bmpCanvas.drawLine(width/3*2, height/2, width/3*2, height, paint3);
			 		bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
				}
			
				else if(PuzzleBlack.a2==6){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint2);
			    	bmpCanvas.drawLine(0, height/3, width, height/3, paint8);
			    	bmpCanvas.drawLine(0, height/3*2, width, height/3*2, paint8);
				}
				
				else if(PuzzleBlack.a2==7){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);					
			    	bmpCanvas.drawLine(width/3, 0, width/3, height/2, paint4);
			 		bmpCanvas.drawLine(width/3*2, 0, width/3*2, height/2, paint4);
			 		bmpCanvas.drawLine(width/4, height/2, width/4, height, paint4);
			 		bmpCanvas.drawLine(width/2, height/2, width/2, height, paint4);
			 		bmpCanvas.drawLine(width/4*3, height/2, width/4*3, height, paint4);
			 		bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
				}
			
				else if(PuzzleBlack.a2==8){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint);
			    	bmpCanvas.drawLine(0, height/4, width, height/4, paint8);
			    	bmpCanvas.drawLine(0, height/2, width, height/2, paint8);
			    	bmpCanvas.drawLine(0, height/4*3, width, height/4*3, paint8);
				}
				
				else if(PuzzleBlack.a2==9){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			    	bmpCanvas.drawLine(width/3, 0, width/3, height, paint5);
			    	bmpCanvas.drawLine(width/3*2, 0, width/3*2, height, paint5);
			 		bmpCanvas.drawLine(0, height/3, width, height/3, paint5);
			 		bmpCanvas.drawLine(0, height/3*2, width, height/3*2, paint5);
				}
								
				else if(PuzzleBlack.a2==10){
					//bmpCanvas.drawBitmap(image, 0, 0, null);
			    	bmpCanvas.drawBitmap(bmp, 0, 0, null);
			    	bmpCanvas.drawLine(width/2, 0, width/2, height, paint6);
			    	bmpCanvas.drawLine(0, height/5, width, height/5, paint8);
			    	bmpCanvas.drawLine(0, height/5*2, width, height/5*2, paint8);
			    	bmpCanvas.drawLine(0, height/5*3, width, height/5*3, paint8);
			    	bmpCanvas.drawLine(0, height/5*4, width, height/5*4, paint8);
				}				
			invalidate();
    }
	
	public void saveToFile(int a2, int s, int g){
		 if(!sdcardWriteReady()){
		 Toast.makeText(_context, "SDcardÇ™îFéØÇ≥ÇÍÇ‹ÇπÇÒÅB", Toast.LENGTH_SHORT).show();
		 return;
		 }
		 
		 
		 File file = new File(Environment.getExternalStorageDirectory().getPath()+"/drawbm/puzzle"+a2+"/");
		 try{
			 
		 if(!file.exists()){
			 file.mkdir();
		 }
			 
		 
		 image1 = BitmapFactory.decodeResource(getResources(), R.drawable.brack);
		 image1 = Bitmap.createScaledBitmap(image1, w, h, false);
		 }catch(SecurityException e){
			 Toast.makeText(_context, "ó·äOî≠ê∂", Toast.LENGTH_SHORT).show();
		 }
		 for(int i=s; i<g; i++){
			 
			 //âÊëúçÏê¨
			 if(PuzzleBlack.a2==2){
				    //ÇQï™äÑ
			        a = h/2;
			        aa = (int)a;
			        b = w;
			        if(i==0&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, b, aa);
			        //else if(i==0&&br[1]!=1)
			        //p = Bitmap.createBitmap(image, 0, 0, b, aa);
			        if(i==1&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa, b, aa);
			        //else if(i==1&&br[2]!=1)
				    //p = Bitmap.createBitmap(image, 0, aa, b, aa);
				 }else if(PuzzleBlack.a2==3){
			        //ÇRï™äÑ
			        a = h/3;
			        aa = (int)a;
			        b = w;
			        if(i==2&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, b, aa);
			        if(i==3&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa, b, aa);
			        if(i==4&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa*2, b, aa);
				 }else if(PuzzleBlack.a2==4){
			        //ÇSï™äÑ
			        a = h/2;
			        aa = (int)a;
			        b = w/2;
			        if(i==5&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, b, aa);
			        if(i==6&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, 0, b, aa);
			        if(i==7&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa, b, aa);
			        if(i==8&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa, b, aa);
				 }else if(PuzzleBlack.a2==5){
			        //ÇTï™äÑ
			        a = w/3;
			        aa = (int)a;
			        b = h/2;
			        if(i==9&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, w/2, b);
			        if(i==10&&br[i]==1)
			        p = Bitmap.createBitmap(image1, w/2, 0, w/2, b);
			        if(i==11&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, b, aa, b);
			        if(i==12&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa, b, aa, b);
			        if(i==13&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa*2, b, aa, b);
				 }else if(PuzzleBlack.a2==6){ 
			        //ÇUï™äÑ
			        a = h/3;
			        aa = (int)a;
			        b = w/2;
			        if(i==14&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, b, aa);
			        if(i==15&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, 0, b, aa);
			        if(i==16&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa, b, aa);
			        if(i==17&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa, b, aa);
			        if(i==18&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa*2, b, aa);
			        if(i==19&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa*2, b, aa);
				 }else if(PuzzleBlack.a2==7){  
			        //ÇVï™äÑ
			        a = w/4;
			        aa = (int)a;
			        b = h/2;
			        if(i==20&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, w/3, b);
			        if(i==21&&br[i]==1)
			        p = Bitmap.createBitmap(image1, w/3, 0, w/3, b);
			        if(i==22&&br[i]==1)
			        p = Bitmap.createBitmap(image1, w/3*2, 0, w/3, b);
			        if(i==23&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, b, aa, b);
			        if(i==24&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa, b, aa, b);
			        if(i==25&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa*2, b, aa, b);
			        if(i==26&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa*3, b, aa, b);
				 }else if(PuzzleBlack.a2==8){  
			        //ÇWï™äÑ
			        a = h/4;
			        aa = (int)a;
			        b = w/2;
			        if(i==27&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, b, aa);
			        if(i==28&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, 0, b, aa);
			        if(i==29&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa, b, aa);
			        if(i==30&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa, b, aa);
			        if(i==31&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa*2, b, aa);
			        if(i==32&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa*2, b, aa);
			        if(i==33&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa*3, b, aa);
			        if(i==34&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa*3, b, aa);
				 }else if(PuzzleBlack.a2==9){   
			        //ÇXï™äÑ
			        a = w/3;
			        aa = (int)a;
			        b = h/3;
			        if(i==35&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, aa, b);
			        if(i==36&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa, 0, aa, b);
			        if(i==37&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa*2, 0, aa, b);
			        if(i==38&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, b, aa, b);
			        if(i==39&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa, b, aa, b);
			        if(i==40&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa*2, b, aa, b);
			        if(i==41&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, b*2, aa, b);
			        if(i==42&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa, b*2, aa, b);
			        if(i==43&&br[i]==1)
			        p = Bitmap.createBitmap(image1, aa*2, b*2, aa, b);
				 }else if(PuzzleBlack.a2==10){   
			        //ÇPÇOï™äÑ
			        a = h/5;
			        aa = (int)a;
			        b = w/2;
			        if(i==44&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, 0, b, aa);
			        if(i==45&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, 0, b, aa);
			        if(i==46&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa, b, aa);
			        if(i==47&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa, b, aa);
			        if(i==48&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa*2, b, aa);
			        if(i==49&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa*2, b, aa);
			        if(i==50&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa*3, b, aa);
			        if(i==51&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa*3, b, aa);
			        if(i==52&&br[i]==1)
			        p = Bitmap.createBitmap(image1, 0, aa*4, b, aa);
			        if(i==53&&br[i]==1)
			        p = Bitmap.createBitmap(image1, b, aa*4, b, aa);
				 }
			 
		 String AttachName = file.getAbsolutePath() + "/";
		 AttachName += "puzzle"+a3+".jpg";
		 a3++;
		 if(br[i]==1){
		 try {
		 FileOutputStream out = new FileOutputStream(AttachName);
		 p.compress(CompressFormat.JPEG, 100, out);
		 out.flush();
		 out.close();
		 } catch(Exception e) {
		 Toast.makeText(_context, "ó·äOî≠ê∂", Toast.LENGTH_SHORT).show();
		 }
		 }
		 if(a2==a3){//Toast.makeText(_context, "ï€ë∂Ç≥ÇÍÇ‹ÇµÇΩÅB", Toast.LENGTH_SHORT).show();
		 a3=0;
		 }
		 }
		 }
	
	public boolean onTouchEvent(MotionEvent e) {
		if(e.getAction() == MotionEvent.ACTION_DOWN) {
			x = e.getX();
			y = e.getY();
			
			//Toast.makeText(_context, "x:"+x+" y:"+y, Toast.LENGTH_SHORT).show();
			
			if(PuzzleBlack.a2==2){
				image2 = Bitmap.createScaledBitmap(image1, w2, h2/2, false);
				if(y<h2/2&&br[0]!=1){
					bmpCanvas.drawBitmap(image2, 0, 0, null);
					br[0]=1;
					}else if(y<h2/2&&br[0]==1){
						image3 = Bitmap.createBitmap(image, 0, 0, w, h2/2);
						bmpCanvas.drawBitmap(image3, 0, 0, null);
						br[0]=0;
					}
				if(y>h2/2&&br[1]==0){
					bmpCanvas.drawBitmap(image2, 0, h2/2, null);
					br[1]=1;
					}else if(y>h2/2&&br[1]==1){
						image3 = Bitmap.createBitmap(image, 0, h2/2, w2, h2/2);
						bmpCanvas.drawBitmap(image3, 0, h2/2, null);
						br[1]=0;
					}
				}else if(PuzzleBlack.a2==3){
					image2 = Bitmap.createScaledBitmap(image1, w2, h2/3, false);
					if(y<h2/3&&br[2]!=1){
						bmpCanvas.drawBitmap(image2, 0, 0, null);
						br[2]=1;
						}else if(y<h2/3&&br[2]==1){
							image3 = Bitmap.createBitmap(image, 0, 0, w, h2/3);
							bmpCanvas.drawBitmap(image3, 0, 0, null);
							br[2]=0;
						}
					if(y>h2/3&&y<h2/3*2&&br[3]==0){
						bmpCanvas.drawBitmap(image2, 0, h2/3, null);
						br[3]=1;
						}else if(y>h2/3&&y<h2/3*2&&br[3]==1){
							image3 = Bitmap.createBitmap(image, 0, h2/3, w2, h2/3);
							bmpCanvas.drawBitmap(image3, 0, h2/3, null);
							br[3]=0;
						}
					if(y>h2/3*2&br[4]==0){
						bmpCanvas.drawBitmap(image2, 0, h2/3*2, null);
						br[4]=1;
						}else if(y>h2/3*2&&br[4]==1){
							image3 = Bitmap.createBitmap(image, 0, h2/3*2, w2, h2/3);
							bmpCanvas.drawBitmap(image3, 0, h2/3*2, null);
							br[4]=0;
						}
					}else if(PuzzleBlack.a2==4){
						image2 = Bitmap.createScaledBitmap(image1, w2/2, h2/2, false);
						if(y<h2/2&&x<w2/2&&br[5]!=1){
							bmpCanvas.drawBitmap(image2, 0, 0, null);
							br[5]=1;
							}else if(y<h2/2&&x<w2/2&&br[5]==1){
								image3 = Bitmap.createBitmap(image, 0, 0, w/2, h2/2);
								bmpCanvas.drawBitmap(image3, 0, 0, null);
								br[5]=0;
							}
						if(y<h2/2&&x>w2/2&&br[6]==0){
							bmpCanvas.drawBitmap(image2, w/2, 0, null);
							br[6]=1;
							}else if(y<h2/2&&x>w2/2&&br[6]==1){
								image3 = Bitmap.createBitmap(image, w/2, 0, w2/2, h2/2);
								bmpCanvas.drawBitmap(image3, w/2, 0, null);
								br[6]=0;
							}
						if(y>h2/2&&x<w2/2&&br[7]==0){
							bmpCanvas.drawBitmap(image2, 0, h2/2, null);
							br[7]=1;
							}else if(y>h2/2&&x<w2/2&&br[7]==1){
								image3 = Bitmap.createBitmap(image, 0, h2/2, w2/2, h2/2);
								bmpCanvas.drawBitmap(image3, 0, h2/2, null);
								br[7]=0;
							}
						if(y>h2/2&&x>w2/2&&br[8]==0){
							bmpCanvas.drawBitmap(image2, w/2, h2/2, null);
							br[8]=1;
							}else if(y>h2/2&&x>w2/2&&br[8]==1){
								image3 = Bitmap.createBitmap(image, w/2, h2/2, w2/2, h2/2);
								bmpCanvas.drawBitmap(image3, w2/2, h2/2, null);
								br[8]=0;
							}
						}else if(PuzzleBlack.a2==5){
							image2 = Bitmap.createScaledBitmap(image1, w2/2, h2/2, false);
							if(y<h2/2&&x<w2/2&&br[9]!=1){
								bmpCanvas.drawBitmap(image2, 0, 0, null);
								br[9]=1;
								}else if(y<h2/2&&x<w2/2&&br[9]==1){
									image3 = Bitmap.createBitmap(image, 0, 0, w2/2, h2/2);
									bmpCanvas.drawBitmap(image3, 0, 0, null);
									br[9]=0;
								}
							if(y<h2/2&&x>w2/2&&br[10]==0){
								bmpCanvas.drawBitmap(image2, w2/2, 0, null);
								br[10]=1;
								}else if(y<h2/2&&x>w2/2&&br[10]==1){
									image3 = Bitmap.createBitmap(image, w2/2, 0, w2/2, h2/2);
									bmpCanvas.drawBitmap(image3, w2/2, 0, null);
									br[10]=0;
								}
							image2 = Bitmap.createScaledBitmap(image1, w2/3, h2/2, false);
							if(y>h2/2&&x<w2/3&&br[11]==0){
								bmpCanvas.drawBitmap(image2, 0, h2/2, null);
								br[11]=1;
								}else if(y>h2/2&&x<w2/3&&br[11]==1){
									image3 = Bitmap.createBitmap(image, 0, h2/2, w2/3, h2/2);
									bmpCanvas.drawBitmap(image3, 0, h2/2, null);
									br[11]=0;
								}
							if(y>h2/2&&x>w2/3&&x<w2/3*2&&br[12]==0){
								bmpCanvas.drawBitmap(image2, w2/3, h2/2, null);
								br[12]=1;
								}else if(y>h2/2&&x>w2/3&&x<w2/3*2&&br[12]==1){
									image3 = Bitmap.createBitmap(image, w2/3, h2/2, w2/3, h2/2);
									bmpCanvas.drawBitmap(image3, w2/3, h2/2, null);
									br[12]=0;
								}
							if(y>h2/2&&x>w2/3*2&&br[13]==0){
								bmpCanvas.drawBitmap(image2, w2/3*2, h2/2, null);
								br[13]=1;
								}else if(y>h2/2&&x>w2/3*2&&br[13]==1){
									image3 = Bitmap.createBitmap(image, w2/3*2, h2/2, w2/3, h2/2);
									bmpCanvas.drawBitmap(image3, w2/3*2, h2/2, null);
									br[13]=0;
								}
							}else if(PuzzleBlack.a2==6){
								image2 = Bitmap.createScaledBitmap(image1, w2/2, h2/3, false);
								if(y<h2/3&&x<w/2&&br[14]!=1){
									bmpCanvas.drawBitmap(image2, 0, 0, null);
									br[14]=1;
									}else if(y<h2/3&&x<w/2&&br[14]==1){
										image3 = Bitmap.createBitmap(image, 0, 0, w/2, h2/3);
										bmpCanvas.drawBitmap(image3, 0, 0, null);
										br[14]=0;
									}
								if(y<h2/3&&x>w/2&&br[15]==0){
									bmpCanvas.drawBitmap(image2, w/2, 0, null);
									br[15]=1;
									}else if(y<h2/3&&x>w/2&&br[15]==1){
										image3 = Bitmap.createBitmap(image, w/2, 0, w2/2, h2/3);
										bmpCanvas.drawBitmap(image3, w/2, 0, null);
										br[15]=0;
									}
								if(y>h2/3&&y<h2/3*2&&x<w/2&&br[16]==0){
									bmpCanvas.drawBitmap(image2, 0, h2/3, null);
									br[16]=1;
									}else if(y>h2/3&&y<h2/3*2&&x<w/2&&br[16]==1){
										image3 = Bitmap.createBitmap(image, 0, h2/3, w2/2, h2/3);
										bmpCanvas.drawBitmap(image3, 0, h2/3, null);
										br[16]=0;
									}
								if(y>h2/3&&y<h2/3*2&&x>w/2&&br[17]==0){
									bmpCanvas.drawBitmap(image2, w2/2, h2/3, null);
									br[17]=1;
									}else if(y>h2/3&&y<h2/3*2&&x>w/2&&br[17]==1){
										image3 = Bitmap.createBitmap(image, w/2, h2/3, w2/2, h2/3);
										bmpCanvas.drawBitmap(image3, w/2, h2/3, null);
										br[17]=0;
									}
								if(y>h2/3*2&&x<w/2&&br[18]==0){
									bmpCanvas.drawBitmap(image2, 0, h2/3*2, null);
									br[18]=1;
									}else if(y>h2/3*2&&x<w/2&&br[18]==1){
										image3 = Bitmap.createBitmap(image, 0, h2/3*2, w2/2, h2/3);
										bmpCanvas.drawBitmap(image3, 0, h2/3*2, null);
										br[18]=0;
									}
								if(y>h2/3*2&&x>w/2&&br[19]==0){
									bmpCanvas.drawBitmap(image2, w/2, h2/3*2, null);
									br[19]=1;
									}else if(y>h2/3*2&&x>w/2&&br[19]==1){
										image3 = Bitmap.createBitmap(image, w/2, h2/3*2, w2/2, h2/3);
										bmpCanvas.drawBitmap(image3, w/2, h2/3*2, null);
										br[19]=0;
									}
								}else if(PuzzleBlack.a2==7){
									image2 = Bitmap.createScaledBitmap(image1, w2/3, h2/2, false);
									if(y<h2/2&&x<w2/3&&br[20]!=1){
										bmpCanvas.drawBitmap(image2, 0, 0, null);
										br[20]=1;
										}else if(y<h2/2&&x<w2/3&&br[20]==1){
											image3 = Bitmap.createBitmap(image, 0, 0, w2/3, h2/2);
											bmpCanvas.drawBitmap(image3, 0, 0, null);
											br[20]=0;
										}
									if(y<h2/2&&x>w2/3&&x<w2/3*2&&br[21]==0){
										bmpCanvas.drawBitmap(image2, w2/3, 0, null);
										br[21]=1;
										}else if(y<h2/2&&x>w2/3&&x<w2/3*2&&br[21]==1){
											image3 = Bitmap.createBitmap(image, w2/3, 0, w2/3, h2/2);
											bmpCanvas.drawBitmap(image3, w2/3, 0, null);
											br[21]=0;
										}
									if(y<h2/2&&x>w2/3*2&&br[22]==0){
										bmpCanvas.drawBitmap(image2, w2/3*2, 0, null);
										br[22]=1;
										}else if(y<h2/2&&x>w2/3*2&&br[22]==1){
											image3 = Bitmap.createBitmap(image, w2/3*2, 0, w2/3, h2/2);
											bmpCanvas.drawBitmap(image3, w2/3*2, 0, null);
											br[22]=0;
										}
									image2 = Bitmap.createScaledBitmap(image1, w2/4, h2/2, false);
									if(y>h2/2&&x<w2/4&&br[23]==0){
										bmpCanvas.drawBitmap(image2, 0, h2/2, null);
										br[23]=1;
										}else if(y>h2/2&&x<w2/4&&br[23]==1){
											image3 = Bitmap.createBitmap(image, 0, h2/2, w2/4, h2/2);
											bmpCanvas.drawBitmap(image3, 0, h2/2, null);
											br[23]=0;
										}
									if(y>h2/2&&x>w2/4&&x<w2/2&&br[24]==0){
										bmpCanvas.drawBitmap(image2, w2/4, h2/2, null);
										br[24]=1;
										}else if(y>h2/2&&x>w2/4&&x<w2/2&&br[24]==1){
											image3 = Bitmap.createBitmap(image, w2/4, h2/2, w2/4, h2/2);
											bmpCanvas.drawBitmap(image3, w2/4, h2/2, null);
											br[24]=0;
										}
									if(y>h2/2&&x>w2/2&&x<w2/4*3&&br[25]==0){
										bmpCanvas.drawBitmap(image2, w2/2, h2/2, null);
										br[25]=1;
										}else if(y>h2/2&&x>w2/2&&x<w2/4*3&&br[25]==1){
											image3 = Bitmap.createBitmap(image, w2/2, h2/2, w2/4, h2/2);
											bmpCanvas.drawBitmap(image3, w2/2, h2/2, null);
											br[25]=0;
										}
									if(y>h2/2&&x>w2/4*3&&br[26]==0){
										bmpCanvas.drawBitmap(image2, w2/4*3, h2/2, null);
										br[26]=1;
										}else if(y>h2/2&&x>w2/4*3&&br[26]==1){
											image3 = Bitmap.createBitmap(image, w2/4*3, h2/2, w2/4, h2/2);
											bmpCanvas.drawBitmap(image3, w2/4*3, h2/2, null);
											br[26]=0;
										}
									}else if(PuzzleBlack.a2==8){
										image2 = Bitmap.createScaledBitmap(image1, w2/2, h2/4, false);
										if(y<h2/4&&x<w2/2&&br[27]!=1){
											bmpCanvas.drawBitmap(image2, 0, 0, null);
											br[27]=1;
											}else if(y<h2/4&&x<w2/2&&br[27]==1){
												image3 = Bitmap.createBitmap(image, 0, 0, w2/2, h2/4);
												bmpCanvas.drawBitmap(image3, 0, 0, null);
												br[27]=0;
											}
										if(y<h2/4&&x>w2/2&&br[28]==0){
											bmpCanvas.drawBitmap(image2, w2/2, 0, null);
											br[28]=1;
											}else if(y<h2/4&&x>w2/2&&br[28]==1){
												image3 = Bitmap.createBitmap(image, w2/2, 0, w2/2, h2/4);
												bmpCanvas.drawBitmap(image3, w2/2, 0, null);
												br[28]=0;
											}
										if(y>h2/4&&y<h2/2&&x<w2/2&&br[29]==0){
											bmpCanvas.drawBitmap(image2, 0, h2/4, null);
											br[29]=1;
											}else if(y>h2/4&&y<h2/2&&x<w2/2&&br[29]==1){
												image3 = Bitmap.createBitmap(image, 0, h2/4, w2/2, h2/4);
												bmpCanvas.drawBitmap(image3, 0, h2/4, null);
												br[29]=0;
											}
										if(y>h2/4&&y<h2/2&&x>w2/2&&br[30]==0){
												bmpCanvas.drawBitmap(image2, w2/2, h2/4, null);
												br[30]=1;
												}else if(y>h2/4&&y<h2/2&&x>w2/2&&br[30]==1){
													image3 = Bitmap.createBitmap(image, w2/2, h2/4, w2/2, h2/4);
													bmpCanvas.drawBitmap(image3, w2/2, h2/4, null);
													br[30]=0;
												}
											if(y>h2/2&&y<h2/4*3&&x<w2/2&&br[31]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/2, null);
												br[31]=1;
												}else if(y>h2/2&&y<h2/4*3&&x<w2/2&&br[31]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/2, w2/2, h2/4);
													bmpCanvas.drawBitmap(image3, 0, h2/2, null);
													br[31]=0;
												}
											if(y>h2/2&&y<h2/4*3&&x>w2/2&&br[32]==0){
												bmpCanvas.drawBitmap(image2, w2/2, h2/2, null);
												br[32]=1;
												}else if(y>h2/2&&y<h2/4*3&&x>w2/2&&br[32]==1){
													image3 = Bitmap.createBitmap(image, w2/2, h2/2, w2/2, h2/4);
													bmpCanvas.drawBitmap(image3, w2/2, h2/2, null);
													br[32]=0;
												}
											if(y>h2/4*3&&x<w2/2&&br[33]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/4*3, null);
												br[33]=1;
												}else if(y>h2/4*3&&x<w2/2&&br[33]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/4*3, w2/2, h2/4);
													bmpCanvas.drawBitmap(image3, 0, h2/4*3, null);
													br[33]=0;
												}
											if(y>h2/4*3&&x>w2/2&&br[34]==0){
												bmpCanvas.drawBitmap(image2, w2/2, h2/4*3, null);
												br[34]=1;
												}else if(y>h2/4*3&&x>w2/2&&br[34]==1){
													image3 = Bitmap.createBitmap(image, w2/2, h2/4*3, w2/2, h2/4);
													bmpCanvas.drawBitmap(image3, w2/2, h2/4*3, null);
													br[34]=0;
												}
										}else if(PuzzleBlack.a2==9){
											image2 = Bitmap.createScaledBitmap(image1, w2/3, h2/3, false);
											if(y<h2/3&&x<w2/3&&br[35]!=1){
												bmpCanvas.drawBitmap(image2, 0, 0, null);
												br[35]=1;
												}else if(y<h2/3&&x<w2/3&&br[35]==1){
													image3 = Bitmap.createBitmap(image, 0, 0, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, 0, 0, null);
													br[35]=0;
												}
											if(y<h2/3&&x>w2/3&&x<w2/3*2&&br[36]==0){
												bmpCanvas.drawBitmap(image2, w2/3, 0, null);
												br[36]=1;
												}else if(y<h2/3&&x>w2/3&&x<w2/3*2&&br[36]==1){
													image3 = Bitmap.createBitmap(image, w2/3, 0, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, w2/3, 0, null);
													br[36]=0;
												}
											if(y<h2/3&&x>w2/3*2&&br[37]==0){
												bmpCanvas.drawBitmap(image2, w2/3*2, 0, null);
												br[37]=1;
												}else if(y<h2/3&&x>w2/3*2&&br[37]==1){
													image3 = Bitmap.createBitmap(image, w/3*2, 0, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, w/3*2, 0, null);
													br[37]=0;
												}
											if(y>h2/3&&y<h2/3*2&&x<w2/3&&br[38]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/3, null);
												br[38]=1;
												}else if(y>h2/3&&y<h2/3*2&&x<w2/3&&br[38]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/3, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, 0, h2/3, null);
													br[38]=0;
												}
											if(y>h2/3&&y<h2/3*2&&x>w2/3&&x<w2/3*2&&br[39]==0){
												bmpCanvas.drawBitmap(image2, w2/3, h2/3, null);
												br[39]=1;
												}else if(y>h2/3&&y<h2/3*2&&x>w2/3&&x<w2/3*2&&br[39]==1){
													image3 = Bitmap.createBitmap(image, w2/3, h2/3, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, w2/3, h2/3, null);
													br[39]=0;
												}
											if(y>h2/3&&y<h2/3*2&&x>w2/3*2&&br[40]==0){
												bmpCanvas.drawBitmap(image2, w2/3*2, h2/3, null);
												br[40]=1;
												}else if(y>h2/3&&y<h2/3*2&&x>w2/3*2&&br[40]==1){
													image3 = Bitmap.createBitmap(image, w2/3*2, h2/3, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, w2/3*2, h2/3, null);
													br[40]=0;
												}
											if(y>h2/3*2&&x<w2/3&&br[41]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/3*2, null);
												br[41]=1;
												}else if(y>h2/3*2&&x<w2/3&&br[41]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/3*2, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, 0, h2/3*2, null);
													br[41]=0;
												}
											if(y>h2/3*2&&x>w2/3&&x<w2/3*2&&br[42]==0){
												bmpCanvas.drawBitmap(image2, w2/3, h2/3*2, null);
												br[42]=1;
												}else if(y>h2/3*2&&x>w2/3&&x<w2/3*2&&br[42]==1){
													image3 = Bitmap.createBitmap(image, w2/3, h2/3*2, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, w2/3, h2/3*2, null);
													br[42]=0;
												}
											if(y>h2/3*2&&x>w2/3*2&&br[43]==0){
												bmpCanvas.drawBitmap(image2, w2/3*2, h2/3*2, null);
												br[43]=1;
												}else if(y>h2/3*2&&x>w2/3*2&&br[43]==1){
													image3 = Bitmap.createBitmap(image, w2/3*2, h2/3*2, w2/3, h2/3);
													bmpCanvas.drawBitmap(image3, w2/3*2, h2/3*2, null);
													br[43]=0;
												}
											}else if(PuzzleBlack.a2==10){
												image2 = Bitmap.createScaledBitmap(image1, w2/2, h2/5, false);
												if(y<h2/5&&x<w2/2&&br[44]==0){
													bmpCanvas.drawBitmap(image2, 0, 0, null);
													br[44]=1;
													}else if(y<h2/5&&x<w2/2&&br[44]==1){
														image3 = Bitmap.createBitmap(image, 0, 0, w2/2, h2/5);
														bmpCanvas.drawBitmap(image3, 0, 0, null);
														br[44]=0;
													}
											if(y<h2/5&x>w2/2&&br[45]==0){
												bmpCanvas.drawBitmap(image2, w2/2, 0, null);
												br[45]=1;
												}else if(y<h2/5&x>w2/2&&br[45]==1){
													image3 = Bitmap.createBitmap(image, w2/2, 0, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, w2/2, 0, null);
													br[45]=0;
												}
											if(y>h2/5&&y<h2/5*2&&x<w2/2&&br[46]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/5, null);
												br[46]=1;
												}else if(y>h2/5&&y<h2/5*2&&x<w2/2&&br[46]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/5, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, 0, h2/5, null);
													br[46]=0;
												}
											if(y>h2/5&&y<h2/5*2&&x>w2/2&&br[47]==0){
												bmpCanvas.drawBitmap(image2, w2/2, h2/5, null);
												br[47]=1;
												}else if(y>h2/5&&y<h2/5*2&&x>w2/2&&br[47]==1){
													image3 = Bitmap.createBitmap(image, w2/2, h2/5, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, w2/2, h2/5, null);
													br[47]=0;
												}
											if(y>h2/5*2&&y<h2/5*3&&x<w2/2&&br[48]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/5*2, null);
												br[48]=1;
												}else if(y>h2/5*2&&y<h2/5*3&&x<w2/2&&br[48]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/5*2, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, 0, h2/5*2, null);
													br[48]=0;
												}
											if(y>h2/5*2&&y<h2/5*3&&x>w2/2&&br[49]==0){
												bmpCanvas.drawBitmap(image2, w2/2, h2/5*2, null);
												br[49]=1;
												}else if(y>h2/5*2&&y<h2/5*3&&x>w2/2&&br[49]==1){
													image3 = Bitmap.createBitmap(image, w/2, h2/5*2, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, w2/2, h2/5*2, null);
													br[49]=0;
												}
											if(y>h2/5*3&&y<h2/5*4&&x<w2/2&&br[50]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/5*3, null);
												br[50]=1;
												}else if(y>h2/5*3&&y<h2/5*4&&x<w2/2&&br[50]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/5*3, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, 0, h2/5*3, null);
													br[50]=0;
												}
											if(y>h2/5*3&&y<h2/5*4&&x>w2/2&&br[51]==0){
												bmpCanvas.drawBitmap(image2, w2/2, h2/5*3, null);
												br[51]=1;
												}else if(y>h2/5*3&&y<h2/5*4&&x>w2/2&&br[51]==1){
													image3 = Bitmap.createBitmap(image, w/2, h2/5*3, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, w2/2, h2/5*3, null);
													br[51]=0;
												}
											if(y>h2/5*4&&x<w2/2&&br[52]==0){
												bmpCanvas.drawBitmap(image2, 0, h2/5*4, null);
												br[52]=1;
												}else if(y>h2/5*4&&x<w2/2&&br[52]==1){
													image3 = Bitmap.createBitmap(image, 0, h2/5*4, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, 0, h2/5*4, null);
													br[52]=0;
												}
											if(y>h2/5*4&&x>w2/2&&br[53]==0){
												bmpCanvas.drawBitmap(image2, w2/2, h2/5*4, null);
												br[53]=1;
												}else if(y>h2/5*4&&x>w2/2&&br[53]==1){
													image3 = Bitmap.createBitmap(image, w/2, h2/5*4, w2/2, h2/5);
													bmpCanvas.drawBitmap(image3, w2/2, h2/5*4, null);
													br[53]=0;
												}
											}
			}
		return true;
		}

		 private boolean sdcardWriteReady(){
		 String state = Environment.getExternalStorageState();
		 return (Environment.MEDIA_MOUNTED.equals(state));
		 }
}