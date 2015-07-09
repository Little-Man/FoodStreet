package com.niit.bean;

import cn.bmob.v3.BmobObject;

public class User extends BmobObject {
	private String userName;//��¼�û���
	private String userPass;//��¼����
	private String tel;//�绰
	private String email;//�ʼ�
	private String addr;//��ַ
	private int age;//����
	private String realName;//�û���ʵ����
	private int gender;//�Ա�
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPass=" + userPass
				+ ", tel=" + tel + ", email=" + email + ", addr=" + addr
				+ ", age=" + age + ", realName=" + realName + ", gender="
				+ gender + "]";
	}
	public User(String userName, String userPass, String tel, String email,
			String addr, int age, String realName, int gender) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.tel = tel;
		this.email = email;
		this.addr = addr;
		this.age = age;
		this.realName = realName;
		this.gender = gender;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String tableName) {
		super(tableName);
		// TODO Auto-generated constructor stub
	}
	
}