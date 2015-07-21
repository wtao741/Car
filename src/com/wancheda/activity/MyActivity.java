package com.wancheda.activity;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wancheda.manager.BaseActivity;

public class MyActivity extends BaseActivity {

	@ViewInject(id=R.id.my_order) RelativeLayout rl_my_order;
	@ViewInject(id=R.id.my_car_manager) RelativeLayout rl_car_manager;
	@ViewInject(id=R.id.my_account) RelativeLayout rl_account;
	@ViewInject(id=R.id.my_adjust) RelativeLayout rl_adjust;
	@ViewInject(id=R.id.my_setting) RelativeLayout rl_setting;
	
	@ViewInject(id=R.id.head_title) TextView tv_head_title;
	@ViewInject(id=R.id.my_car_type) TextView tv_car_type;
	@ViewInject(id=R.id.my_car_distance) TextView tv_car_distance;
	@ViewInject(id=R.id.my_car_model) TextView tv_car_model;
	@ViewInject(id=R.id.my_car_late) TextView tv_car_late;
	
	@ViewInject(id=R.id.my_help) Button bt_help;
	@ViewInject(id=R.id.my_call) Button bt_call;
	@ViewInject(id=R.id.my_service) Button bt_service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_my);
		
		initView();
	}

	private void initView() {
		tv_head_title.setText("我的");
		
		rl_car_manager.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.my_car_manager:
			
			break;

		default:
			break;
		}
	}
}
