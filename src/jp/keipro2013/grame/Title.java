package jp.keipro2013.grame;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Title extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2;
	private ImageView images;
	static int w ,h;
	int a=0;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// WindowManager取得
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// Displayインスタンス生成
		Display dp = wm.getDefaultDisplay();
		// ディスプレイサイズ取得
		w = dp.getWidth();
		h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.tsukuru);
		image = Bitmap.createScaledBitmap(image, w/3, h/9, false);

		// レイアウトの生成
		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundColor(Color.rgb(0, 0, 0));
		
		layout.setBackgroundResource(R.drawable.grame);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.miru);
		image = Bitmap.createScaledBitmap(image, w/3, h/9, false);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		// setLLParams(imageButton);
		images = new ImageView(this);
		layout.addView(images, new AbsoluteLayout.LayoutParams(w+1, h+1, -1, -1));
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w/3 , h/10, w/9, h/4*3));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/3, h/10, w/9*5, h/4*3));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == imageButton){
			Intent intent1 = new Intent(Title.this, PackageBOX.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}else if (v == imageButton2){
			/*Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.grame2);
			image = Bitmap.createScaledBitmap(image, w+2, h+2, false);
			images.setImageBitmap(image);*/
			Intent intent1 = new Intent(Title.this, MainActivity.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_BACK:
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
				alertDialogBuilder.setMessage("Graffiti Messageを終了しますか？");        
				alertDialogBuilder.setPositiveButton("いいえ",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                    	
		                    }
		                });
				alertDialogBuilder.setNegativeButton("はい",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                    	try {
		                    		finish();
		                			} catch (Exception e) {

		                			}
		                    }
		                });
				alertDialogBuilder.setCancelable(false);
		        AlertDialog alertDialog = alertDialogBuilder.create();
		        alertDialog.show();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}