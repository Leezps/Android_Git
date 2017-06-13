package com.leezp.android.day06;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/6/13.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

}
