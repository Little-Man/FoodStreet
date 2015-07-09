package com.niit.rs;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.niit.bean.User;

public class RegActivity extends Activity {
	
	private User u = new User();
	
	private EditText etRegName;
	private EditText etRegPass;
	private EditText etRegPass1;
	private EditText etRegEmail;
	private EditText etRegAddr;
	private EditText etRegTel;
	private EditText etRegAge;
	private EditText etRegRealName;
	private TextView tvGender;

	private int gender = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		
		
		
		etRegName=(EditText)findViewById(R.id.regName);
		etRegPass=(EditText)findViewById(R.id.regPass);
		etRegPass1=(EditText)findViewById(R.id.regPass1);
		etRegEmail=(EditText)findViewById(R.id.regEmail);
		etRegAddr=(EditText)findViewById(R.id.regAddr);
		etRegTel=(EditText)findViewById(R.id.regTel);
		etRegAge=(EditText)findViewById(R.id.regAge);
		etRegRealName=(EditText)findViewById(R.id.regRealName);
		tvGender = (TextView)findViewById(R.id.gender);
	}
	
	public void reg(View v){
		String userName = etRegName.getText().toString();
		if(TextUtils.isEmpty(userName)){
			Toast.makeText(this, "�û�������Ϊ��", 0);
			return;
		}
		if(userName.length()<6 || userName.length()>16){
			Toast.makeText(this, "�û�������Ӧ����6��16֮��", 0);
			return;
		}
		
		BmobQuery<User> query = new BmobQuery<User>();
		
		query.addWhereEqualTo("userName",userName);
		
		query.findObjects(this, new FindListener<User>() {
			
			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(RegActivity.this, "�û�������", 0);
			}

			
			@Override
			public void onSuccess(List<User> users) {
				if(users.size()>0){
					Toast.makeText(RegActivity.this, "�û����Ѵ���", 0);
					return;
				}
			}
		});
		
		String userPass = etRegPass.getText().toString();
		if(TextUtils.isEmpty(userPass)){
			Toast.makeText(this, "���벻��Ϊ��", 0);
			return;
		}
		String userPass1 = etRegPass1.getText().toString();
		if(!userPass1.equals(userPass)){
			Toast.makeText(this, "������������벻һ��", 0);
			return;
		}
		String email = etRegEmail.getText().toString();
		if(TextUtils.isEmpty(email)){
			Toast.makeText(this, "���䲻��Ϊ��", 0);
			return;
		}
		if(email.indexOf("@")==-1){
			Toast.makeText(this, "�����ʽ����ȷ", 0);
			return;
		}
		String addr = etRegAddr.getText().toString();
		if(TextUtils.isEmpty(addr)){
			Toast.makeText(this, "��ַ����Ϊ��", 0);
			return;
		}
		
		String strAge = etRegAge.getText().toString();
		
		if(TextUtils.isEmpty(strAge)){
			Toast.makeText(this, "����������", 0);
			return;
		}
		
		int age = Integer.parseInt(strAge);
		if(age<0 || age>150){
			Toast.makeText(this, "���䷶Χ����ȷ", 0);
			return;
		}
		
		String realName = etRegRealName.getText().toString();
		if(TextUtils.isEmpty(realName)){
			Toast.makeText(this, "������������Ϊ��", 0);
			return;
		}
		
		String tel = etRegTel.getText().toString();
		if(TextUtils.isEmpty(tel)){
			Toast.makeText(this, "�绰����Ϊ��", 0);
			return;
		}
		
		u.setUserName(userName);
		u.setUserPass(userPass);
		u.setAddr(addr);
		u.setTel(tel);
		u.setEmail(email);
		u.setRealName(realName);
		u.setAge(age);
		u.setGender(gender);
		//��ʼ����
		u.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				
				Intent intent = new Intent(RegActivity.this,MainActivity.class);
				startActivity(intent);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(RegActivity.this, "ע��ʧ��", 1).show();
			}
		});
	}
	
	private String genders[] = new String[]{"����","��","Ů"};
	
	public void choseGender(View v){
		new AlertDialog.Builder(this).setTitle("��ѡ���Ա�").setSingleChoiceItems(genders, 0, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int index) {
					gender = index;
			}
		}).setPositiveButton("ȷ��", new OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						tvGender.setText(genders[gender]);
					}
				}).create().show();
	}
}

