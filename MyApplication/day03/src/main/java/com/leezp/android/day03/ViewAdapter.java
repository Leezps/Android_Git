package com.leezp.android.day03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class ViewAdapter extends FragmentPagerAdapter {
    /**
     * List 数据源
     *      List集合
     *      Map
     *      Set
     *      List -> ArrayList有序，查找效率高
     */
    private List<Fragment> data;

    public ViewAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    /**
     * 根据位置返回对应的Fragment
     *      前提拥有所有的Fragment的集合
     */
    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    /**
     *获取数据源的条目数
     */
    @Override
    public int getCount() {
        return data.size();
    }
}
