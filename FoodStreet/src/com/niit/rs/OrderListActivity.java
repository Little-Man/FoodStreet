package com.niit.rs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.niit.bean.Order;
import com.niit.data.StaticData;

public class OrderListActivity extends Activity {
	
	private ListView orderLv;
	private List<Order> ol = new ArrayList<Order>();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);
		
		orderLv= (ListView)findViewById(R.id.orderList);
		
		final OrderListAdapter adapter = new OrderListAdapter();
		orderLv.setAdapter(adapter);
		
		BmobQuery<Order> query = new BmobQuery<Order>();		
		query.order("-createdAt");//���մ���ʱ��Ľ�������
		//���ݵ�ǰ��¼�û��ı�Ų�ѯ����
		query.addWhereEqualTo("userId", StaticData.loginUser.getObjectId());
		query.findObjects(this, new FindListener<Order>() {
			@Override
			public void onSuccess(List<Order> arg0) {
				adapter.setOls(arg0);
				adapter.notifyDataSetChanged();
			}
			@Override
			public void onError(int arg0, String arg1) {
				
			}
		});		
		//�����¼�
		orderLv.setOnItemLongClickListener(new OnItemLongClickListener() {			
			int chose = 0;			
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int index, long arg3) {
				new AlertDialog.Builder(OrderListActivity.this).setTitle("��ѡ�����Ĳ���")
				.setSingleChoiceItems(new String[]{"ȡ������","��������"}, 0, new OnClickListener() {						
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								chose = arg1;
							}
						}).setPositiveButton("ȷ��", new OnClickListener() {						
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								switch(chose){
								case 0:
									Toast.makeText(OrderListActivity.this, "��Ҫȡ������", 0).show();
									
									break;
								case 1:
									Toast.makeText(OrderListActivity.this, "��Ҫ��������", 0).show();
									
									break;
								}
							}
						}).setNegativeButton("ȡ��", null).create().show();
				return true;
			}
		});
		//�����¼�
		orderLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int index,
					long arg3) {
				

				Intent intent = new Intent(OrderListActivity.this,OrderListDetailActivity.class);
				
				intent.putExtra("objectId",ol.get(index).getObjectId());
				startActivity(intent);
			}
		});
	}
	
	class OrderListAdapter extends BaseAdapter{	
		private List<Order> ols;
		public void setOls(List<Order> ol) {
			OrderListAdapter.this.ols = ol;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return ols==null?0:ols.size();
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
			view = LinearLayout.inflate(OrderListActivity.this, R.layout.order_list_item, null);
			TextView tvorderId = (TextView)view.findViewById(R.id.orderId);
			TextView tvshopName = (TextView)view.findViewById(R.id.shopName);
			TextView tvorderTime = (TextView)view.findViewById(R.id.orderTime);
			TextView tvproCount = (TextView)view.findViewById(R.id.proCount);
			TextView tvorderTotal = (TextView)view.findViewById(R.id.orderTotal);
			TextView tvorderStatus = (TextView)view.findViewById(R.id.orderStatus);
			
			Order o = OrderListAdapter.this.ols.get(index);
			tvorderId.setText(o.getObjectId());
			
			String shopName = o.getShop().getShopName();
			tvshopName.setText(shopName);
			
			tvorderTime.setText(o.getOrderTime());
			tvproCount.setText(o.getOrderCount()+"");
			tvorderTotal.setText(o.getOrderTotalMoney()+"Ԫ");
			switch(o.getOrderStatus()){
			case 1:
				tvorderStatus.setText("�ȴ�����");
				break;
			case 2:
				tvorderStatus.setText("�������");
				break;
			}
			return view;
		}	
	}
}
