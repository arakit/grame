package jp.keipro2013.grame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import android.view.Window;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PuzzleActivity extends Activity implements View.OnClickListener {

	PuzzleView imageview;
	int a = 0;
	static int s;
	static int g;
	static int a2;
	
	int w,h;
	Intent intent;

	private ImageButton Button, Button2, Button3;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

	int hozon = 0;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		imageview = new PuzzleView(this);
		setContentView(imageview);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		w = dp.getWidth();
		h = dp.getHeight();

		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.modoru);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		Button = new ImageButton(this);
		Button.setImageBitmap(image);
		Button.setOnClickListener(this);

		Button2 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.ok);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		Button2.setImageBitmap(image);
		Button2.setOnClickListener(this);

		Button3 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.susumu);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		Button3.setImageBitmap(image);
		Button3.setOnClickListener(this);
		
		AbsoluteLayout layout = new AbsoluteLayout(this);
	    addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    layout.addView(Button, new AbsoluteLayout.LayoutParams(w / 3, h/10, 0, h-h/10-50));
		layout.addView(Button2, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3, h-h/10-50));
		layout.addView(Button3, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3*2, h-h/10-50));
	}

	public void onClick(View v) {
		if (v == Button2){
			if (a2 <= 1) {
				AlertDialog.Builder ad = new AlertDialog.Builder(this);
				ad.setTitle("エラー");
				ad.setMessage("パズル化する数を選択してください。");
				ad.setPositiveButton("OK", null);
				ad.show();
			} else if (a2 >= 2) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

				alertDialogBuilder.setMessage("これでパズル化してもいいですか？");
				alertDialogBuilder.setNegativeButton("はい",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								try {
									if(a2==2){
										s = 0;
										g = 2;
									}else if(a2==3){
										s = 2;
										g = 5;
									}else if(a2==4){
										s = 5;
										g = 9;
									}else if(a2==5){
										s  = 9;
										g = 14;
									}else if(a2==6){
										s = 14;
										g = 20;
									}else if(a2==7){
										s = 20;
										g = 27;
									}else if(a2==8){
										s = 27;
										g = 35;
									}else if(a2==9){
										s = 35;
										g = 44;
									}else if(a2==10){
										s = 44;
										g = 54;
									}
									imageview.saveToFile(a2, s, g);
									hozon = 1;
									AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
											PuzzleActivity.this);

									alertDialogBuilder
											.setMessage("パーツを塗りつぶしますか？");
									alertDialogBuilder
											.setNegativeButton(	"はい",new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int which) {
																Intent intent1 = new Intent(PuzzleActivity.this, PuzzleBlack.class);
																try {
																	startActivity(intent1);
																} catch (Exception e) {

																}
															}
													});
									alertDialogBuilder.setPositiveButton("いいえ",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int which) {
																Intent intent1 = new Intent(PuzzleActivity.this, SakuseiMenu.class);
																try {
																	startActivity(intent1);
																} catch (Exception e) {

																}
														}
													});
									alertDialogBuilder.setCancelable(false);
									AlertDialog alertDialog = alertDialogBuilder
											.create();
									alertDialog.show();
								} catch (Exception e) {
									Toast.makeText(PuzzleActivity.this, "You can't save file.", Toast.LENGTH_SHORT).show();
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
		}else if (v == Button){
			if(a2 > 2)
				a2--;			
		}else if (v == Button3){
			if(a2<10)
				a2++;
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