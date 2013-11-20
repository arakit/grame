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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SakuseiMenu extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2,imageButton3,imageButton4;
	static int w ,h,bm;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		w = dp.getWidth();
		h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.messaget);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/6, false);
		bm=image.getWidth();
		AbsoluteLayout layout = new AbsoluteLayout(this);

		layout.setBackgroundResource(R.drawable.sakusei_bg);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.route);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/6, false);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.pouse);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/6, false);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		imageButton3.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.finish);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/6, false);
		imageButton4 = new ImageButton(this);
		imageButton4.setImageBitmap(image);
		imageButton4.setOnClickListener(this);
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w/10*9, h/6, w/20, h/5-h/20));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/10*9, h/6, w/20, h/5*2-h/20));
		layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(w/10*9, h/6, w/20, h/5*3-h/20));
		layout.addView(imageButton4, new AbsoluteLayout.LayoutParams(w/10*9, h/6, w/20, h/5*4-h/20));
	}

	@Override
	public void onClick(View v) {
		if (v == imageButton){
			Intent intent1 = new Intent(SakuseiMenu.this, CameraEx.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}else if (v == imageButton2){
			Intent intent1 = new Intent(SakuseiMenu.this, RouteActivity.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}else if (v == imageButton3){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setMessage("保存しました。\nパッケージBOX画面に戻りますか？");
			alertDialogBuilder.setNegativeButton("はい",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							Intent intent1 = new Intent(SakuseiMenu.this, PackageBOX.class);
							try {
								startActivity(intent1);
							} catch (Exception e) {

							}
						}
			});
			alertDialogBuilder.setNeutralButton("いいえ",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int which) {

				}
			});
			alertDialogBuilder.setCancelable(false);
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}else if (v == imageButton4){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setMessage("パッケージ作成を完了しますか？");
			alertDialogBuilder.setNegativeButton("はい",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							Intent intent1 = new Intent(SakuseiMenu.this, PackageBOX.class);
							try {
								startActivity(intent1);
							} catch (Exception e) {

							}
						}
			});
			alertDialogBuilder.setNeutralButton("いいえ",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int which) {

				}
			});
			alertDialogBuilder.setCancelable(false);
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_BACK:
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}