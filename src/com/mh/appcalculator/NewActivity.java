package com.mh.appcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class NewActivity extends ActionBarActivity {
	String message = String.valueOf(MainActivity.spinner1.getSelectedItem());
	private int seekR, seekG, seekB;
	SeekBar redSeekBar, greenSeekBar, blueSeekBar;
	LinearLayout mScreen;
	public static Button butt1, butt2, butt3;
	static TextView textView;
	public static int buttCode = 0;
	
	@SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        
        if(MainActivity.reqCode == 1){
        	setContentView(R.layout.activity_new);
        	textView = (TextView)findViewById(R.id.textViewNew);
            textView.setTextSize(40);
            textView.setText(message);
        } else if(MainActivity.reqCode == 2){
        	setContentView(R.layout.activity_new);
        	Intent intent = getIntent();
    	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
    	    textView = (TextView)findViewById(R.id.textViewNew);
    	    textView.setTextSize(40);
    	    textView.setText(message);
        } else if(MainActivity.reqCode == 3){
        	setContentView(R.layout.activity_seekbarcolor);
    		
    		mScreen = (LinearLayout) findViewById(R.id.seek_col_changer);
    		redSeekBar = (SeekBar) findViewById(R.id.red_seek);
    		greenSeekBar = (SeekBar) findViewById(R.id.green_seek);
    		blueSeekBar = (SeekBar) findViewById(R.id.blue_seek);
    		updateBackground();
    		
    		redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    		greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    		blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        }
        
        butt1 = (Button)findViewById(R.id.button_yes);
		butt2 = (Button)findViewById(R.id.button_no);
		butt3 = (Button)findViewById(R.id.button_cancel);
        
        OnClickListener buttListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.equals(butt1)){
					//yes
					buttCode = 1;
					Intent intent = new Intent(NewActivity.this, MainActivity.class);
					startActivity(intent);
					
				} else if(v.equals(butt2)){
					//no
					buttCode = 2;
					Intent intent = new Intent(NewActivity.this, MainActivity.class);
					startActivity(intent);
				}else if(v.equals(butt3)){
					//cancel
					buttCode = 3;
					Intent intent = new Intent(NewActivity.this, MainActivity.class);
					startActivity(intent);
				}
			}
        	
        };

			butt1.setOnClickListener(buttListener);
			butt2.setOnClickListener(buttListener);
			butt3.setOnClickListener(buttListener);
			
		
        
    }

private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			updateBackground();
		}
	};
	
	private void updateBackground() {
		seekR = redSeekBar.getProgress();
		seekG = greenSeekBar.getProgress();
		seekB = blueSeekBar.getProgress();
		
		mScreen.setBackgroundColor(0xff000000 + seekR * 0x10000 + seekG * 0x100 + seekB);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
