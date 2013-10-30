package jp.keipro2013.grame;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PackageBOX extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2,imageButton3,imageButton4,imageButton5,imageButton6,imageButton7,imageButton8;
	static int dp_w ,dp_h,bm;
	String s;

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
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.enpitsu);
		
		// レイアウトの生成
		AbsoluteLayout layout = new AbsoluteLayout(this);
		//layout.setBackgroundColor(Color.rgb(255, 255, 255));
		layout.setBackgroundResource(R.drawable.pbox);
		setContentView(layout);
		
		imageButton = new ImageButton(this);
		imageButton.setImageBitmap(image);
		//imageButton.setBackgroundDrawable(null);
		imageButton.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.syusei);
		imageButton2 = new ImageButton(this);
		imageButton2.setImageBitmap(image);
		//imageButton2.setBackgroundDrawable(null);
		imageButton2.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.mails);
		imageButton3 = new ImageButton(this);
		imageButton3.setImageBitmap(image);
		//imageButton3.setBackgroundDrawable(null);
		imageButton3.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.gomi);
		imageButton4 = new ImageButton(this);
		imageButton4.setImageBitmap(image);
		//imageButton4.setBackgroundDrawable(null);
		imageButton4.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.modoru);
		imageButton5 = new ImageButton(this);
		imageButton5.setImageBitmap(image);
		//imageButton5.setBackgroundDrawable(null);
		imageButton5.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.sinki);
		bm=image.getWidth();
		imageButton6 = new ImageButton(this);
		imageButton6.setImageBitmap(image);
		imageButton6.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.pb1);
		imageButton7 = new ImageButton(this);
		imageButton7.setImageBitmap(image);
		imageButton7.setOnClickListener(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.pb2);
		imageButton8 = new ImageButton(this);
		imageButton8.setImageBitmap(image);
		imageButton8.setOnClickListener(this);
		//layout.addView(imageButton, new AbsoluteLayout.LayoutParams(140, 140, (dp_w-560)/5, dp_h/2 - 60));
		//layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(140, 140, 140+(dp_w-560)/5*2, dp_h/2 - 60));
		//layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(140, 140, 280+(dp_w-560)/5*3, dp_h/2 - 60));
		//layout.addView(imageButton4, new AbsoluteLayout.LayoutParams(140, 140, 420+(dp_w-560)/5*4, dp_h/2 - 60));
		layout.addView(imageButton5, new AbsoluteLayout.LayoutParams(dp_w / 4, 90, 0, 0));
		layout.addView(imageButton6, new AbsoluteLayout.LayoutParams(bm+20, 140, (dp_w-bm-20)/2, dp_h/6+50));
		layout.addView(imageButton7, new AbsoluteLayout.LayoutParams(bm+20, 340, (dp_w-bm-20)/2, dp_h/3));
		layout.addView(imageButton8, new AbsoluteLayout.LayoutParams(bm+20, 340, (dp_w-bm-20)/2, dp_h/3*2-50));
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
			        //.setIcon(android.R.drawable.ic_dialog_info)
			        .setTitle("タイトル入力")
			        .setView(editView)
			        .setPositiveButton("キャンセル", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			                
			            }
			        })
			        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			            	//入力した文字をトースト出力する
			            	s = editView.getText().toString();
			                Toast.makeText(PackageBOX.this, s, Toast.LENGTH_SHORT).show();//editView.getText().toString(), Toast.LENGTH_SHORT).show();
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
				img1.setImageResource(R.drawable.enpitsu);

				ImageButton img2 = (ImageButton) layout1.findViewById(R.id.img2);
				img2.setImageResource(R.drawable.syusei);
					
				ImageButton img3 = (ImageButton) layout1.findViewById(R.id.img3);
				img3.setImageResource(R.drawable.mails);
				
				ImageButton img4 = (ImageButton) layout1.findViewById(R.id.img4);
				img4.setImageResource(R.drawable.gomi);

				new AlertDialog.Builder(this).//setTitle("").
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
		case R.id.img2:
			Intent intent2 = new Intent(PackageBOX.this, SyuseiMenu.class);
			try {
				startActivity(intent2);
			} catch (Exception e) {

			}
			break;
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
				// ダイアログ表示など特定の処理を行いたい場合はここに記述
				// 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

}

