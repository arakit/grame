package jp.keipro2013.grame;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RoutePreview extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2,imageButton3,imageButton4;
	static int d;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// WindowManager取得
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// Displayインスタンス生成
		Display dp = wm.getDefaultDisplay();
		// ディスプレイサイズ取得
		int dp_w = dp.getWidth();
		int dp_h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.modoru);
		Bitmap image1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/route.jpg");
		
		int deg = 90;
		Matrix mat = new Matrix();
		mat.postRotate( deg );
		int sw = image1.getWidth();
		int sh = image1.getHeight();

		image1 = Bitmap.createBitmap(image1, 0, 0, sw, sh, mat, true);
		
		image1 = Bitmap.createScaledBitmap(image1, dp_w, dp_h, false);
		// レイアウトの生成
		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundColor(Color.rgb(0, 0, 0));
		//layout.setBackgroundResource(R.drawable.grame);
		setContentView(layout);
		
		imageButton = new ImageButton(this);		
		imageButton.setImageBitmap(image);
		imageButton2 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.kakutei);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		imageButton3 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.no);
		imageButton3.setImageBitmap(image);
		imageButton3.setOnClickListener(this);
		imageButton.setOnClickListener(this);
		imageButton4 = new ImageButton(this);
		imageButton4.setImageBitmap(image1);
		// setLLParams(imageButton);
		
		RelativeLayout relativeLayout = new RelativeLayout(this);
	    
	    RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	     
	    // マージンを指定（左、上、右、下）
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
	    
	    addContentView(relativeLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.addView(imageButton4, new AbsoluteLayout.LayoutParams(dp_w, dp_h, 0, 0));// );
		//layout.addView(imageButton, new AbsoluteLayout.LayoutParams(dp_w / 2, 100, dp_w/4, dp_h - 250));
		//layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(dp_w / 2, 100, 0, dp_h - 250));
		//Toast.makeText(this, ""+CameraEx.latitude+" "+CameraEx.longtude, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == imageButton2){
			d=1;
			drawdialog();
		}else if (arg0 == imageButton){
			d=1;
			Intent intent1 = new Intent(RoutePreview.this, RouteActivity.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}
			
	}
	
	public void drawdialog(){AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("この写真を保存しますか？");        
		alertDialogBuilder.setPositiveButton("いいえ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	/*Intent intent1 = new Intent(Preview.this, CameraEx.class);
            			try {
            				startActivity(intent1);
            			} catch (Exception e) {

            			}*/
                    }
                });
		alertDialogBuilder.setNegativeButton("はい",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	try {
                    		Intent intent1 = new Intent(RoutePreview.this, SakuseiMenu.class);
                			try {
                				startActivity(intent1);
                			} catch (Exception e) {

                			}
                		} catch (Exception e) {

                		}
                    }
                });
		alertDialogBuilder.setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_BACK:
				// ダイアログ表示など特定の処理を行いたい場合はここに記述
				// 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}