package com.wancheda.manager;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;

public class BaseApplication extends Application {

	private static Context mContext;

	public static final String URL = "";

	public static double lng;

	public static double lat;

	public static String address;

	public static boolean isLogin;
	
	private SharedPreferences share;
	private static String user;
	private static String password;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = this;
		
		//得到用户名和密码去登录，
		share = getSharedPreferences("login", MODE_PRIVATE);
		user = share.getString("username", "");
		password = share.getString("password", "");
	}

	public static Context getAppContext() {
		return mContext;
	}
	
	@Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 应用是否处于debug模式
     * 
     * @return
     */
    public static boolean isDebugMode() {
        ApplicationInfo info = getAppContext().getApplicationInfo();
        return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
