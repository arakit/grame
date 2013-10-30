package jp.keipro2013.grame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
	static int dp_w ,dp_h;
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
		dp_w = dp.getWidth();
		dp_h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.tsukuru);

		// レイアウトの生成
		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundColor(Color.rgb(0, 0, 0));
		
		layout.setBackgroundResource(R.drawable.grame);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.miru);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		// setLLParams(imageButton);
		images = new ImageView(this);
		layout.addView(images, new AbsoluteLayout.LayoutParams(dp_w+1, dp_h+1, -1, -1));
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(dp_w / 4, 100, dp_w/7, dp_h - 250));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(dp_w / 4, 100, dp_w/5*3, dp_h - 250));
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
		}else if (v == imageButton2&&a==0){
			Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.grame2);
			image = Bitmap.createScaledBitmap(image, dp_w+2, dp_h+2, false);
			images.setImageBitmap(image);
			a=1;
		}else if(v == imageButton2&&a==1){
			PuzzleActivity.a2=9;
			Intent intent1 = new Intent(Title.this, PuzzleBlack.class);
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