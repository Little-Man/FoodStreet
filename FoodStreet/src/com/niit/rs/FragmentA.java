package com.niit.rs;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.niit.bean.Product;
import com.niit.bean.Shop;

public class FragmentA extends Fragment{

	private ListView lv;
	
	private String categoryId;
	

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	private List<Product> product = new ArrayList<Product>();

	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
		
		View v = inflater.inflate(R.layout.fragmenta, container, false);
		lv=(ListView)v.findViewById(R.id.productLv);
		
		final ProductListAdapter adapter = new ProductListAdapter();
		lv.setAdapter(adapter);
		
		BmobQuery<Product> query = new BmobQuery<Product>();
		query.include("shop");
		if(categoryId!=null&&!"".equals(categoryId)){
			query.addWhereEqualTo("categoryId", categoryId);
		}
		
		query.findObjects(getActivity(),new FindListener<Product>(){

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "加载数据失败", 0);
			}

			@Override
			public void onSuccess(List<Product> products) {
				// TODO Auto-generated method stub
				product = products;
				adapter.setProducts(products);
				adapter.notifyDataSetChanged();
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Intent intent = new Intent(getActivity(),ProductDetailActivity.class);
				intent.putExtra("productId", product.get(index).getObjectId());
				intent.putExtra("productShopId", product.get(index).getProductShopId());
				getActivity().startActivity(intent);
			}
		});
		
		return v;
	}
	
	class ProductListAdapter extends BaseAdapter{
		
		private List<Product> products;
		
		public void setProducts(List<Product> products){
			this.products=products;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return products==null?0:products.size();
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
			
			view = RelativeLayout.inflate(getActivity(), R.layout.product_list_item, null);
			
			Product p = products.get(index);
			
			TextView tvProductName = (TextView)view.findViewById(R.id.productName);
			TextView tvProductDir = (TextView)view.findViewById(R.id.productDir);
			TextView tvProductPrice = (TextView)view.findViewById(R.id.productPrice);
			TextView tvShopName = (TextView)view.findViewById(R.id.shopName);
			
			ImageView tuijian = (ImageView)view.findViewById(R.id.productTuijian);
			ImageView img = (ImageView)view.findViewById(R.id.productImg);
			
			tvShopName.setText(p.getShop().getShopName());
			String dir = p.getProductName();
			if(dir.length()>7){
				dir=dir.substring(0,7)+"...";
			}
			tvProductName.setText(dir);
			
			String dir_1 = p.getProductDir();
			if(dir_1.length()>10){
				dir_1=dir_1.substring(0, 10)+"...";
			}
			tvProductDir.setText(dir_1);
			
			tvProductPrice.setText("价格："+p.getProductPrice());
			
			if(p.getTuijian()){
				tuijian.setVisibility(View.VISIBLE);
			}
			
			if(p.getProductImg()!=null){
				p.getProductImg().loadImage(getActivity(), img);
			}
			return view;
		}
		
	}
}
