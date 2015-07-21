package com.wancheda.activity;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wancheda.manager.BaseActivity;

public class BookCarActivity extends BaseActivity {

	@ViewInject(id = R.id.head_title)
	TextView tv_head_title;
	@ViewInject(id = R.id.head_left)
	LinearLayout ll_head_left;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookcar);
		initView();
		initEvent();
	}

	private void initView() {
		tv_head_title.setText("预约取车");
	}

	private void initEvent() {
		ll_head_left.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.head_left:
			finish();
			break;

		default:
			break;
		}
	}

}
