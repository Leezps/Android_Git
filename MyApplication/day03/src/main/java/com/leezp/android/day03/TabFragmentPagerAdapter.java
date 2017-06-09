package com.leezp.android.day03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<TabData> data;

    public TabFragmentPagerAdapter(FragmentManager fm, List<TabData> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        TabData tabData = data.get(position);
        TabCommentFragment fragment = new TabCommentFragment();
        //将数据传递到Fragment中
        /**
         * Bundle 数据载体
         *      存储数据使用put
         *      获取数据使用get
         */
        Bundle bundle = new Bundle();
        //存数据 key-value
        bundle.putString("content", tabData.getMsg());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "标题->"+position;
    }
}
