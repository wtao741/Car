package com.wancheda.manager;

import com.wancheda.activity.R;

import android.view.View;
import android.view.View.OnClickListener;
import net.tsz.afinal.FinalActivity;

public class BaseActivity extends FinalActivity implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public String getResoure(int id){
		return getResources().getString(id);
	}
	
}
