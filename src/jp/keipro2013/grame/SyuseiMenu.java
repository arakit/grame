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

public class SyuseiMenu extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2,imageButton3;
	static int w ,h,bm;

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
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.r_syusei);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/6, false);
		bm=image.getWidth();
		// レイアウトの生成
		AbsoluteLayout layout = new AbsoluteLayout(this);
		//layout.setBackgroundColor(Color.rgb(255, 255, 255));
		
		layout.setBackgroundResource(R.drawable.s_menu);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		//imageButton.setBackgroundDrawable(null);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.rj_syusei);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/6, false);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		//imageButton2.setBackgroundDrawable(null);
		imageButton2.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.modoru);
		image = Bitmap.createScaledBitmap(image, w/4, h/13, false);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		//imageButton3.setBackgroundDrawable(null);
		imageButton3.setOnClickListener(this);

		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w/10*9, h/6, w/20, h/5));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/10*9, h/6, w/20, h/5*3));
		layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(w / 4, h/13, 0, 0));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == imageButton){
			/*Intent intent1 = new Intent(SyuseiMenu.this, CameraEx.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}*/
		}else if (v == imageButton2){
			/*Intent intent1 = new Intent(SyuseiMenu.this, CameraEx.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}*/
		}else if (v == imageButton3){
			Intent intent1 = new Intent(SyuseiMenu.this, PackageBOX.class);
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
				// ダイアログ表示など特定の処理を行いたい場合はここに記述
				// 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}