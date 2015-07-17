package com.wancheda.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.tsz.afinal.annotation.view.ViewInject;

import com.wancheda.manager.BaseActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends BaseActivity {

	@ViewInject(id = R.id.home_viewPager)
	ViewPager viewPager;
	@ViewInject(id = R.id.v_dot0)
	View dot0;
	@ViewInject(id = R.id.v_dot1)
	View dot1;
	@ViewInject(id = R.id.v_dot2)
	View dot2;

	private List<View> dots;
	private int[] images = new int[] { R.drawable.home_advert,
			R.drawable.viewpager1, R.drawable.viewpager2 };
	private List<ImageView> ivs;
    private int currentPosition;
	
    private ScheduledExecutorService scheduledExecutorService;

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentPosition);// 切换当前显示的图片
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		initViewPager();
	}

	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		scheduledExecutorService.shutdown();
		super.onStop();
	}
	
	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentPosition);
				currentPosition = (currentPosition + 1) % images.length;
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}
	
	private void initViewPager() {
		// TODO Auto-generated method stub
		dots = new ArrayList<View>();
		dots.add(dot0);
		dots.add(dot1);
		dots.add(dot2);

		ivs = new ArrayList<ImageView>();
		for (int i = 0; i < images.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(images[i]);
			ivs.add(iv);
		}
		
		viewPager.setAdapter(new ViewPagerAdapter());
		viewPager.setOnPageChangeListener(new ViewPagerListener());
	}

	class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(ivs.get(position));
			return ivs.get(position);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView((View)object);
		}
	}
	
	class ViewPagerListener implements OnPageChangeListener{

		private int oldPosition = 0;
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			currentPosition = arg0;
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(arg0).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = arg0;
		}
		
	}
}
