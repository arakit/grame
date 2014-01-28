package jp.keipro2013.grame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ImageButton;

public class PackageBOX extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2,imageButton3,imageButton4,imageButton5,imageButton6,imageButton7,imageButton8;
	static int w ,h,bm;
	String s;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		w = dp.getWidth();
		h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.enp);
		image = Bitmap.createScaledBitmap(image, w/5, w/5, false);

		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundResource(R.drawable.pbox);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.syusei);
		image = Bitmap.createScaledBitmap(image, w/5, w/5, false);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.mails);
		image = Bitmap.createScaledBitmap(image, w/5, w/5, false);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		imageButton3.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.gomi);
		image = Bitmap.createScaledBitmap(image, w/5, w/5, false);
		imageButton4 = new ImageButton(this);
		imageButton4.setImageBitmap(image);
		imageButton4.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.backt);
		image = Bitmap.createScaledBitmap(image, w/4, h/13, false);
		imageButton5 = new ImageButton(this);
		imageButton5.setImageBitmap(image);
		imageButton5.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.createt);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/10, false);
		bm=image.getWidth();
		imageButton6 = new ImageButton(this);
		imageButton6.setImageBitmap(image);
		imageButton6.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.pb1);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/7*2, false);
		imageButton7 = new ImageButton(this);
		imageButton7.setImageBitmap(image);
		imageButton7.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.pb2);
		image = Bitmap.createScaledBitmap(image, w/10*9, h/7*2, false);
		imageButton8 = new ImageButton(this);
		imageButton8.setImageBitmap(image);
		imageButton8.setOnClickListener(this);
		layout.addView(imageButton5, new AbsoluteLayout.LayoutParams(w/4, h/13, 0, 0));
		layout.addView(imageButton6, new AbsoluteLayout.LayoutParams(w/10*9, h/10, w/20, h/5));
		layout.addView(imageButton7, new AbsoluteLayout.LayoutParams(w/10*9, h/7*2, w/20, h/50*16));
		layout.addView(imageButton8, new AbsoluteLayout.LayoutParams(w/10*9, h/7*2, w/20, h/50*31));
		
	}

	@Override
	public void onClick(View v) {
		if (v == imageButton){
			Intent intent1 = new Intent(PackageBOX.this, SakuseiMenu.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}else if (v == imageButton2){
			Intent intent1 = new Intent(PackageBOX.this, SyuseiMenu.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}else if (v == imageButton3){
			Intent intent1 = new Intent(PackageBOX.this, Mail.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}else if (v == imageButton4){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setMessage("本当に削除しますか？");
			alertDialogBuilder.setNegativeButton("はい",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							
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
			}else if (v == imageButton5){
				Intent intent1 = new Intent(PackageBOX.this, Title.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
			}else if (v==imageButton6){
			    final EditText editView = new EditText(PackageBOX.this);
			    new AlertDialog.Builder(PackageBOX.this)
			        .setTitle("タイトル入力")
			        .setView(editView)
			        .setPositiveButton("キャンセル", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			                
			            }
			        })
			        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			            	s = editView.getText().toString();
			                Intent intent1 = new Intent(PackageBOX.this, SakuseiMenu.class);
			    			try {
			    				startActivity(intent1);
			    			} catch (Exception e) {

			    			}
			            }
			        })
			        .show();
			}else if (v==imageButton7||v==imageButton8){
				View layout1 = this.getLayoutInflater().inflate(R.layout.pbox,(ViewGroup) findViewById(R.id.layout_root));

				ImageButton img1 = (ImageButton) layout1.findViewById(R.id.img1);
				img1.setImageResource(R.drawable.enp);

				//ImageButton img2 = (ImageButton) layout1.findViewById(R.id.img2);
				//img2.setImageResource(R.drawable.syusei);
					
				ImageButton img3 = (ImageButton) layout1.findViewById(R.id.img3);
				img3.setImageResource(R.drawable.mails);
				
				ImageButton img4 = (ImageButton) layout1.findViewById(R.id.img4);
				img4.setImageResource(R.drawable.gomi);

				new AlertDialog.Builder(this).
				setView(layout1)
				.setCancelable(false) 		
				.setPositiveButton("キャンセル", new OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						}).show();
			}
	}
	
	public void detail_click(View v) {
		switch (v.getId()) {
		case R.id.img1:
			Intent intent1 = new Intent(PackageBOX.this, SakuseiMenu.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
			break;
		/*case R.id.img2:
			Intent intent2 = new Intent(PackageBOX.this, SyuseiMenu.class);
			try {
				startActivity(intent2);
			} catch (Exception e) {

			}
			break;*/
		case R.id.img3:
			Intent intent3 = new Intent(PackageBOX.this, Mail.class);
			try {
				startActivity(intent3);
			} catch (Exception e) {

			}
			break;
		case R.id.img4:
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setMessage("本当に削除しますか？");
			alertDialogBuilder.setNegativeButton("はい",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							//delete
							//deleteClass a = new deleteClass();
							
							{}
							Intent intent4 = new Intent(PackageBOX.this, PackageBOX.class);
							try {
								startActivity(intent4);
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
			break;
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