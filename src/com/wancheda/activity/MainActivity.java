package com.wancheda.activity;

import java.io.IOException;
import java.util.List;

import cn.bmob.v3.Bmob;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.wancheda.manager.BaseApplication;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnClickListener{
	
	public static String TAB_HOME = "home";
	public static String TAB_ORDER = "order";
	public static String TAB_MY = "my";
	public static String TAB_CALL = "call";
	public static TabHost mTabHost;
	static final int COLOR_DEFAULT = Color.parseColor("#828282");
	static final int COLOR_CLICK = Color.parseColor("#ff5837");
	ImageView mBut1, mBut2, mBut3, mBut4;
	TextView mCateText1, mCateText2, mCateText3, mCateText4;

	Intent homeIntent, orderIntent, myIntent, callIntent;

	int mCurTabId = R.id.channel3;

	// 是否是第一次登录、保存登录后的信息、share得到保存的城市
	private SharedPreferences shared, loginShare,share;
	private Editor edit, loginEdit;
    private int flag = 0;
    
    public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;
	
	private static String APP_ID = "5836bccc74527302934dd7848ae36184";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bmob.initialize(this, APP_ID);
		
		prepareIntent();
		setupIntent();
		prepareView();
		
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(getApplicationContext());

		mLocationClient.start();
		mLocationClient.requestLocation();
		
		
		if(savedInstanceState != null){
			int flag = savedInstanceState.getInt("flag");
			switch (flag) {
			case 1:
				mTabHost.setCurrentTabByTag(TAB_HOME);
				mBut3.setImageResource(R.drawable.recommend_select);
				mCateText3.setTextColor(COLOR_CLICK);
				break;
			case 2:
				mBut3.setImageResource(R.drawable.recommend_normal);
				mCateText3.setTextColor(COLOR_DEFAULT);
				mTabHost.setCurrentTabByTag(TAB_ORDER);
				mBut1.setImageResource(R.drawable.nearby_select);
				mCateText1.setTextColor(COLOR_CLICK);
				break;
			case 3:
				mBut3.setImageResource(R.drawable.recommend_normal);
				mCateText3.setTextColor(COLOR_DEFAULT);
				mTabHost.setCurrentTabByTag(TAB_MY);
				mBut2.setImageResource(R.drawable.select_select);
				mCateText2.setTextColor(COLOR_CLICK);
				break;
			case 4:
				mBut3.setImageResource(R.drawable.recommend_normal);
				mCateText3.setTextColor(COLOR_DEFAULT);
				mTabHost.setCurrentTabByTag(TAB_CALL);
				mBut4.setImageResource(R.drawable.sort_select);
				mCateText4.setTextColor(COLOR_CLICK);
				break;
			}
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (mCurTabId == v.getId()) {
			return;
		}
		mBut3.setImageResource(R.drawable.recommend_normal);
		mBut1.setImageResource(R.drawable.nearby_normal);
		mBut2.setImageResource(R.drawable.search_normal);
		mBut4.setImageResource(R.drawable.sort_normal);
		mCateText1.setTextColor(COLOR_DEFAULT);
		mCateText2.setTextColor(COLOR_DEFAULT);
		mCateText3.setTextColor(COLOR_DEFAULT);
		mCateText4.setTextColor(COLOR_DEFAULT);
		int checkedId = v.getId();
		switch (checkedId) {
		case R.id.channel3:
			mTabHost.setCurrentTabByTag(TAB_HOME);
			mBut3.setImageResource(R.drawable.recommend_select);
			mCateText3.setTextColor(COLOR_CLICK);
			flag = 1;
			break;
		case R.id.channel1:
			mTabHost.setCurrentTabByTag(TAB_ORDER);
			mBut1.setImageResource(R.drawable.nearby_select);
			mCateText1.setTextColor(COLOR_CLICK);
			flag = 2;
			break;
		case R.id.channel2:
			mTabHost.setCurrentTabByTag(TAB_MY);
			mBut2.setImageResource(R.drawable.select_select);
			mCateText2.setTextColor(COLOR_CLICK);
			flag = 3;
			break;
		case R.id.channel4:
			mTabHost.setCurrentTabByTag(TAB_CALL);
			mBut4.setImageResource(R.drawable.sort_select);
			mCateText4.setTextColor(COLOR_CLICK);
			flag = 4;
			break;
		}
		mCurTabId = checkedId;
	}

	private void prepareView() {
		mBut1 = (ImageView) findViewById(R.id.imageView1);
		mBut2 = (ImageView) findViewById(R.id.imageView2);
		mBut3 = (ImageView) findViewById(R.id.imageView3);
		mBut4 = (ImageView) findViewById(R.id.imageView4);
		findViewById(R.id.channel1).setOnClickListener(this);
		findViewById(R.id.channel2).setOnClickListener(this);
		findViewById(R.id.channel3).setOnClickListener(this);
		findViewById(R.id.channel4).setOnClickListener(this);
		mCateText1 = (TextView) findViewById(R.id.textView1);
		mCateText2 = (TextView) findViewById(R.id.textView2);
		mCateText3 = (TextView) findViewById(R.id.textView3);
		mCateText4 = (TextView) findViewById(R.id.textView4);
		mCateText3.setTextColor(COLOR_CLICK);
	}

	private void prepareIntent() {
		homeIntent = new Intent(this, HomeActivity.class);
		orderIntent = new Intent(this, OrderActivity.class);
		myIntent = new Intent(this, MyActivity.class);
		callIntent = new Intent(this, CallActivity.class);
	}

	public static void setCurrentTabByTag(String tab) {
		mTabHost.setCurrentTabByTag(tab);
	}
	
	private void setupIntent() {
		mTabHost = getTabHost();
		mTabHost.addTab(buildTabSpec(TAB_HOME, R.string.tab_main,
				R.drawable.recommend_select, homeIntent));
		mTabHost.addTab(buildTabSpec(TAB_ORDER, R.string.tab_order,
				R.drawable.nearby_normal, orderIntent));
		mTabHost.addTab(buildTabSpec(TAB_MY, R.string.tab_my,
				R.drawable.search_normal, myIntent));
		mTabHost.addTab(buildTabSpec(TAB_CALL, R.string.tab_call,
				R.drawable.sort_normal, callIntent));
	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return mTabHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("flag", flag);
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			// sb.append("time : ");
			// sb.append(location.getTime());
			// sb.append("\nerror code : ");
			// sb.append(location.getLocType());
			// sb.append("\nlatitude : ");
			BaseApplication.lat = location.getLatitude();
			// sb.append("\nlontitude : ");
			BaseApplication.lng = location.getLongitude();
			// sb.append("\nradius : ");
			// sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				// sb.append("\nspeed : ");
				// sb.append(location.getSpeed());
				// sb.append("\nsatellite : ");
				// sb.append(location.getSatelliteNumber());
				// sb.append("\ndirection : ");
				// sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				// sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				// sb.append("\noperationers : ");
				// sb.append(location.getOperators());
			}
			Geocoder geocoder = new Geocoder(MainActivity.this);
			List<Address> list = null;
			try {
				list = geocoder.getFromLocation(BaseApplication.lat,
						BaseApplication.lng, 1);
				if (list.size() != 0) {
					Address add = list.get(0);
					BaseApplication.address = add.getAddressLine(0);
					Log.e("tag",""+add.getLocale());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
