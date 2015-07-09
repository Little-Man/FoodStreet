package com.niit.rs;

import cn.bmob.v3.Bmob;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		Bmob.initialize(this, "0638671eb2ff02967fb22f8796854474");
		handler.sendEmptyMessageDelayed(0, 3000);
		
	}
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg){
			Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
			startActivity(intent);
		}
	};	
}
