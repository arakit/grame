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

		// WindowManager�擾
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// Display�C���X�^���X����
		Display dp = wm.getDefaultDisplay();
		// �f�B�X�v���C�T�C�Y�擾
		int dp_w = dp.getWidth();
		int dp_h = dp.getHeight();
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.soushin);

		// ���C�A�E�g�̐���
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
			// Intent�C���X�^���X�𐶐�  
			Intent intent = new Intent();  
			  
			// �A�N�V�������w��(ACTION_SENDTO�ł͂Ȃ��Ƃ��낪�~�\)  
			intent.setAction(Intent.ACTION_SEND);  
			// �f�[�^�^�C�v���w��  
			intent.setType("message/rfc822");  
			  
			// ������w��  
			intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"�Ղ����傳��"});  
			// CC���w��  
			//intent.putExtra(Intent.EXTRA_CC, new String[]{""});  
			// BCC���w��  
			//intent.putExtra(Intent.EXTRA_BCC, new String[]{"bcc"});  
			// �������w��  
			intent.putExtra(Intent.EXTRA_SUBJECT, "Graffiti Message");  
			// �{�����w��  
			intent.putExtra(Intent.EXTRA_TEXT, "A�l����B����f�G�ȃ��b�Z�[�W���͂��Ă��܂���B\n\n" +
					"�X�^�[�g�ʒu:http:\n\n" +
					"Graffiti Message���������łȂ�����\n" +
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
				// �_�C�A���O�\���ȂǓ���̏������s�������ꍇ�͂����ɋL�q
				// �e�N���X��dispatchKeyEvent()���Ăяo������true��Ԃ�
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}

