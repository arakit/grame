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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RoutePreview extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2,imageButton3,imageButton4;
	static int d;
	private ImageView images;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		int w = dp.getWidth();
		int h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.backt);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		
		int deg = 90;
		Matrix mat = new Matrix();
		mat.postRotate( deg );

		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundColor(Color.rgb(0, 0, 0));
		setContentView(layout);
		
		imageButton = new ImageButton(this);		
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		imageButton2 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.ok);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		imageButton3 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.nont);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton3.setImageBitmap(image);
		imageButton3.setOnClickListener(this);
		
		images = new ImageView(this);
		image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/route.jpg");
		int sw = image.getWidth();
		int sh = image.getHeight();
		image = Bitmap.createBitmap(image, 0, 0, sw, sh, mat, true);
		image = Bitmap.createScaledBitmap(image, w, h, false);
		images.setImageBitmap(image);
		layout.addView(images, new AbsoluteLayout.LayoutParams(w, h, 0, 0));
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w / 3, h/10, 0, h - h/10 -50));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3, h - h/10 -50));
		layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3*2, h - h/10 -50));
	}

	@Override
	public void onClick(View arg0) {
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
		alertDialogBuilder.setMessage("Ç±ÇÃé ê^Çï€ë∂ÇµÇ‹Ç∑Ç©ÅH");        
		alertDialogBuilder.setPositiveButton("Ç¢Ç¢Ç¶",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	
                    }
                });
		alertDialogBuilder.setNegativeButton("ÇÕÇ¢",
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
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}