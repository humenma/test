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
	
	public TextView tView;
	public Spinner spinner1;
	public Button btn1, btn2, btn3;
	public EditText editText;
	public String msg;
	public int buttCode = getIntent().getExtras().getInt("BUTT_CODE");
	public String textView = getIntent().getExtras().getString("TEXT_VIEW");
	
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
					intent.putExtra("REQ_CODE", 1);
					intent.putExtra("EDIT_TEXT", editText.getText());
					intent.putExtra("SPINNER", spinner1.getSelectedItem().toString());
					startActivity(intent);
				}
				else if(v.equals(btn2)){
					Intent intent = new Intent(MainActivity.this, NewActivity.class);
					intent.putExtra("REQ_CODE", 2);
					intent.putExtra("EDIT_TEXT", editText.getText());
					intent.putExtra("SPINNER", spinner1.getSelectedItem().toString());
					startActivity(intent);
				}
				else if(v.equals(btn3)){
					Intent intent = new Intent(MainActivity.this, NewActivity.class);
					intent.putExtra("REQ_CODE", 3);
					intent.putExtra("EDIT_TEXT", editText.getText());
					intent.putExtra("SPINNER", spinner1.getSelectedItem().toString());
					startActivity(intent);
				}
			}
		};
		
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		
		if(buttCode == 1){
			
			//skuska
			if(tView != null || !tView.getText().equals("")){
				msg = tView.getText().toString();
				tView.append(textView);
			} else {
				msg = textView;
				tView.setText(msg);
			}
			
		} else if(buttCode == 2){
			msg = textView;
			tView.setText(msg);
		} else if(buttCode == 3){
			msg = "cancel";
			tView.setText(msg);
		}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
