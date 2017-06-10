package com.leezp.android.day04;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/6/10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

}
