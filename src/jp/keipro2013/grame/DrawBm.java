package jp.keipro2013.grame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;


public class DrawBm extends Activity implements OnClickListener, OnItemClickListener {
	Context mcontext = this;
	
	penView penview;
	int b=0;
	
	LinearLayout liLayout;
	Button button;
	
	//繝懊ち繝ｳ
	private Button penButton;
	private Button eraButton;
	private Button staButton;
	private Button savButton;
	private Button delButton;
	private Button undButton;
	private Button redButton;
	
	//繧ｹ繧ｿ繝ｳ繝励げ繝ｪ繝�ラ
	GridView stampGrid;
	
	LinearLayout pensizeView;
	LinearLayout erasizeView;
	
	//繧ｷ繝ｼ繧ｯ繝舌�
	SeekBar seekBar_e;
	SeekBar seekBar_p;
    int oldValue;
    
    //繧ｫ繝ｩ繝ｼ繝斐ャ繧ｫ繝ｼ繝�う繧｢繝ｭ繧ｰ
    private int selectColor;
    private ColorPickerDialog mColorPickerDialog;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.drawbm);

		new AlertDialog.Builder(this)
    	.setCancelable(false)    	
    	.setMessage("メッセージ作成するためにラクガキを行ってください。")    	
    	.setPositiveButton("はい",null)    	
    	.show();
		
		//繝懊ち繝ｳ縺ｮ險ｭ螳�
		penButton = (Button) this.findViewById(R.id.pen_button);
		eraButton = (Button) this.findViewById(R.id.eraser_button);
		staButton = (Button) this.findViewById(R.id.stamp_button);
		savButton = (Button) this.findViewById(R.id.save_button);
		delButton = (Button) this.findViewById(R.id.delete_button);
		undButton = (Button) this.findViewById(R.id.undo_button);
		redButton = (Button) this.findViewById(R.id.redo_button);

		penButton.setOnClickListener(this);
		eraButton.setOnClickListener(this);
		staButton.setOnClickListener(this);
		savButton.setOnClickListener(this);
		delButton.setOnClickListener(this);
		undButton.setOnClickListener(this);
		redButton.setOnClickListener(this);
		
		//繧ｹ繧ｿ繝ｳ繝励げ繝ｪ繝�ラ
		stampGrid = (GridView) this.findViewById(R.id.stampGrid);
		stampGrid.setAdapter(new ImageAdapter(this));
		stampGrid.setOnItemClickListener(this);
		
		erasizeView = (LinearLayout) this.findViewById(R.id.EraSizeLinear);
		pensizeView = (LinearLayout) this.findViewById(R.id.PenSizeLinear);
		
		//繝壹Φ繝薙Η繝ｼ
		penview = (penView) this.findViewById(R.id.penView1);
		
		//繧ｷ繝ｼ繧ｯ繝舌�
		seekBar_e = (SeekBar)this.findViewById(R.id.seekBar1);
		seekBar_p = (SeekBar)this.findViewById(R.id.seekBar2); 
		
		seekBar_e.setMax(50);
		seekBar_e.setProgress(12);
		
		seekBar_p.setMax(40);
		seekBar_p.setProgress(6);
		
		seekBar_e.setOnSeekBarChangeListener(
	               new OnSeekBarChangeListener() {
	                   public void onProgressChanged(SeekBar seekBar,
	                           int progress, boolean fromUser) {
	                       // 繝��繝溘ｒ繝峨Λ繝�げ縺励◆縺ｨ縺阪↓蜻ｼ縺ｰ繧後ｋ
	                       //Toast.makeText(mcontext, "test", Toast.LENGTH_SHORT).show();
	                   }
	 
	                   public void onStartTrackingTouch(SeekBar seekBar) {
	                       // 繝��繝溘↓隗ｦ繧後◆縺ｨ縺阪↓蜻ｼ縺ｰ繧後ｋ
	                   }
	 
	                   public void onStopTrackingTouch(SeekBar seekBar) {
	                       // 繝��繝溘ｒ髮｢縺励◆縺ｨ縺阪↓蜻ｼ縺ｰ繧後ｋ
	                	   penview.erasorSize_action(seekBar.getProgress());
	                   }
	               }
	       );
		
		seekBar_p.setOnSeekBarChangeListener(
	               new OnSeekBarChangeListener() {
	                   public void onProgressChanged(SeekBar seekBar,
	                           int progress, boolean fromUser) {
	                       // 繝��繝溘ｒ繝峨Λ繝�げ縺励◆縺ｨ縺阪↓蜻ｼ縺ｰ繧後ｋ
	                       //Toast.makeText(mcontext, "test", Toast.LENGTH_SHORT).show();
	                   }
	 
	                   public void onStartTrackingTouch(SeekBar seekBar) {
	                       // 繝��繝溘↓隗ｦ繧後◆縺ｨ縺阪↓蜻ｼ縺ｰ繧後ｋ
	                   }
	 
	                   public void onStopTrackingTouch(SeekBar seekBar) {
	                       // 繝��繝溘ｒ髮｢縺励◆縺ｨ縺阪↓蜻ｼ縺ｰ繧後ｋ
	                	   penview.penSize_action(seekBar.getProgress());
	               			//Toast.makeText(mcontext, "test", Toast.LENGTH_SHORT).show();
	                   }
	               }
	       );
		
		//繧ｫ繝ｩ繝ｼ繝斐ャ繧ｫ繝ｼ
		mColorPickerDialog = new ColorPickerDialog(this,
			new ColorPickerDialog.OnColorChangedListener() {
				public void colorChanged(int color){
					selectColor = color;
					penview.colorSelect(selectColor);
					pensizeView.setVisibility(View.VISIBLE);
				}
			},
			Color.BLACK);
		
		/*new AlertDialog.Builder(this)
    	.setCancelable(false)    	
    	.setMessage("メッセージ作成するためにラクガキを行ってください。")    	
    	.setPositiveButton("はい",null)    	
    	.show();*/
		
	}
	/** 繝｡繝九Η繝ｼ縺ｮ逕滓�繧､繝吶Φ繝�*/
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean ret = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.draw_bm, menu);
		return ret;
	}

	/** 繝｡繝九Η繝ｼ縺後け繝ｪ繝�け縺輔ｌ縺滓凾縺ｮ繧､繝吶Φ繝�*/
	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.clean:
			penview.clearDrawList();
			break;
		case R.id.finish:
			if(b==0){
				AlertDialog.Builder ad=new AlertDialog.Builder(this);
    	        ad.setTitle("繧ｨ繝ｩ繝ｼ");
    	        ad.setMessage("繝ｩ繧ｯ繧ｬ繧ｭ繧剃ｿ晏ｭ倥＠縺ｦ縺上□縺輔＞縲�");
    	        ad.setPositiveButton("OK",null);
    	        ad.show();
    	        b++;
			}
			
			else{
				try {
    			startActivity(this, "net.npaka.imageex","net.npaka.imageex.ImageEx");
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				AlertDialog.Builder ad=new AlertDialog.Builder(this);
        	        ad.setTitle("aaa");
        	        ad.setMessage("aaa");
        	        ad.setPositiveButton("OK",null);  
        	        ad.show();
    			}
			}
			break;
		}
		return true;
	}
	
	
	
	/* private static void startActivity(Activity activity,
	            String packageName,String className) throws Exception {
	            Intent intent=new Intent(Intent.ACTION_MAIN);
	            intent.setComponent(new ComponentName(packageName,className));
	            intent.removeCategory(Intent.CATEGORY_DEFAULT);
	            intent.addCategory(Intent.CATEGORY_LAUNCHER);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            activity.startActivity(intent);
	        }*/
	
	@Override
	public void onClick(View view) {
		// TODO 閾ｪ蜍慕函謌舌＆繧後◆繝｡繧ｽ繝�ラ繝ｻ繧ｹ繧ｿ繝�
		if(view.getId() == R.id.pen_button){
			penview.pen_action();
			if(erasizeView.getVisibility() == View.VISIBLE){
				erasizeView.setVisibility(View.GONE);
			}
			if(pensizeView.getVisibility() == View.VISIBLE){
				pensizeView.setVisibility(View.GONE);
			} else {
				pensizeView.setVisibility(View.VISIBLE);
			}
			mColorPickerDialog.show();
		}
		if(view.getId() == R.id.eraser_button){
			penview.erasor_action();
			if(pensizeView.getVisibility() == View.VISIBLE){
				pensizeView.setVisibility(View.GONE);
			}
			if(erasizeView.getVisibility() == View.VISIBLE){
				erasizeView.setVisibility(View.GONE);
			} else {
				erasizeView.setVisibility(View.VISIBLE);
			}
		}
		if(view.getId() == R.id.stamp_button){
			stampGrid.setVisibility(View.VISIBLE);
			if(pensizeView.getVisibility() == View.VISIBLE){
				pensizeView.setVisibility(View.GONE);
			}
			if(erasizeView.getVisibility() == View.VISIBLE){
				erasizeView.setVisibility(View.GONE);
			}
		}
		if(view.getId() == R.id.save_button){
			penview.saveToFile();
			b++;
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setMessage("ラクガキを完了しパズル化を行いますか？");
			alertDialogBuilder.setNegativeButton("はい",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							Intent intent1 = new Intent(DrawBm.this, PuzzleActivity.class);
							try {
								startActivity(intent1);
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
		}
		if(view.getId() == R.id.delete_button){
			penview.clearDrawList();
		}
		if(view.getId() == R.id.undo_button){
			penview.undo_action();
		}
		if(view.getId() == R.id.redo_button){
			penview.redo_action();
		}
	}
	

	@Override
	public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
		// TODO 閾ｪ蜍慕函謌舌＆繧後◆繝｡繧ｽ繝�ラ繝ｻ繧ｹ繧ｿ繝�
		penview.stamp_action(id);		
		stampGrid.setVisibility(View.GONE);
	}
	
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(stampGrid.getVisibility() == View.VISIBLE){
				stampGrid.setVisibility(View.GONE);
				return true;
			}
		}else{
			
		}
		return super.onKeyDown(keyCode, event);
	}*/
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_BACK:
				if(stampGrid.getVisibility() == View.VISIBLE){
					stampGrid.setVisibility(View.GONE);
				}else{
					
				}
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}