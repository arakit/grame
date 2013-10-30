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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
public class PuzzleActivity extends Activity implements View.OnClickListener {

	PuzzleView imageview;
	int a = 0;
	static int s;
	static int g;
	static int a2;
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
		imageview = new PuzzleView(this);
		setContentView(imageview);

		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.hidari);

		Button = new ImageButton(this);
		Button.setImageBitmap(image);
		Button.setOnClickListener(this);
		// imageview.addView(Button, new AbsoluteLayout.LayoutParams(0, 0, 0,
		// 150));

		Button2 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.kettei);
		Button2.setImageBitmap(image);
		Button2.setOnClickListener(this);
		// ((ViewManager) imageview).addView(Button, new
		// AbsoluteLayout.LayoutParams(10, 10, 10, 150));

		Button3 = new ImageButton(this);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.migi);
		Button3.setImageBitmap(image);
		Button3.setOnClickListener(this);
		// imageview.addView(Button, new AbsoluteLayout.LayoutParams(0, 0, 0,
		// 150));

		/*
		 * setContentView(Button, new LayoutParams(WC, WC));
		 * setContentView(Button2, new LayoutParams(WC, WC));
		 * setContentView(Button3, new LayoutParams(WC, WC));
		 */
		
RelativeLayout relativeLayout = new RelativeLayout(this);
	    
	    RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    RelativeLayout.LayoutParams param3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	     
	    // �}�[�W�����w��i���A��A�E�A���j
	    param.setMargins(5, 5, 15, 0);
	    param2.setMargins(5, 5, 15, 0);
	    param3.setMargins(5, 5, 15, 0);
	    param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
	    param.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 2);
	    param2.addRule(RelativeLayout.CENTER_HORIZONTAL, 2);
	    param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 2);
	    relativeLayout.addView(Button, param);
	    relativeLayout.addView(Button2, param2);
	    relativeLayout.addView(Button3, param3);
	    
	    addContentView(relativeLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	// ���j���[�쐬
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
*/
	// ���j���[�A�C�e���I���C�x���g
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu1:
			if (a2 <= 1) {
				AlertDialog.Builder ad = new AlertDialog.Builder(this);
				ad.setTitle("�G���[");
				ad.setMessage("�p�Y�������鐔��I�����Ă��������B");
				ad.setPositiveButton("OK", null);
				ad.show();
			} else if (a2 >= 2) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);

				alertDialogBuilder.setMessage("����Ńp�Y�������Ă������ł����H");
				alertDialogBuilder.setNegativeButton("�͂�",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								try {
									imageview.saveToFile(a2, s, g);
									hozon = 1;
									/*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
											PuzzleActivity.this);

									alertDialogBuilder
											.setMessage("�p�[�c��h��Ԃ��܂����H");
									alertDialogBuilder
											.setPositiveButton(
													"�͂�",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int which) {
															try {
																AlertDialog.Builder ad = new AlertDialog.Builder(
																		PuzzleActivity.this);
																ad.setMessage("�h��Ԃ������p�[�c���^�b�v���Ă��������B������x�^�b�v����Ɩ߂�܂��B");
																ad.setPositiveButton(
																		"OK",
																		null);
																ad.show();
															} catch (Exception e) {

															}
														}
													});
									alertDialogBuilder
											.setNeutralButton(
													"������",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int which) {

														}
													});
									alertDialogBuilder.setCancelable(false);
									AlertDialog alertDialog = alertDialogBuilder
											.create();
									alertDialog.show();*/
								} catch (Exception e) {
									Toast.makeText(PuzzleActivity.this, "�ۑ��o�����B", Toast.LENGTH_SHORT).show();
								}
							}
						});
				alertDialogBuilder.setPositiveButton("������",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				alertDialogBuilder.setCancelable(false);
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}

			break;

		case R.id.menu2:
			if (hozon == 0) {
				AlertDialog.Builder ad = new AlertDialog.Builder(this);
				ad.setTitle("�G���[");
				ad.setMessage("�p�Y���������������Ă��������B");
				ad.setPositiveButton("OK", null);
				ad.show();
			} else {
				/*
				 * Intent intent = new Intent(ImageEx.this, ImageEx.class); try
				 * { startActivity(intent);// } catch (Exception e) {
				 * 
				 * }
				 */

				AlertDialog.Builder ad = new AlertDialog.Builder(this);
				ad.setTitle("�G���[");
				ad.setMessage("�߂����@�\��");
				ad.setPositiveButton("OK", null);
				ad.show();

				a = 99;
			}

			break;

		case R.id.menu3:
			a2 = 2;
			s = 0;
			g = 2;
			PuzzleView.paint.setColor(Color.RED);
			// intent = new Intent(ImageEx.this, ImageEx.class);
			try {
				PuzzleView.paint.setColor(Color.RED);
				// startActivity(intent);//
			} catch (Exception e) {

			}
			break;

		case R.id.menu4:
			a2 = 3;
			s = 2;
			g = 5;

			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */

			break;

		case R.id.menu5:
			a2 = 4;
			s = 5;
			g = 9;

			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */
			break;

		case R.id.menu6:
			a2 = 5;
			s = 9;
			g = 14;
			

			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */
			break;

		case R.id.menu7:
			a2 = 6;
			s = 14;
			g = 20;
			

			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */
			break;

		case R.id.menu8:
			a2 = 7;
			s = 20;
			g = 27;
			

			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */
			break;

		case R.id.menu9:
			a2 = 8;
			s = 27;
			g = 35;

			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */
			break;

		case R.id.menu10:
			a2 = 9;
			s = 35;
			g = 44;

			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */
			break;

		case R.id.menu11:
			a2 = 10;
			s = 44;
			g = 54;


			/*
			 * intent = new Intent(ImageEx.this, ImageEx.class); try {
			 * startActivity(intent);// } catch (Exception e) {
			 * 
			 * }
			 */
			break;
		}
		return true;
		// return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == Button2){
			if (a2 <= 1) {
				AlertDialog.Builder ad = new AlertDialog.Builder(this);
				ad.setTitle("�G���[");
				ad.setMessage("�p�Y�������鐔��I�����Ă��������B");
				ad.setPositiveButton("OK", null);
				ad.show();
			} else if (a2 >= 2) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

				alertDialogBuilder.setMessage("����Ńp�Y�������Ă������ł����H");
				alertDialogBuilder.setNegativeButton("�͂�",
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
											.setMessage("�p�[�c��h��Ԃ��܂����H");
									alertDialogBuilder
											.setNegativeButton(	"�͂�",new DialogInterface.OnClickListener() {
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
									alertDialogBuilder.setPositiveButton("������",
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
									Toast.makeText(PuzzleActivity.this, "�ۑ��o�����B", Toast.LENGTH_SHORT).show();
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
				// �_�C�A���O�\���ȂǓ���̏������s�������ꍇ�͂����ɋL�q
				// �e�N���X��dispatchKeyEvent()���Ăяo������true��Ԃ�
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}