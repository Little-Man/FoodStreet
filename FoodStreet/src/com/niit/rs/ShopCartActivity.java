package com.niit.rs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.niit.bean.ShopCart;
import com.niit.data.StaticData;

public class ShopCartActivity extends Activity {

	private ListView carLv;
	
	private ShapCarListAdapter adapter;

	private List<ShopCart> items = new ArrayList<ShopCart>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_cart);

		carLv = (ListView) findViewById(R.id.carList);
		adapter = new ShapCarListAdapter();
		carLv.setAdapter(adapter);
		
		BmobQuery<ShopCart> query = new BmobQuery<ShopCart>();
		query.addWhereEqualTo("userId", StaticData.loginUser.getObjectId());//根据登录用户的编号查询购物车中的列表
		query.include("product");
		query.order("-createAt");
		query.findObjects(this, new FindListener<ShopCart>() {
			
			@Override
			public void onSuccess(List<ShopCart> arg0) {
				adapter.setPros(arg0);
				adapter.notifyDataSetChanged();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				
			}
		});
		
	}
	class ShapCarListAdapter extends BaseAdapter {
		private List<ShopCart> pros;
		public void setPros(List<ShopCart> pros) {
			this.pros = pros;
		}

		@Override
		public int getCount() {
			return pros == null ? 0 : pros.size();
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
			view = RelativeLayout.inflate(ShopCartActivity.this,
					R.layout.shopcar_list_item, null);

			TextView tvPronName = (TextView) view.findViewById(R.id.proName);
			TextView tvPronDesc = (TextView) view.findViewById(R.id.proDesc);
			TextView tvProPrice = (TextView) view.findViewById(R.id.proPrice);
			TextView tvShopName = (TextView) view.findViewById(R.id.shopName);
			TextView tvProCount = (TextView) view.findViewById(R.id.proCount);


			ShopCart c = pros.get(index);

			String name = c.getProduct().getProductName();
			if(name.length()>5){
				name = name.substring(0, 5)+"...";
			}
			tvPronName.setText(name);
			
			String dir = c.getProduct().getProductDir();
			if(dir.length()>11){
				dir = dir.substring(0, 11)+"...";
			}
			tvPronDesc.setText(dir);
			tvProPrice.setText("价格:" + c.getProduct().getProductPrice());
			tvShopName.setText(c.getShopName());
			tvProCount.setText("X"+c.getProductNum());
			return view;
		}
	}

	public void back(View v) {
		this.finish();
	}
}