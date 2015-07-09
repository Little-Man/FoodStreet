package com.niit.rs;

import com.niit.bean.User;

import cn.bmob.v3.listener.UpdateListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EdutActivity extends Activity {

	private TextView tvUserNewName;
	private EditText tvUserNewPass;
	private EditText tvUserNewTel;
	private EditText tvuserNewAddr;

	String userNewName;
	String userNewPass;
	String userNewTel;
	String userNewAddr;
	String objectid;

	User u = new User();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edut);

		tvUserNewName = (TextView) findViewById(R.id.userNewName);
		tvUserNewPass = (EditText) findViewById(R.id.userNewPass);
		tvUserNewTel = (EditText) findViewById(R.id.userNewTel);
		tvuserNewAddr = (EditText) findViewById(R.id.userNewAddr);

		Intent intent = getIntent();

		userNewName = intent.getStringExtra("userName");
		userNewPass = intent.getStringExtra("userPass");
		userNewTel = intent.getStringExtra("tel");
		userNewAddr = intent.getStringExtra("addr");
		objectid = intent.getStringExtra("objectId");

		tvUserNewName.setText(userNewName);
		tvUserNewPass.setText(userNewPass);
		tvUserNewTel.setText(userNewTel);
		tvuserNewAddr.setText(userNewAddr);
	}

	public void Edit(View v) {

		String editpsd = tvUserNewPass.getText().toString();
		String edittel = tvUserNewTel.getText().toString();
		String editaddr = tvuserNewAddr.getText().toString();

		u.setUserPass(editpsd);
		u.setTel(edittel);
		u.setAddr(editaddr);

		u.update(this, objectid, new UpdateListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				t("修改成功");
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				t("修改失败");
				System.out.println(arg1);
			}
		});
	}

	public void t(String msg) {
		Toast.makeText(this, msg, 0).show();
	}
}
