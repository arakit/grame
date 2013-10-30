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

public class Mail extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2;

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
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.soushin);

		// レイアウトの生成
		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundColor(Color.rgb(255, 255, 255));
		
		layout.setBackgroundResource(R.drawable.mail_bg);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setBackgroundDrawable(null);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.chushi);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setBackgroundDrawable(null);
		imageButton2.setOnClickListener(this);
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(dp_w / 2, 150, dp_w/4, dp_h /4));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(dp_w / 2, 150, dp_w/4, dp_h /5*3));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == imageButton){
			// Intentインスタンスを生成  
			Intent intent = new Intent();  
			  
			// アクションを指定(ACTION_SENDTOではないところがミソ)  
			intent.setAction(Intent.ACTION_SEND);  
			// データタイプを指定  
			intent.setType("message/rfc822");  
			  
			// 宛先を指定  
			intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ぷっちょさん"});  
			// CCを指定  
			//intent.putExtra(Intent.EXTRA_CC, new String[]{""});  
			// BCCを指定  
			//intent.putExtra(Intent.EXTRA_BCC, new String[]{"bcc"});  
			// 件名を指定  
			intent.putExtra(Intent.EXTRA_SUBJECT, "Graffiti Message");  
			// 本文を指定  
			intent.putExtra(Intent.EXTRA_TEXT, "A様宛にBから素敵なメッセージが届いていますよ。\n\n" +
					"スタート位置:http:\n\n" +
					"Graffiti Messageをお持ちでない方は\n" +
					"Android:http\n" +
					"iPhone:http");  
			try {
				startActivity(intent);
			} catch (Exception e) {

			}
		}else if (v == imageButton2){
			Intent intent1 = new Intent(Mail.this, PackageBOX.class);
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

