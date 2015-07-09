package com.niit.rs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.niit.bean.Product;

public class ShopProductActivity extends Activity {
	
	private ListView lv;
	private TextView tvShopName ;
	private List<Product> spls = new ArrayList<Product>();
	private String productShopId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_product);
		
		tvShopName = (TextView)findViewById(R.id.shopName);
		
		Intent intent = this.getIntent();
		tvShopName.setText(intent.getStringExtra("shopName"));
		
		productShopId = intent.getStringExtra("shopId");
		lv = (ListView)findViewById(R.id.productLv);
		final ProductListAdapter adapter = new ProductListAdapter();
		lv.setAdapter(adapter);
		
		BmobQuery<Product> query = new BmobQuery<Product>();
		query.addWhereEqualTo("productShopId", productShopId);
		
		
		query.findObjects(this, new FindListener<Product>() {
			@Override
			public void onSuccess(List<Product> pros) {
				// TODO Auto-generated method stub
				
				spls = pros;//将查询出来的商品类表数据存储在spls中。
				adapter.setPros(pros);
				adapter.notifyDataSetChanged();
				
			}
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(ShopProductActivity.this, "加载店铺失败", 0).show();
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Intent intent = new Intent(ShopProductActivity.this,ProductDetailActivity.class);
				intent.putExtra("productId", spls.get(index).getObjectId());
				intent.putExtra("shopId", spls.get(index).getProductShopId());
				startActivity(intent);
			}
		});
	}

	class ProductListAdapter extends BaseAdapter{
		private List<Product> pros;
		public void setPros(List<Product> pros) {
			this.pros = pros;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pros==null?0:pros.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int index, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			view = RelativeLayout.inflate(ShopProductActivity.this, R.layout.product_list_item, null);
			
			TextView tvProductName = (TextView)view.findViewById(R.id.productName);
			TextView tvProductPrice = (TextView)view.findViewById(R.id.productPrice);
			TextView tvProductDir = (TextView)view.findViewById(R.id.productDir);
			TextView tvShopName = (TextView)view.findViewById(R.id.shopName);
			
			ImageView tuijian = (ImageView)view.findViewById(R.id.productTuijian);
			ImageView img = (ImageView)view.findViewById(R.id.productImg);
			
			Product p = pros.get(index);
			
			String productName = p.getProductName();
			if(productName.length()>7){
				productName=productName.substring(0,7)+"...";
			}
			tvProductName.setText(productName);
			
			tvProductPrice.setText(p.getProductPrice());
			
			String productDir = p.getProductDir();
			if(productDir.length()>10){
				productDir=productDir.substring(0, 10)+"...";
			}
			tvProductDir.setText(productDir);
			
			tvShopName.setText(p.getShop().getShopName());
			
			if(p.getTuijian()){
				tuijian.setVisibility(View.VISIBLE);
			}
			if(p.getProductImg()!=null){
				p.getProductImg().loadImage(ShopProductActivity.this, img);
			}
			return view;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_product, menu);
		return true;
	}

}
