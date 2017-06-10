package com.leezp.android.image;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/6/10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化操作
        x.Ext.init(this);
        //让他可以给我们打印更多的日志
        x.Ext.setDebug(true);
    }
}
