package com.leezp.android.loading;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Android 中的动画系统
 *  API 1
 *      逐帧动画
 *      补间动画 对View进行 缩放 旋转 平移 渐变
 *   API 11 Android 3.0 属性动画
 *   API 21 Android 转场动画
 */

public class MainActivity extends AppCompatActivity {

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.teach_image);
        mImage.setBackgroundResource(R.drawable.loading);
        //获取背景资源，转换成动画
        AnimationDrawable animationDrawable = (AnimationDrawable) mImage.getBackground();
        //开启动画
        animationDrawable.start();
    }
}
