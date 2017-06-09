package com.leezp.android.day03;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 导航条
 *      TabLayout
 */

public class SecondActivity extends AppCompatActivity {

    private boolean isExit;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.activity_second_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.activity_second_viewPager);
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), getData());
        mViewPager.setAdapter(adapter);
        //TabLayout与ViewPager进行绑定,还需重写PagerAdapter中的getPagerTitle方法
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * fori 快捷生成for循环结构
     */
    private List<TabData> getData() {
        List<TabData> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TabData tabData = new TabData();
            tabData.setMsg("猴子"+i);
            data.add(tabData);
        }
        return data;
    }

    //当设备的物理按键被按下时的监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断按下的键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit = true;
                //定时任务 定时器
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        //还原状态
                        isExit = false;
                    }
                };
                //执行定时任务
                timer.schedule(timerTask, 3*1000);

                //true代表事件已处理
                return true;
            }
        }
        return  super.onKeyDown(keyCode, event);
    }
}
