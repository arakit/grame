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

	private Button penButton;
	private Button eraButton;
	private Button staButton;
	private Button savButton;
	private Button delButton;
	private Button undButton;
	private Button redButton;

	GridView stampGrid;
	
	LinearLayout pensizeView;
	LinearLayout erasizeView;

	SeekBar seekBar_e;
	SeekBar seekBar_p;
    int oldValue;

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

		stampGrid = (GridView) this.findViewById(R.id.stampGrid);
		stampGrid.setAdapter(new ImageAdapter(this));
		stampGrid.setOnItemClickListener(this);
		
		erasizeView = (LinearLayout) this.findViewById(R.id.EraSizeLinear);
		pensizeView = (LinearLayout) this.findViewById(R.id.PenSizeLinear);

		penview = (penView) this.findViewById(R.id.penView1);

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
	                       
	                   }
	 
	                   public void onStartTrackingTouch(SeekBar seekBar) {
	                       
	                   }
	 
	                   public void onStopTrackingTouch(SeekBar seekBar) {
	                	   penview.erasorSize_action(seekBar.getProgress());
	                   }
	               }
	       );
		
		seekBar_p.setOnSeekBarChangeListener(
	               new OnSeekBarChangeListener() {
	                   public void onProgressChanged(SeekBar seekBar,
	                           int progress, boolean fromUser) {
	                      
	                   }
	 
	                   public void onStartTrackingTouch(SeekBar seekBar) {
	                       
	                   }
	 
	                   public void onStopTrackingTouch(SeekBar seekBar) {
	                	   penview.penSize_action(seekBar.getProgress());
	                   }
	               }
	       );
		
		mColorPickerDialog = new ColorPickerDialog(this,
			new ColorPickerDialog.OnColorChangedListener() {
				public void colorChanged(int color){
					selectColor = color;
					penview.colorSelect(selectColor);
					pensizeView.setVisibility(View.VISIBLE);
				}
			},
			Color.BLACK);
	}

	@Override
	public void onClick(View view) {
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
		penview.stamp_action(id);		
		stampGrid.setVisibility(View.GONE);
	}

	
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