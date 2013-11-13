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
import android.view.Gravity;
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

public class Preview extends Activity implements View.OnClickListener {
	private ImageButton imageButton,imageButton2,imageButton3,imageButton4;
	static int d;
	private ImageView images;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// WindowManager�擾
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// Display�C���X�^���X����
		Display dp = wm.getDefaultDisplay();
		// �f�B�X�v���C�T�C�Y�擾
		int w = dp.getWidth();
		int h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.modoru);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		Bitmap image1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/test.jpg");
		
		int deg = 90;
		Matrix mat = new Matrix();
		mat.postRotate( deg );
		int sw = image1.getWidth();
		int sh = image1.getHeight();

		image1 = Bitmap.createBitmap(image1, 0, 0, sw, sh, mat, true);
		image1 = Bitmap.createScaledBitmap(image1, w, h, false);
		// ���C�A�E�g�̐���
		AbsoluteLayout layout = new AbsoluteLayout(this);
		layout.setBackgroundColor(Color.rgb(0, 0, 0));
		//layout.setBackgroundResource(R.drawable.grame);
		setContentView(layout);
		
		imageButton = new ImageButton(this);		
		imageButton.setImageBitmap(image);
		imageButton.setBackgroundDrawable(null);
		imageButton.setOnClickListener(this);
		imageButton2 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.kakutei);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton2.setImageBitmap(image);
		imageButton2.setOnClickListener(this);
		imageButton2.setBackgroundDrawable(null);
		imageButton3 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.no);
		image = Bitmap.createScaledBitmap(image, w/3, h/10, false);
		imageButton3.setImageBitmap(image);
		imageButton3.setOnClickListener(this);
		imageButton3.setBackgroundDrawable(null);
		imageButton4 = new ImageButton(this);
		image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/drawbm/test.jpg");
		image = Bitmap.createBitmap(image, 0, 0, sw, sh, mat, true);
		image = Bitmap.createScaledBitmap(image, w, h, false);
		images = new ImageView(this);
		images.setImageBitmap(image);
		//imageButton4.setImageBitmap(image);
		// setLLParams(imageButton);
		
		/*RelativeLayout relativeLayout = new RelativeLayout(this);
	    
	    RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	     
	    // �}�[�W�����w��i���A��A�E�A���j
	    param.setMargins(0, 0, 0, 0);
	    param2.setMargins(5, 5, 15, 0);
	    param3.setMargins(5, 5, 15, 0);
	    param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 2);
	    param2.addRule(RelativeLayout.CENTER_HORIZONTAL, 2);
	    param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 2);
	    relativeLayout.addView(imageButton, param);
	    relativeLayout.addView(imageButton2, param2);
	    relativeLayout.addView(imageButton3, param3);*/
	    
	    //addContentView(relativeLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    //layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w / 3, h/10, 0, h-h/10));
		//layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3, h-h/10));
		//layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3*2, h-h/10));
		layout.addView(images, new AbsoluteLayout.LayoutParams(w, h, 0, 0));// );
		layout.addView(imageButton, new AbsoluteLayout.LayoutParams(w / 3, h/10, 0, h - h/10 -50));
		layout.addView(imageButton2, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3, h - h/10 -50));
		layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(w/3, h/10, w/3*2, h - h/10 -50));
		//layout.addView(imageButton, new AbsoluteLayout.LayoutParams(dp_w / 2, 100, dp_w/4, dp_h - 250));
		//layout.addView(imageButton3, new AbsoluteLayout.LayoutParams(dp_w / 2, 100, 0, dp_h - 250));
		//Toast.makeText(this, ""+CameraEx.latitude+" "+CameraEx.longtude, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == imageButton2){
			d=1;
			drawdialog();
		}else if (arg0 == imageButton){
			d=1;
			Intent intent1 = new Intent(Preview.this, CameraEx.class);
			try {
				startActivity(intent1);
			} catch (Exception e) {

			}
		}
			
	}
	
	public void drawdialog(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("���̎ʐ^��ۑ����܂����H");        
		alertDialogBuilder.setPositiveButton("������",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	/*Intent intent1 = new Intent(Preview.this, CameraEx.class);
            			try {
            				startActivity(intent1);
            			} catch (Exception e) {

            			}*/
                    }
                });
		alertDialogBuilder.setNegativeButton("�͂�",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	try {
                    		Intent intent1 = new Intent(Preview.this, DrawBm.class);
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
				// �_�C�A���O�\���ȂǓ���̏������s�������ꍇ�͂����ɋL�q
				// �e�N���X��dispatchKeyEvent()���Ăяo������true��Ԃ�
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}
