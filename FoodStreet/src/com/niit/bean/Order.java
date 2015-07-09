package com.niit.bean;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject {
	private String orderTime;
	private int orderCount;
	private int orderTotalMoney;
	private int orderStatus;
	private String shopId;
	private Shop shop;
	private String userId;
	private User user;
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public int getOrderTotalMoney() {
		return orderTotalMoney;
	}
	public void setOrderTotalMoney(int orderTotalMoney) {
		this.orderTotalMoney = orderTotalMoney;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
