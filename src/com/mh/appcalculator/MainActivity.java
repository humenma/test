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
	
	public TextView mainTextView;
	public Spinner spinner1;
	public Button btn1, btn2, btn3;
	public EditText editText;
	public String msg;
	public int buttCode = 0;
	public String textView;
	public String msgSaved;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
		spinner1 = (Spinner) findViewById(R.id.spinner1);	
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		mainTextView = (TextView)findViewById(R.id.mlTextView);
		editText = (EditText) findViewById(R.id.editText1);
		
		
		if(getIntent() != null && getIntent().getExtras() != null){
			buttCode = getIntent().getExtras().getInt("BUTT_CODE");
			textView = getIntent().getExtras().getString("TEXT_VIEW");
		}
		
		OnClickListener listener = new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.equals(btn1)){
					setReqCode(1);
				}
				else if(v.equals(btn2)){
					setReqCode(2);
				}
				else if(v.equals(btn3)){
					setReqCode(3);
				}
			}
		};
		
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		
		if(buttCode == 1){
			mainTextView.setText(textView);
		} else if(buttCode == 2){
			mainTextView.setText(textView);
		} else if(buttCode == 3){
			mainTextView.setText(textView);
		}
		
	}
	
	public void setReqCode(int code){
		Intent intent = new Intent(MainActivity.this, NewActivity.class);
		intent.putExtra("REQ_CODE", code);
		intent.putExtra("EDIT_TEXT", editText.getText().toString());
		intent.putExtra("T_VIEW", mainTextView.getText().toString());
		intent.putExtra("SPINNER", spinner1.getSelectedItem().toString());
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
