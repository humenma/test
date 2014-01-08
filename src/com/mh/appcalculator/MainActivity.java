package com.mh.appcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	public static TextView tView;
	public static Spinner spinner1;
	public static Button btn1, btn2, btn3;
	public static int reqCode;
	public static EditText editText;
	public static String msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
		spinner1 = (Spinner) findViewById(R.id.spinner1);	
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		tView = (TextView)findViewById(R.id.mlTextView);
		editText = (EditText) findViewById(R.id.editText1);
		
		OnClickListener listener = new OnClickListener() {
			
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.equals(btn1)){
					Intent intent = new Intent(MainActivity.this, NewActivity.class);
					reqCode = 1;
					startActivity(intent);
				}
				else if(v.equals(btn2)){
					Intent intent = new Intent(MainActivity.this, NewActivity.class);
					reqCode = 2;
					startActivity(intent);
				}
				else if(v.equals(btn3)){
					reqCode = 3;
					Intent intent = new Intent(MainActivity.this, NewActivity.class);
					startActivity(intent);
				}
			}
		};
		
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		
		if(NewActivity.buttCode == 1){
			
			//skuska
			if(tView != null || !tView.getText().equals("")){
				msg = MainActivity.tView.getText().toString();
				MainActivity.tView.append(msg + "\n" + NewActivity.textView.getText().toString());
			} else {
				msg = NewActivity.textView.getText().toString();
				MainActivity.tView.setText(msg);
			}
			
		} else if(NewActivity.buttCode == 2){
			msg = NewActivity.textView.getText().toString();
			MainActivity.tView.setText(msg);
		} else if(NewActivity.buttCode == 3){
			msg = "cancel";
			MainActivity.tView.setText(msg);
		}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
