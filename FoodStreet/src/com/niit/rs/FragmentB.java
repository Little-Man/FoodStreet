package com.niit.rs;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.niit.bean.Shop;

public class FragmentB extends Fragment{

	private ListView lv;
	
	private List<Shop> sps;
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fragmentb, container, false);
		
		lv = (ListView) view.findViewById(R.id.shopLv);
		final ShopListAdapter adapter = new ShopListAdapter();
		lv.setAdapter(adapter);
		
		BmobQuery<Shop> query = new BmobQuery<Shop>();
		
		query.findObjects(getActivity(), new FindListener<Shop>(){
			@Override
			public void onSuccess(List<Shop> shops) {
				//将查询出来的商品类表数据存储在sps中
				sps = shops;
				
				adapter.setShop(shops);

				adapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {

			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int index,
					long arg3) {
				Intent intent = new Intent(getActivity(),ShopProductActivity.class);
				intent.putExtra("shopName", sps.get(index).getShopName());
				intent.putExtra("shopId", sps.get(index).getObjectId());
				getActivity().startActivity(intent);
			}
			
		});

		return view;
	}
	
	class ShopListAdapter extends BaseAdapter{

		private List<Shop> shops;
		
		public void setShop(List<Shop> shops){
			this.shops = shops;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return shops==null?0:shops.size();
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
			
			view = LinearLayout.inflate(getActivity(), R.layout.shop_list_item,
					null);
			Shop shop = shops.get(index);

			// 获取视图中的每一个控件对象
			ImageView img = (ImageView) view.findViewById(R.id.shopPic);
			TextView tvShopName = (TextView) view.findViewById(R.id.shopName);
			TextView tvShopDir = (TextView) view.findViewById(R.id.shopDir);
			TextView tvShopAddr = (TextView) view.findViewById(R.id.shopAddr);
			TextView tvShopTel = (TextView) view.findViewById(R.id.shopTel);
			// 将对象中的数据填充到每一个对应的控件中
			tvShopName.setText(shop.getShopName());
			
			String dir = shop.getShopDir();
			if(dir.length()>15){
				dir = dir.substring(0, 15)+"...";
			}
			tvShopDir.setText(dir);
			
			String add = shop.getShopAddr();
			if(add.length()>5){
				add = add.substring(0, 5)+"...";
			}
			tvShopAddr.setText("地址："+add);
			tvShopTel.setText("电话:"+shop.getShopTel());

			// 处理图片
			if (shop.getShopPic() != null) {
				shop.getShopPic().loadImage(getActivity(), img);
			}
			return view;
		}
		
	}
}
