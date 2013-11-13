package jp.keipro2013.grame;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
//import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.ContentResolver;
import android.content.Context;
//import android.content.ContextWrapper;
//import android.content.DialogInterface;
//import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
//import android.graphics.Path;
//import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
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
	
	private Canvas dCanvas;	//繧ｭ繝｣繝ｳ繝舌せ
	private Bitmap bmpCanvas = null;	//繧ｭ繝｣繝ｳ繝舌せ縺ｮ逕ｻ蜒�
	private Bitmap bmpBack = null;		//閭梧勹逕ｻ蜒�
	int saveNum = 1;					//菫晏ｭ伜屓謨ｰ
	
	private Activity _context;	//繧ｳ繝ｳ繝�け繧ｹ繝�
	
	//謠冗判繝｢繝ｼ繝�
	private int mode = 0;

	//繧｢繝ｳ繝峨ぇ繝ｻ繝ｪ繝峨ぇ
	private ArrayList<Bitmap> undoBitmap = new ArrayList<Bitmap>();	//undo縺ｮ逕ｻ蜒�
	private ArrayList<Bitmap> redoBitmap = new ArrayList<Bitmap>();	//redo縺ｮ逕ｻ蜒�
	private int backMaxNum = 5;	//繧｢繝ｳ繝峨ぇ縺ｮ譛�､ｧ蝗樊焚

	//繝壹Φ繝ｻ豸医＠繧ｴ繝�
	private Paint paint;
	private int color = Color.MAGENTA;
	private int penSize=6, erasorSize=12;

	//繧ｹ繧ｿ繝ｳ繝�
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
		//画像の回転
		int deg = 90;
		Matrix mat = new Matrix();
		mat.postRotate( deg );
		int sw = bmpBack.getWidth();
		int sh = bmpBack.getHeight();
		bmpBack = Bitmap.createBitmap(bmpBack, 0, 0, sw, sh, mat, true);
		dCanvas = new Canvas(bmpCanvas);
		dCanvas.drawColor(0, PorterDuff.Mode.CLEAR);

		// 蜀咏悄縺ｮ蜻ｼ縺ｳ蜃ｺ縺励�縺､縺�〒縺ｫ蜀咏悄縺ｮ繝ｪ繧ｵ繧､繧ｺ繧�繧｢繧ｹ繝壹け繝域ｯ斐�邯ｭ謖√＠縺､縺､逕ｻ髱｢繧ｵ繧､繧ｺ縺ｫ蜷医ｏ縺帙ｋ縲ゑｼ�
		// 繧ｹ繧ｯ繝ｪ繝ｼ繝ｳ繧ｵ繧､繧ｺ縺ｮ蜿門ｾ�
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
		// bmpBack = Bitmap.createBitmap(bmpBack, 0, 0, (int)photoWidth,
		// (int)photoHeight, matrix,true);
		bmpBack = Bitmap.createScaledBitmap(bmpBack, (int) screenWidth,
				(int) screenHeight, false); // 竊腎est.jpg繧�196ﾃ�20縺ｫ繝ｪ繧ｵ繧､繧ｺ縺励※繧�
		//dCanvas.drawBitmap(bmpBack, 0, 0, null);
		//dCanvas.drawBitmap(bmpCanvas, 0, 0, null);
	}

	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bmpBack, 0, 0, null);
		canvas.drawBitmap(bmpCanvas, 0, 0, null);
		if (saveNum == 1)
			saveToFile();
	}

	public boolean onTouchEvent(MotionEvent e) {
		/*
		 * 繧ｿ繝�メ縺励◆繧英ath繧剃ｽｿ縺｣縺ｦ邱壹�霆瑚ｷ｡繧剃ｽ懊ｋ縲ゅ�髮｢縺励◆繧峨Μ繧ｹ繝医↓霑ｽ蜉�☆繧九�
		 */
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN: // 譛��縺ｮ繝昴う繝ｳ繝�
			//逕ｻ蜒上�繧ｭ繝｣繝�す繝･繧貞叙繧�
			if (undoBitmap.size() >= backMaxNum)	undoBitmap.remove(0);
			undoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
			//redo繧ｭ繝｣繝�す繝･縺ｮ繧ｵ繧､繧ｺ縺�繧医ｊ螟ｧ縺阪°縺｣縺溘ｉ蜈ｨ驛ｨ豸医☆
			if(redoBitmap.size() > 0) redoBitmap.clear();

			//繝壹Φ繝ｻ豸医＠繧ｴ繝�Δ繝ｼ繝峨〒縺ｮ蜃ｦ逅�
			if(mode == 0){
				oldx = e.getX();
				oldy = e.getY();
			}
			//繧ｹ繧ｿ繝ｳ繝励Δ繝ｼ繝峨〒縺ｮ蜃ｦ逅�
			if(mode == 1){				
			}
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE: // 騾比ｸｭ縺ｮ繝昴う繝ｳ繝�
			//繝壹Φ繝ｻ豸医＠繧ｴ繝�Δ繝ｼ繝峨〒縺ｮ蜃ｦ逅�
			if(mode == 0){
				dCanvas.drawLine(oldx, oldy, e.getX(), e.getY(), paint);
				oldx = e.getX();
				oldy = e.getY();
			}
			//繧ｹ繧ｿ繝ｳ繝励Δ繝ｼ繝峨〒縺ｮ蜃ｦ逅�
			if(mode == 1){
			}
			invalidate();
			break;
		case MotionEvent.ACTION_UP: //縺ゅ￡縺溘ｉ
			//繧ｹ繧ｿ繝ｳ繝励Δ繝ｼ繝峨〒縺ｮ蜃ｦ逅�
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
		//逕ｻ蜒上�繧ｭ繝｣繝�す繝･繧貞叙繧�
		if (undoBitmap.size() >= backMaxNum)	undoBitmap.remove(0);
		undoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
		//redo繧ｭ繝｣繝�す繝･縺ｮ繧ｵ繧､繧ｺ縺�繧医ｊ螟ｧ縺阪°縺｣縺溘ｉ蜈ｨ驛ｨ豸医☆
		if(redoBitmap.size() > 0) redoBitmap.clear();
		
		//繧ｭ繝｣繝ｳ繝舌せ繧帝�譏弱〒蝪励ｊ縺､縺ｶ縺�
		dCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		
		invalidate();
	}
	

	public void pen_action() {
		// 縺ｨ繧翫≠縺医★邁｡蜊倥↓濶ｲ螟画峩縺�￠�医�繧ｼ繝ｳ繧ｿ��
		// color = Color.MAGENTA;
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC));
		paint.setStrokeWidth(penSize);
		//mode繧��昴�繝ｳ繝ｻ豸医＠繧ｴ繝�Δ繝ｼ繝峨↓險ｭ螳�
		mode=0;
		//Toast.makeText(_context, "繝壹Φ", Toast.LENGTH_SHORT).show();
		// paint.setColor(Color.MAGENTA);
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
		// 謠冗判濶ｲ繧帝�譏弱↓縲�
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.CLEAR));
		paint.setStrokeWidth(erasorSize);
		//mode繧��昴�繝ｳ繝ｻ豸医＠繧ｴ繝�Δ繝ｼ繝峨↓險ｭ螳�
		mode=0;
		//Toast.makeText(_context, "豸医＠繧ｴ繝�", Toast.LENGTH_SHORT).show();
	}
	public void erasorSize_action(int size){
		erasorSize = size + 12;
		this.erasor_action();
	}

	public void undo_action() {
		String n = Integer.toString(undoBitmap.size());
		//Toast.makeText(_context, "謌ｻ繧具ｼ�+n", Toast.LENGTH_SHORT).show();
		if (undoBitmap.size() > 0) {
			//莉翫�迥ｶ諷九ｒredo縺ｫ蜈･繧後ｋ
			redoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
			
			//繧ｭ繝｣繝�す繝･縺九ｉ蜿悶ｊ蜃ｺ縺励※繧ｭ繝｣繝ｳ繝舌せ縺ｫ險ｭ螳壹＠縺ｪ縺翫☆
			bmpCanvas = undoBitmap.get(undoBitmap.size() - 1);
			dCanvas = new Canvas(bmpCanvas);
			undoBitmap.remove(undoBitmap.size() - 1);
			invalidate();
		}
	}
	
	public void redo_action() {
		String n = Integer.toString(redoBitmap.size());
		//Toast.makeText(_context, "騾ｲ繧�ｼ�+n", Toast.LENGTH_SHORT).show();
		if (redoBitmap.size() > 0) {
			//莉翫�迥ｶ諷九ｒundo縺ｫ蜈･繧後ｋ
			undoBitmap.add(bmpCanvas.copy(Bitmap.Config.ARGB_8888, true));
			
			//繧ｭ繝｣繝�す繝･縺九ｉ蜿悶ｊ蜃ｺ縺励※繧ｭ繝｣繝ｳ繝舌せ縺ｫ險ｭ螳壹＠縺ｪ縺翫☆
			bmpCanvas = redoBitmap.get(redoBitmap.size() - 1);
			dCanvas = new Canvas(bmpCanvas);
			redoBitmap.remove(redoBitmap.size() - 1);
			invalidate();
		}
	}
	
	public void stamp_action(long id){
		//逕ｻ蜒上�ID
		stampBmpId = id;
		//Bitmap繧剃ｽ懈�
		Bitmap bitmap = BitmapFactory.decodeResource(_context.getResources(), (int)stampBmpId); //縺薙％縺ｮ繧ｭ繝｣繧ｹ繝域�縺励＞��
		//BitmapDrawable繧剃ｽ懈�
		stampBmp = new BitmapDrawable(bitmap);
		//mode繧��昴せ繧ｿ繝ｳ繝励Δ繝ｼ繝峨↓險ｭ螳�
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
		saveImage.drawBitmap(bmpBack, 0, 0, null);
		saveImage.drawBitmap(bmpCanvas, 0, 0, null);

		File file = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/drawbm/");
		try {
			// file.mkdir();

			if (!file.exists()) {
				file.mkdir();
			}

		} catch (SecurityException e) {
		}

		String AttachName = file.getAbsolutePath() + "/";
		AttachName += "test" + saveNum + ".jpg";// System.currentTimeMillis()+".jpg";
		// File saveFile = new File(AttachName);
		/*
		 * while(saveFile.exists()) { AttachName = file.getAbsolutePath() +
		 * "/test2.jpg"; //+ System.currentTimeMillis()+".jpg"; saveFile = new
		 * File(AttachName); }
		 */
		try {
			FileOutputStream out = new FileOutputStream(AttachName);
			newbitmap.compress(CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
			if (saveNum != 1){
				
			}
				//Toast.makeText(_context, "菫晏ｭ倥＆繧後∪縺励◆縲�", Toast.LENGTH_SHORT).show();
			if (saveNum == 1)
				saveNum++;
		} catch (Exception e) {
			//Toast.makeText(_context, "萓句､也匱逕�", Toast.LENGTH_SHORT).show();
		}
	}

	private boolean sdcardWriteReady() {
		String state = Environment.getExternalStorageState();
		return (Environment.MEDIA_MOUNTED.equals(state));
	}
}