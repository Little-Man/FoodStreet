package com.niit.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Product extends BmobObject{
	private String productName; //��Ʒ����
	private String productPrice;//��Ʒ�۸�
	//private String productNum;//��Ʒʣ������
	private String productDir;//��Ʒ����
	private Shop shop;//���̶���
	public boolean getTuijian() {
		return tuijian;
	}
	public void setTuijian(boolean tuijian) {
		this.tuijian = tuijian;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getProductShopId() {
		return productShopId;
	}
	public void setProductShopId(String productShopId) {
		this.productShopId = productShopId;
	}

	private BmobFile productImg;//��ƷͼƬ
	private boolean tuijian;//�Ƽ�ͼƬ
	private Category category;//��Ʒ���
	private String categoryId;//��Ʒ�����
	private String productShopId;//��Ʒ�������̱��
	
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public BmobFile getProductImg() {
		return productImg;
	}
	public void setProductImg(BmobFile productImg) {
		this.productImg = productImg;
	}
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	public String getProductDir() {
		return productDir;
	}
	public void setProductDir(String productDir) {
		this.productDir = productDir;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice="
				+ productPrice +  ", productDir="
				+ productDir + ", shop=" + shop + ", category=" + category
				+ ", categoryId=" + categoryId + ", productShopId=" + productShopId + "]";
	}
	public Product(String productName, String productPrice, String productNum,
			String productDir, Shop shop, BmobFile productImg, boolean tuijian,
			Category category, String categoryId, String shopId) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		
		this.productDir = productDir;
		this.shop = shop;
		this.productImg = productImg;
		this.tuijian = tuijian;
		this.category = category;
		this.categoryId = categoryId;
		this.productShopId = productShopId;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String tableName) {
		super(tableName);
		// TODO Auto-generated constructor stub
	}
	
}
