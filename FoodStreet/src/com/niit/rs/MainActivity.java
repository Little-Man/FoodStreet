package com.niit.rs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.niit.data.StaticData;

public class MainActivity extends Activity {

	private TextView tvMenu1;
	private TextView tvMenu2;
	private TextView tvMenu3;
	
	private TextView tvMenu_1;
	private TextView tvMenu_2;
	private TextView tvMenu_3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvMenu1=(TextView)findViewById(R.id.tvMenu1);
		tvMenu2=(TextView)findViewById(R.id.tvMenu2);
		tvMenu3=(TextView)findViewById(R.id.tvMenu3);
		tvMenu_1=(TextView)findViewById(R.id.tvMenu_1);
		tvMenu_2=(TextView)findViewById(R.id.tvMenu_2);
		tvMenu_3=(TextView)findViewById(R.id.tvMenu_3);
		
		
		tvMenu1.setTextColor(Color.GREEN);
		tvMenu_1.setBackgroundColor(Color.GREEN);
		tvMenu2.setTextColor(Color.GRAY);
		tvMenu_2.setBackgroundColor(Color.GRAY);
		tvMenu3.setTextColor(Color.GRAY);
		tvMenu_3.setBackgroundColor(Color.GRAY);
		Fragment fragmenta = new FragmentA();
		FragmentTransaction transactiona = getFragmentManager().beginTransaction();
		transactiona.replace(R.id.frags, fragmenta);
		transactiona.addToBackStack(null);
		transactiona.commit();
		
	}
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case R.id.tvMenu1:
				tvMenu1.setTextColor(Color.GREEN);
				tvMenu_1.setBackgroundColor(Color.GREEN);
				
				tvMenu2.setTextColor(Color.GRAY);
        		tvMenu_2.setBackgroundColor(Color.GRAY);
        		
        		tvMenu3.setTextColor(Color.GRAY);
        		tvMenu_3.setBackgroundColor(Color.GRAY);
        		
        		FragmentA fragmenta = new FragmentA();
        		FragmentTransaction transactiona = getFragmentManager().beginTransaction();
        		transactiona.replace(R.id.frags, fragmenta);
        		transactiona.addToBackStack(null);
        		transactiona.commit();
        		
        		break;
        		
			case R.id.tvMenu2:
				tvMenu1.setTextColor(Color.GRAY);
				tvMenu_1.setBackgroundColor(Color.GRAY);
				
				tvMenu2.setTextColor(Color.GREEN);
        		tvMenu_2.setBackgroundColor(Color.GREEN);
        		
        		tvMenu3.setTextColor(Color.GRAY);
        		tvMenu_3.setBackgroundColor(Color.GRAY);
        		
        		FragmentB fragmentb = new FragmentB();
        		FragmentTransaction transactionb = getFragmentManager().beginTransaction();
        		transactionb.replace(R.id.frags, fragmentb);
        		transactionb.addToBackStack(null);
        		transactionb.commit();
        		
        		break;
        		
			case R.id.tvMenu3:
				tvMenu1.setTextColor(Color.GRAY);
				tvMenu_1.setBackgroundColor(Color.GRAY);
				
				tvMenu2.setTextColor(Color.GRAY);
        		tvMenu_2.setBackgroundColor(Color.GRAY);
        		
        		tvMenu3.setTextColor(Color.GREEN);
        		tvMenu_3.setBackgroundColor(Color.GREEN);
        		
        		FragmentC fragmentc = new FragmentC();
        		FragmentTransaction transactionc = getFragmentManager().beginTransaction();
        		transactionc.replace(R.id.frags, fragmentc);
        		transactionc.addToBackStack(null);
        		transactionc.commit();
        		
        		break;
			}
		}
	};
	
	
	public void choseFragment(View v){
		
		Message msg = new Message();
		msg.what = v.getId();
		handler.sendMessage(msg);
	}
	
	
	public void clickText(View v){
		int id = v.getId();
		String categaryId = "";
		switch(id){
		case R.id.C1:
			categaryId="ihVT000F";
			break;
		case R.id.C2:
			categaryId="ripVNNNz";
			break;
		case R.id.C3:
			categaryId="ezUDBBBk";
			break;
		case R.id.C4:
			categaryId="SP8rdddz";
			break;
		case R.id.C5:
			categaryId="4yI7222b";
			break;
		case R.id.C6:
			categaryId="O68BRRRb";
			break;
		case R.id.C7:
			categaryId="DCSa333C";
			break;
		case R.id.C8:
			categaryId="zFm9CCCH";
			break;
		}
		FragmentA fa = new FragmentA();
		
		fa.setCategoryId(categaryId);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.frags, fa);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void exit(View v){
		StaticData.loginUser = null;
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	public void toShoppingCart(View v){
		Intent intent = new Intent(this,ShopCartActivity.class);
		startActivity(intent);
	}
	
	public void toOrderForm(View v){
		Intent intent = new Intent(this,OrderListActivity.class);
		startActivity(intent);
	}
	
	public void xiugai(View v){
		//修改个人信息
		Intent intent = new Intent(this, EdutActivity.class);
		intent.putExtra("userName", StaticData.loginUser.getUserName());
		intent.putExtra("userPass", StaticData.loginUser.getUserPass());
		intent.putExtra("tel", StaticData.loginUser.getTel());
		intent.putExtra("addr", StaticData.loginUser.getAddr());
		intent.putExtra("objectId", StaticData.loginUser.getObjectId());
		
		startActivity(intent);		
	}
}
