package com.niit.rs;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

import com.niit.bean.Order;
import com.niit.bean.Product;
import com.niit.bean.Shop;

public class OrderListDetailActivity extends Activity {

	private ListView lv;

	private ImageView img;
	private TextView tvOrderId;
	private TextView tvOrderTime;
	private TextView tvOrderTotal;
	private TextView tvOrderStatus;
	private TextView tvShopName;

	private List<Product> product;
	private Shop shop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list_detail);

		tvOrderId = (TextView) findViewById(R.id.orderId);
		tvOrderTime = (TextView) findViewById(R.id.orderTime);
		tvOrderTotal = (TextView) findViewById(R.id.orderTotal);
		tvShopName = (TextView) findViewById(R.id.shopName);
		tvOrderStatus = (TextView) findViewById(R.id.orderStatus);

		Intent intent = this.getIntent();
		String productId = intent.getStringExtra("productId");

		BmobQuery<Order> query = new BmobQuery<Order>();
		query.include("product");
		query.getObject(this, productId, new GetListener<Order>() {

			@Override
			public void onSuccess(Order o) {
				// TODO Auto-generated method stub

				tvOrderTime.setText("订单生成时间：" + o.getOrderTime());
				tvOrderTotal.setText("订单总价：" + o.getOrderTotalMoney());
				tvShopName.setText("店铺：" + o.getShop().getShopName());
				tvOrderStatus.setText("订单状态：" + o.getOrderStatus());
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});

		lv = (ListView) findViewById(R.id.orderlistDetaillv);

		final OrderProductAdapter adapter = new OrderProductAdapter();
		lv.setAdapter(adapter);

		BmobQuery<Product> query1 = new BmobQuery<Product>();
		query1.include("shop");
		if (productId != null && !"".equals(productId)) {
			query1.addWhereEqualTo("categoryId", productId);
		}

		query1.findObjects(this, new FindListener<Product>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				// Toast.makeText(getActivity(), "加载数据失败", 0);
			}

			@Override
			public void onSuccess(List<Product> products) {
				// TODO Auto-generated method stub
				product = products;
				adapter.setProducts(products);
				adapter.notifyDataSetChanged();
			}
		});

	}

	class OrderProductAdapter extends BaseAdapter {

		private List<Product> products;

		public void setProducts(List<Product> products) {
			this.products = products;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return products == null ? 0 : products.size();
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

	//		view = RelativeLayout.inflate(this,R.layout.orderdetail_list_item, null);

			Product p = products.get(index);

			TextView tvProductName = (TextView) view.findViewById(R.id.productName);
			TextView tvProductDir = (TextView) view.findViewById(R.id.productDir);
			TextView tvProductPrice = (TextView) view.findViewById(R.id.productPrice);
			
			ImageView tuijian = (ImageView) view.findViewById(R.id.productTuijian);
			ImageView img = (ImageView) view.findViewById(R.id.productImg);

			String dir = p.getProductName();
			if (dir.length() > 6) {
				dir = dir.substring(0, 6) + "...";
			}
			tvProductName.setText(dir);

			String dir_1 = p.getProductDir();
			if (dir_1.length() > 6) {
				dir_1 = dir_1.substring(0, 6);
			}
			tvProductDir.setText(dir_1);

			tvProductPrice.setText("价格：" + p.getProductPrice());

			if (p.getTuijian()) {
				tuijian.setVisibility(View.VISIBLE);
			}

			if (p.getProductImg() != null) {
				p.getProductImg().loadImage(OrderListDetailActivity.this, img);
			}

			return view;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_list_detail, menu);
		return true;
	}
}
