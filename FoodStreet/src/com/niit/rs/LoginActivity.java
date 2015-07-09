package com.niit.rs;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.niit.bean.User;
import com.niit.data.StaticData;

public class LoginActivity extends Activity {
	//�����ؼ�����
	private EditText etLoginName;
	private EditText etLoginPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//��ȡ�ؼ�����
		etLoginName = (EditText) findViewById(R.id.loginName);
		etLoginPass = (EditText) findViewById(R.id.loginPass);
		
		
	}

	public void login(View v){
		//��ȡ�ؼ�ֵ
		String loginName = etLoginName.getText().toString();
		final String loginPass = etLoginPass.getText().toString();
		//��ѯ���ݿ�
		BmobQuery<User> query = new BmobQuery<User>();
		query.addWhereEqualTo("userName",loginName);
		
		
		query.findObjects(this,new FindListener<User>(){
			@Override
			public void onSuccess(List<User> users){
				if(users.size()==0||users==null){
					Toast.makeText(LoginActivity.this,"��¼ʧ��",0).show();
					return;
				}else{
					User u = users.get(0);
					if(u.getUserPass().equals(loginPass)){
						//Toast.makeText(LoginActivity.this,"��¼�ɹ�������������",0).show();
						
						//��¼��ǰ��¼�û�
						StaticData.loginUser = users.get(0);
						//��ת��ҳ��
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
					}else{
						Toast.makeText(LoginActivity.this,"��¼ʧ��",0).show();
						return;
					}
				}
			}
			
		
			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(LoginActivity.this,"��¼ʧ��",0).show();
			}
		});
	
	}
		
	
	public void toReg(View v){
		Intent intent = new Intent(this,RegActivity.class);
		startActivity(intent);
	}
	
	public void goBack(View v){
		this.finish();
	}
}
