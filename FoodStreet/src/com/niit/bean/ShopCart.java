package com.niit.bean;

import cn.bmob.v3.BmobObject;

public class ShopCart extends BmobObject {
	private int productNum;
	private Product product;
	private User user;
	private String productId;
	private String userId;
	private String shopName;
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Override
	public String toString() {
		return "ShopCart [productNum=" + productNum + ", product=" + product
				+ ", user=" + user + ", productId=" + productId + ", userId="
				+ userId + ", shopName=" + shopName + "]";
	}
	public ShopCart(int prodouctNum, Product product, User user,
			String productId, String userId, String shopName) {
		super();
		this.productNum = productNum;
		this.product = product;
		this.user = user;
		this.productId = productId;
		this.userId = userId;
		this.shopName = shopName;
	}
	public ShopCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShopCart(String tableName) {
		super(tableName);
		// TODO Auto-generated constructor stub
	}
	
}