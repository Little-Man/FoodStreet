package com.niit.rs;

import com.niit.bean.User;
import com.niit.data.StaticData;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class FragmentC extends Fragment{

	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
		
		if(StaticData.loginUser==null){
			Intent intent = new Intent(getActivity(),LoginActivity.class);
			getActivity().startActivity(intent);
			getActivity().finish();
			return null;
			
		}
		
		View view = inflater.inflate(R.layout.fragmentc, container, false);
		
		TextView tvName = (TextView)view.findViewById(R.id.userRealName);
		TextView tvTel = (TextView)view.findViewById(R.id.userTel);
		TextView tvAddr = (TextView)view.findViewById(R.id.userAddr);
		TextView tvEmail = (TextView)view.findViewById(R.id.userEmail);
		TextView tvGender = (TextView)view.findViewById(R.id.userGender);
		TextView tvAge = (TextView)view.findViewById(R.id.userAge);
		
		final String genders[] = new String[]{"±£ÃÜ","ÄÐ","Å®"};
		
		tvName.setText(StaticData.loginUser.getRealName());
		tvTel.setText(StaticData.loginUser.getTel());
		tvAddr.setText(StaticData.loginUser.getAddr());
		tvEmail.setText(StaticData.loginUser.getEmail());
		
		int gender = StaticData.loginUser.getGender();
		tvGender.setText(genders[gender]);
		tvAge.setText(StaticData.loginUser.getAge()+"");
		
		return view ;
		
		
	}
}
  