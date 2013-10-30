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
	static int dp_w ,dp_h,bm;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// WindowManager取得
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// Displayインスタンス生成
		Display dp = wm.getDefaultDisplay();
		// ディスプレイサイズ取得
		dp_w = dp.getWidth();
		dp_h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.sakusei);
		bm=image.getWidth();
		// レイアウトの生成
		AbsoluteLayout layout = new AbsoluteLayout(this);
		//layout.setBackgroundColor(Color.rgb(255, 255, 255));
		
		layout.setBackgroundResource(R.drawable.sakusei_bg);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		//imageButton.setBackgroundDrawable(null);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.route);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		//imageButton2.setBackgroundDrawable(null);
		imageButton2.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.ichizihozon);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		//imageButton3.setBackgroundDrawable(null);
		imageButton3.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.kanryou);
		imageButton4 = new ImageButton(this);
		imageButton4.setImageBitmap(image);
		//imageButton4.setBackgroundDrawable(null);
		imageButton4.setOnClickListener(this);
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(bm+30, 220, (dp_w-bm-30)/2, dp_h/5-50));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(bm+30, 220, (dp_w-bm-30)/2, dp_h/5*2-50));
		layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(bm+30, 220, (dp_w-bm-30)/2, dp_h/5*3-50));
		layout.addView(imageButton4, new AbsoluteLayout.LayoutParams(bm+30, 220, (dp_w-bm-30)/2, dp_h/5*4-50));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
				// ダイアログ表示など特定の処理を行いたい場合はここに記述
				// 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}