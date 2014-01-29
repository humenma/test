package com.mh.appcalculator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends ActionBarActivity {
	
	public String message;
	private int seekR, seekG, seekB;
	SeekBar redSeekBar, greenSeekBar, blueSeekBar;
	LinearLayout mScreen;
	public Button buttSubmit;
	public ImageButton buttPrev, buttPlay, buttNext, buttPause;
	public TextView newTextView;
	public String fromMainTextView, toMainTextView;
	MediaPlayer mediaPlayer;
	Uri tracks[] = new Uri[4];
	int currentTrack = 0;
	
	
	@SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
  
        //tracks[0] = R.raw.qflash;
        
        tracks[0] = setSong("qflash");
        tracks[1] = setSong("dio");
        tracks[2] = setSong("graveyard");
        tracks[3] = setSong("vaso");
        mediaPlayer = MediaPlayer.create(NewActivity.this, tracks[currentTrack]);
        
        
        if(getIntent() != null && getIntent().getExtras() != null){
        	fromMainTextView = getIntent().getExtras().getString("T_VIEW");
        	}
        
        int reqCode = getIntent().getExtras().getInt("REQ_CODE");
    	String editText = getIntent().getExtras().getString("EDIT_TEXT");
    	String spinner1 = getIntent().getExtras().getString("SPINNER");

        
        if(reqCode == 1){
        	setContentView(R.layout.activity_new);
        	message = String.valueOf(spinner1);
        	newTextView = (TextView)findViewById(R.id.textViewNew);
            newTextView.setTextSize(40);
            newTextView.setText(message);
        } else if(reqCode == 2){
        	setContentView(R.layout.activity_new);
        	message = String.valueOf(editText);
    	    newTextView = (TextView)findViewById(R.id.textViewNew);
    	    newTextView.setTextSize(40);
    	    newTextView.setText(message);
        } else if(reqCode == 3){
        	setContentView(R.layout.activity_seekbarcolor);
    		mScreen = (LinearLayout) findViewById(R.id.seek_col_changer);
    		redSeekBar = (SeekBar) findViewById(R.id.red_seek);
    		greenSeekBar = (SeekBar) findViewById(R.id.green_seek);
    		blueSeekBar = (SeekBar) findViewById(R.id.blue_seek);
    		updateBackground();
    		
    		redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    		greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    		blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        } else if(reqCode == 4){
        	setContentView(R.layout.activity_player);
        }
        
        //MediaPlayer Start
        
        if(reqCode == 4){
        	
        	mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					nextSong();
					
				}
			});
        	
        	
        	mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        	try {
			//	mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/Music/Darnell.mp3");
			//	Log.i("MOJ_TAG", Environment.getExternalStorageDirectory().getPath() + "/Music/Darnell.mp3");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
        	
        	buttPrev = (ImageButton)findViewById(R.id.butt_prev);
        	buttPrev.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO PREVIEVOUS SONG
					if(currentTrack <= tracks.length){
						mediaPlayer.stop();
						mediaPlayer.release();
						if(--currentTrack < 0){
							currentTrack = tracks.length;
						}
						mediaPlayer = MediaPlayer.create(NewActivity.this, tracks[currentTrack]);
						mediaPlayer.start();
						
						Toast.makeText(getApplicationContext(), "Previous song.", 
							      Toast.LENGTH_SHORT).show();
					}
				}
        		
        	});
        	
        	buttPlay = (ImageButton)findViewById(R.id.butt_play);
        	buttPlay.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO PLAY SONG
					Toast.makeText(getApplicationContext(), "Playing sound.", 
							   Toast.LENGTH_SHORT).show();
					mediaPlayer.start();
					
					/*
					 * mediaPlayer.setOnPreparedListener(new OnPreparedListener(){ 
						public void onPrepared(MediaPlayer player){
						player.start();
							}
						});
						mediaPlayer.prepareAsync();
					 * 
					 */
				}
        	});
        	
        	buttNext = (ImageButton)findViewById(R.id.butt_forward);
        	buttNext.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO NEXT SONG
					nextSong();
				}
        	});
        	
        	//button pause
        	buttPause = (ImageButton)findViewById(R.id.butt_pause);
        	buttPause.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO PAUSE SONG
					Toast.makeText(getApplicationContext(), "Pausing sound.", 
						      Toast.LENGTH_SHORT).show();
						      mediaPlayer.pause();
				}
        		
        	});
        	
        	
        	
        }
 
        
        if(reqCode < 3){
        
		buttSubmit = (Button)findViewById(R.id.button_submit);
		buttSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(NewActivity.this);
				builder.setTitle("Sumbit text?");
				builder.setMessage("Are you sure you want to submit this text ?");
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// YES
						Intent intent = new Intent(NewActivity.this, MainActivity.class);
						intent.putExtra("BUTT_CODE", 1);
						toMainTextView = (fromMainTextView + "\n" + newTextView.getText());
						intent.putExtra("TEXT_VIEW", toMainTextView.toString());
						startActivity(intent);
					}
				});
				
				builder.setNegativeButton("Shuffle", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// NO
						Intent intent = new Intent(NewActivity.this, MainActivity.class);
						intent.putExtra("BUTT_CODE", 2);
						toMainTextView = (fromMainTextView + "\n" + shuffle(newTextView.getText().toString()));
						intent.putExtra("TEXT_VIEW", toMainTextView.toString());
						startActivity(intent);
					}
				});
				
				builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// CANCEL
						Intent intent = new Intent(NewActivity.this, MainActivity.class);
						intent.putExtra("BUTT_CODE", 3);
						toMainTextView = (fromMainTextView);
						intent.putExtra("TEXT_VIEW", toMainTextView.toString());
						startActivity(intent);
					}
				});
				builder.show();
			}
		});
        
		
		
        
        }
		
        
    }
	

	@Override
	public void onDestroy() {
	super.onDestroy();
	mediaPlayer.release();
	mediaPlayer = null;
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
	
	private void nextSong(){
		if(currentTrack >= 0 && currentTrack < tracks.length){
			mediaPlayer.stop();
			mediaPlayer.release();
			if(++currentTrack == tracks.length){
				currentTrack = 0;
			}
			mediaPlayer = MediaPlayer.create(NewActivity.this, tracks[currentTrack]);
			mediaPlayer.start();
			
			Toast.makeText(getApplicationContext(), "Next song.", 
				      Toast.LENGTH_SHORT).show();
		} 
	}
	
	private Uri setSong(String song){
		return Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/MPmusic/" + song + ".mp3"));
	}
	
	private String shuffle(String original){
		List<Character> textList = new ArrayList<Character>();
		for (char character : original.toCharArray()){
			textList.add(character);
		}
		Collections.shuffle(textList);
		StringBuilder shuffledStringBuilder = new StringBuilder(textList.size());
			for (Character characterObject : textList){
				shuffledStringBuilder.append(characterObject);
				}

		return shuffledStringBuilder.toString();
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
