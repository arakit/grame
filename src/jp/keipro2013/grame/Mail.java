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
	int w,h;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		w = dp.getWidth();
		h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.soushin);
		image = Bitmap.createScaledBitmap(image, w/2, h/8, false);

		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundColor(Color.rgb(255, 255, 255));
		
		layout.setBackgroundResource(R.drawable.mail_bg);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setBackgroundDrawable(null);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.chushi);
		image = Bitmap.createScaledBitmap(image, w/2, h/8, false);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setBackgroundDrawable(null);
		imageButton2.setOnClickListener(this);
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w / 2, h/8, w/4, h /4));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w / 2, h/8, w/4, h /5*3));
	}

	@Override
	public void onClick(View v) {
		if (v == imageButton){
			Intent intent = new Intent();  
			
			intent.setAction(Intent.ACTION_SEND);  
			intent.setType("message/rfc822");  
			  
			intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"matsuboo"});  
			//intent.putExtra(Intent.EXTRA_CC, new String[]{"CC"});  
			//intent.putExtra(Intent.EXTRA_BCC, new String[]{"BCC"});  
			intent.putExtra(Intent.EXTRA_SUBJECT, "Graffiti Message");  
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
				finish();
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
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}

