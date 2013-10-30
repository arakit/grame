package jp.keipro2013.grame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import android.view.Window;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

//�C���[�W�̕`��
public class PuzzleBlack extends Activity implements View.OnClickListener {

	PuzzleBlackView imageview;
	int a = 0;
	static int s;
	static int g;
	static int a2=PuzzleActivity.a2;
	//static int a2=7;
	Intent intent;

	private ImageButton Button, Button2, Button3;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

	int hozon = 0;

	// �A�N�e�B�r�e�B�N�����ɌĂ΂��
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		imageview = new PuzzleBlackView(this);
		setContentView(imageview);

		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.kanryou);
		image = Bitmap.createScaledBitmap(image, 360, 108, false);

		Button = new ImageButton(this);
		Button.setImageBitmap(image);
		Button.setOnClickListener(this);
		
RelativeLayout relativeLayout = new RelativeLayout(this);
	    
	    RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	     
	    // �}�[�W�����w��i���A��A�E�A���j
	    param.setMargins(5, 5, 15, 0);
	    param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param.addRule(RelativeLayout.CENTER_HORIZONTAL, 2);
	    relativeLayout.addView(Button, param);

	    addContentView(relativeLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    
	    new AlertDialog.Builder(this)
    	.setCancelable(false)    	
    	.setMessage("�h��Ԃ������p�[�c���^�b�v���Ă��������B\n������x�^�b�v����Ɩ߂�܂��B")    	
    	.setPositiveButton("�͂�",null)    	
    	.show();
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == Button){
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
			
			//imageview.saveToFile(a2, s, g);
			
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

				alertDialogBuilder.setMessage("����Ńp�Y�������s���܂��B");
				alertDialogBuilder.setNegativeButton("�͂�",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								try {
									imageview.saveToFile(a2, s, g);
									hozon = 1;
									
									Intent intent1 = new Intent(PuzzleBlack.this, SakuseiMenu.class);
										
									startActivity(intent1);
										} catch (Exception e) {
											
										}
							}
						});
				alertDialogBuilder.setNeutralButton("������",
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
