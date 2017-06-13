package com.leezp.android.viewpagerindicator;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager mContent;
    private ContentPagerAdapter adapter;
    private View mIndicator;
    private TextView mLast;
    private TextView mChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
    }

    private void initView() {
        mIndicator = findViewById(R.id.activity_main_indicator);
        mLast = (TextView) findViewById(R.id.activity_main_last);
        mChannel = (TextView) findViewById(R.id.activity_main_channel);
        mLast.setOnClickListener(this);
        mChannel.setOnClickListener(this);

        mContent = (ViewPager) findViewById(R.id.activity_main_content);
        adapter = new ContentPagerAdapter(getSupportFragmentManager(), getData());
        mContent.setAdapter(adapter);
        mContent.addOnPageChangeListener(this);
    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();

        data.add(new MovieListFragment());
        data.add(new ChannelListFragment());

        return data;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int width = mLast.getWidth();
        mIndicator.setTranslationX(width*(positionOffset+position));
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mLast.setTextColor(getResources().getColor(R.color.colorWhite));
                mChannel.setTextColor(getResources().getColor(R.color.colorGray));
                break;
            case 1:
                mLast.setTextColor(0xFFAAAAAA);
                mChannel.setTextColor(0xFFFFFFFF);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_last:
                //将指定位置的item滚动到屏幕中央
                mContent.setCurrentItem(0);
                break;
            case R.id.activity_main_channel:
                mContent.setCurrentItem(1);
                break;
        }
    }
}
