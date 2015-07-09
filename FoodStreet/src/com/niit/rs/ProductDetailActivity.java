package com.niit.rs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

import com.niit.bean.Order;
import com.niit.bean.Product;
import com.niit.bean.Shop;
import com.niit.bean.ShopCart;
import com.niit.data.StaticData;

public class ProductDetailActivity extends Activity {

	private ImageView img;
	private TextView tvProductName;
	private TextView tvProductDir;
	private TextView tvProductPrice;
	private TextView tvShopName;
	private TextView tvProductCategory;
	
	private String productShopId;
	private Product product;
	private Shop shop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);

		img = (ImageView) findViewById(R.id.productImg);
		tvProductName = (TextView) findViewById(R.id.productName);
		tvProductDir = (TextView) findViewById(R.id.productDir);
		tvProductPrice = (TextView) findViewById(R.id.productPrice);
		tvShopName = (TextView) findViewById(R.id.shopName);
		tvProductCategory = (TextView) findViewById(R.id.productCategory);

		Intent intent = this.getIntent();
		String productId = intent.getStringExtra("productId");
		String productShopId = intent.getStringExtra("productShopId");
		//查询商品信息
		BmobQuery<Product> query = new BmobQuery<Product>();

		query.include("category");
		
		query.getObject(this, productId, new GetListener<Product>() {
			
			@Override
			public void onSuccess(Product p) {
				// TODO Auto-generated method stub
				product = p;
				if(p.getProductImg()!=null){
					p.getProductImg().loadImage(ProductDetailActivity.this, img);
				}
				tvProductName.setText("商品名称："+p.getProductName());
				tvProductDir.setText("商品描述："+p.getProductDir());
				tvProductPrice.setText("商品价格："+p.getProductPrice());
				tvProductCategory.setText("商品类别："+p.getCategory().getCategoryName());
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		BmobQuery<Shop> query1 = new BmobQuery<Shop>();
		query1.getObject(this, productShopId, new GetListener<Shop>() {
			@Override
			public void onFailure(int arg0, String arg1) {
				
			}
			@Override
			public void onSuccess(Shop shop) {
				ProductDetailActivity.this.shop=shop;
				tvShopName.setText("商店名称："+shop.getShopName());
			}
		});

	}
	
	public void toShoppingCart(View v){
		final EditText editText = new EditText(this);
		new AlertDialog.Builder(this).setTitle("提示").setMessage("请输入数量").setView(editText)
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				if(StaticData.loginUser==null){
					Toast.makeText(ProductDetailActivity.this, "请先登录", 0);
					Intent intent = new Intent(ProductDetailActivity.this,LoginActivity.class);
					startActivity(intent);
					return ;
				}
				final int productCount = Integer.parseInt(editText.getText().toString());
				BmobQuery<ShopCart> query = new BmobQuery<ShopCart>();
				query.addWhereEqualTo("userId", StaticData.loginUser.getObjectId());
				query.addWhereEqualTo("productId", product.getObjectId());
				query.findObjects(ProductDetailActivity.this,new FindListener<ShopCart>() {
					
					@Override
					public void onSuccess(List<ShopCart> scs) {
						// TODO Auto-generated method stub
						if(scs.size()>0){
							scs.get(0).setProductNum(scs.get(0).getProductNum()+productCount);
							scs.get(0).update(ProductDetailActivity.this);
							Intent intent = new Intent(ProductDetailActivity.this, ShopCartActivity.class);
							startActivity(intent);
						}else{
							ShopCart sc = new ShopCart();
							sc.setUser(StaticData.loginUser);
							sc.setUserId(StaticData.loginUser.getObjectId());
							sc.setProductNum(productCount);
							sc.setProduct(product);
							sc.setProductId(product.getObjectId());
							
							//sc.setShopName(shop.getShopName());
							sc.save(ProductDetailActivity.this,new SaveListener() {
								
								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub
									Intent intent = new Intent(ProductDetailActivity.this, ShopCartActivity.class);
									startActivity(intent);
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub
									
								}
							});
							
						}
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						//创建购物车条目对象
						ShopCart sc = new ShopCart();
						sc.setUser(StaticData.loginUser);
						sc.setUserId(StaticData.loginUser.getObjectId());
						sc.setProductNum(productCount);
						sc.setProduct(product);
						sc.setProductId(product.getObjectId());
						sc.setShopName(shop.getShopName());
						sc.save(ProductDetailActivity.this, new SaveListener() {
							
							@Override
							public void onSuccess() {
								Intent intent = new Intent(ProductDetailActivity.this, ShopCartActivity.class);
								startActivity(intent);
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								
							}
						});
					}
				});				
			}
		}).setNegativeButton("取消", null).create().show();		
	}

	public void toOrderForm(View v){
		final EditText editText = new EditText(this);
		new AlertDialog.Builder(this).setTitle("提示").setMessage("请输入数量:").setView(editText)
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				if(StaticData.loginUser==null){
					Toast.makeText(ProductDetailActivity.this, "请先登录", 0).show();
					Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
					startActivity(intent);
					return;
				}
				int productCount = Integer.parseInt(editText.getText().toString());
				//创建订单对象
				Order order = new Order();
				order.setOrderCount(productCount);
				order.setOrderStatus(0);
				order.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				order.setOrderTotalMoney(Integer.parseInt(product.getProductPrice())*productCount);
				order.setShop(shop);
				order.setShopId(shop.getObjectId());
				order.setUser(StaticData.loginUser);
				order.setUserId(StaticData.loginUser.getObjectId());
				order.save(ProductDetailActivity.this, new SaveListener() {
					
					@Override
					public void onSuccess() {
						Intent intent = new Intent(ProductDetailActivity.this,OrderListActivity.class);
						startActivity(intent);
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(ProductDetailActivity.this, "操作失败，请重试", 0).show();
					}
				});
			}
		}).setNegativeButton("取消", null).create().show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_detail, menu);
		return true;
	}

}
