package com.niit.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Shop extends BmobObject {
	private String shopName;//��������
	private String shopAddr;//���̵�ַ
	private String shopTel;//���̵绰
	private String shopDir;//��������
	private BmobFile shopPic;//�����̱�
	public BmobFile getShopPic() {
		return shopPic;
	}
	public void setShopPic(BmobFile shopPic) {
		this.shopPic = shopPic;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getShopTel() {
		return shopTel;
	}
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	public String getShopDir() {
		return shopDir;
	}
	public void setShopDir(String shopDir) {
		this.shopDir = shopDir;
	}
	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", shopAddr=" + shopAddr
				+ ", shopTel=" + shopTel + ", shopDir=" + shopDir + "]";
	}
	public Shop(String shopName, String shopAddr, String shopTel,
			String shopDir, BmobFile shopPic) {
		super();
		this.shopName = shopName;
		this.shopAddr = shopAddr;
		this.shopTel = shopTel;
		this.shopDir = shopDir;
		this.shopPic = shopPic;
	}
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shop(String tableName) {
		super(tableName);
		// TODO Auto-generated constructor stub
	}
	
	
}
