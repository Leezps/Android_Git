package com.leezp.android.day03;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager
 *      需要使用适配器进行使用
 */

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private ViewAdapter adapter;
    private RadioGroup mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.teach_viewpager);
        //一个对象的实例化，直接new，通过他的各种工具类，获取出来
        adapter = new ViewAdapter(getSupportFragmentManager(), getData());
        mViewPager.setAdapter(adapter);
//        mViewPager.setOnPageChangeListener();
        mViewPager.addOnPageChangeListener(this);

        mIndicator = (RadioGroup) findViewById(R.id.activity_main_guide);
        RadioButton childOne = (RadioButton) mIndicator.getChildAt(0);
        childOne.setChecked(true);

    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        //向集合中添加数据
        data.add(new GuideOne());
        data.add(new GuideTwo());
        data.add(new GuideThree());
        return data;
    }

    /**
     *当页面进行滚动的时候
     *      positionOffset  0-1 半闭半开区间  [0,1)
     *
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * 当页面被选择的时候回调
     */
    @Override
    public void onPageSelected(int position) {
        RadioButton child = (RadioButton) mIndicator.getChildAt(position);
        child.setChecked(true);
    }

    /**
     * 当滚动状态发生改变的时候
     * state对应的三种状态，如下：
     *      1.静止状态
     *      2.在外力的作用下滚动、触摸、鼠标滑动
     *      3.在惯性的作用下滚动，跑起来
     */
    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
