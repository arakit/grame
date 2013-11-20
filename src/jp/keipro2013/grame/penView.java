package jp.keipro2013.grame;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("SdCardPath")
public class penView extends View {
	  

	private float oldx = 0f;
	private float oldy = 0f;
	
	private Canvas dCanvas;
	private Bitmap bmpCanvas = null;
	private Bitmap bmpBack = null;
	int saveNum = 1;
	
	private Activity _context;
	
	private int mode = 0;

	private ArrayList<Bitmap> undoBitmap = new ArrayList<Bitmap>();
	private ArrayList<Bitmap> redoBitmap = new ArrayList<Bitmap>();
	private int backMaxNum = 5;

	private Paint paint;
	private int color = Color.MAGENTA;
	private int penSize=6, erasorSize=12;

	private long stampBmpId;
	private BitmapDrawable stampBmp;
	
	public penView(Context context, AttributeSet attr) {
		super(context, attr);
		_context = (Activity) context;
		paint = new Paint();
		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(penSize);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);

		this.setBackgroundColor(Color.argb(0, 0, 0, 0));
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		bmpCanvas = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		bmpBack = BitmapFactory.decodeFile(Environment
				.getExternalStorageDirectory() + "/drawbm/test.jpg");
		int deg = 90;
		Matrix mat = new Matrix();
		mat.postRotate( deg );
		int sw = bmpBack.getWidth();
		int sh = bmpBack.getHeight();
		bmpBack = Bitmap.createBitmap(bmpBack, 0, 0, sw, sh, mat, true);
		dCanvas = new Canvas(bmpCanvas);
		dCanvas.drawColor(0, PorterDuff.Mode.CLEAR);

		DisplayMetrics metrics = new DisplayMetrics();
		_context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		float screenWidth = (float) metrics.widthPixels;
		float screenHeight = (float) metrics.heightPixels;
		int photoWidth = bmpBack.getWidth();
		int photoHeight = bmpBack.getHeight();
		Matrix matrix = new Matrix();
		float widthScale = screenWidth / photoWidth;
		float heightScale = screenHeight / photoHeight;
		if (widthScale > heightScale) {
			matrix.postScale(heightScale, heightScale);
		} else {
			matrix.postScale(widthScale, widthScale);
		}
		bmpBack = Bitmap.createScaledBitmap(bmpBack, (int) screenWidth,
				(int) screenHeight, false);
	}

	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bmpBack, 0, 0, null);
		canvas.drawBitmap(bmpCanvas, 0, 0, null);
		if (saveNum == 1)
			saveToFile();
	}

	public boolean onTouchEvent(MotionEvent e) {

		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (undoBitmap.size() >= backMaxNum)	undoBitmap.remove(0);
			undoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
			if(redoBitmap.size() > 0) redoBitmap.clear();

			if(mode == 0){
				oldx = e.getX();
				oldy = e.getY();
			}
			if(mode == 1){				
			}
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE: 
			if(mode == 0){
				dCanvas.drawLine(oldx, oldy, e.getX(), e.getY(), paint);
				oldx = e.getX();
				oldy = e.getY();
			}

			if(mode == 1){
			}
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			if(mode == 1){
				stampBmp.setBounds((int)(e.getX()-stampBmp.getBitmap().getWidth()/2), (int)(e.getY()-stampBmp.getBitmap().getHeight()/2), (int)(e.getX()+stampBmp.getBitmap().getWidth()/2), (int)(e.getY()+stampBmp.getBitmap().getHeight()/2));
				stampBmp.draw(dCanvas);
			}
			invalidate();
			break;
		default:
			break;
		}
		return true;
	}

	public void clearDrawList() {
		if (undoBitmap.size() >= backMaxNum)	undoBitmap.remove(0);
		undoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
		if(redoBitmap.size() > 0) redoBitmap.clear();

		dCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		
		invalidate();
	}
	

	public void pen_action() {
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC));
		paint.setStrokeWidth(penSize);
		mode=0;
	}
	public void penSize_action(int size){
		penSize = size + 6;
		this.pen_action();
	}
	public void colorSelect(int color){
		this.color = color;
		paint.setColor(this.color);
	}

	public void erasor_action() {
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.CLEAR));
		paint.setStrokeWidth(erasorSize);
		mode=0;
	}
	public void erasorSize_action(int size){
		erasorSize = size + 12;
		this.erasor_action();
	}

	public void undo_action() {
		String n = Integer.toString(undoBitmap.size());
		if (undoBitmap.size() > 0) {
			redoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
			bmpCanvas = undoBitmap.get(undoBitmap.size() - 1);
			dCanvas = new Canvas(bmpCanvas);
			undoBitmap.remove(undoBitmap.size() - 1);
			invalidate();
		}
	}
	
	public void redo_action() {
		String n = Integer.toString(redoBitmap.size());
		if (redoBitmap.size() > 0) {
			undoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
			bmpCanvas = redoBitmap.get(redoBitmap.size() - 1);
			dCanvas = new Canvas(bmpCanvas);
			redoBitmap.remove(redoBitmap.size() - 1);
			invalidate();
		}
	}
	
	public void stamp_action(long id){
		stampBmpId = id;
		Bitmap bitmap = BitmapFactory.decodeResource(_context.getResources(), (int)stampBmpId);
		stampBmp = new BitmapDrawable(bitmap);
		mode=1;
	}
	
	public void saveToFile() {
		if (!sdcardWriteReady()) {
			Toast.makeText(_context, "SDcard ga nai", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		
		Bitmap newbitmap = Bitmap.createBitmap(bmpBack.getWidth(), bmpBack.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas saveImage = new Canvas(newbitmap);
		//saveImage.drawBitmap(bmpBack, 0, 0, null);
		saveImage.drawBitmap(bmpCanvas, 0, 0, null);

		File file = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/drawbm/");
		try {
			if (!file.exists()) {
				file.mkdir();
			}

		} catch (SecurityException e) {
		}

		String AttachName = file.getAbsolutePath() + "/";
		AttachName += "test" + saveNum + ".png";
		try {
			FileOutputStream out = new FileOutputStream(AttachName);
			newbitmap.compress(CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			if (saveNum != 1){
				
			}
			if (saveNum == 1)
				saveNum++;
		} catch (Exception e) {
			
		}
	}

	private boolean sdcardWriteReady() {
		String state = Environment.getExternalStorageState();
		return (Environment.MEDIA_MOUNTED.equals(state));
	}
}