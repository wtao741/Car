package com.wancheda.activity;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wancheda.manager.BaseActivity;

public class CallActivity extends BaseActivity {

	@ViewInject(id = R.id.call_vip)
	TextView tv_call_vip;
	@ViewInject(id = R.id.call_south)
	TextView tv_call_south;
	@ViewInject(id = R.id.call_north)
	TextView tv_call_north;
	@ViewInject(id = R.id.call_cdu)
	TextView tv_call_cdu;
	@ViewInject(id = R.id.call)
	ImageView iv_call;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_call);
		
		tv_call_vip.setOnClickListener(this);
		tv_call_south.setOnClickListener(this);
		tv_call_north.setOnClickListener(this);
		tv_call_cdu.setOnClickListener(this);
		iv_call.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		String action = "tel:";
		switch (v.getId()) {
		case R.id.call:
            action = action+"4000-050607";
			break;
		case R.id.call_vip:
			 action = action+getResoure(R.string.call_vip_phone);
			break;
		case R.id.call_south:
			 action = action+getResoure(R.string.call_south_phone);
			break;
		case R.id.call_north:
			 action = action+getResoure(R.string.call_north_phone);
			break;
		case R.id.call_cdu:
			 action = action+getResoure(R.string.call_cdu_phone);
			break;
		}
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri
				.parse(action));
		startActivity(intent);
	}
}
