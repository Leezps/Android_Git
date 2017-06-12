package com.leezp.android.twoweek;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Android 中的动画系统
 *      API 1
 *          逐帧动画
 *          补间动画
 *              - 平移
 *              - 旋转
 *              - 缩放
 *              - 渐变
 *              - 动画集合
 *          API 11
 *              属性动画
 *                  - 修改固有属性，实现动画效果
 *                  - 可修改的属性都可以做成动画
 *         API 21
 *              转场动画
 *                  - 整体转场
 *                  - 共享元素
 *                  - 水波纹揭露
 *
 *          补间动画    重要特性
 *              View不会发生真正的改变，做动画的知识一个虚影
 *              官方更推荐我们在xml中写补间动画
 *
 *          属性动画    重要特性
 *              对象属性发生真实改变  在View上的展现就是View真实被移动
 *              可控性更高
 *          原则  能用补间动画实现的，尽量使用补间动画
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    private ImageView mImage;
    private Button mTranslation;
    private Button mXY;
    private Button mRotate;
    private Button mScale;
    private Button mAlpha;
    private Button mSet;
    private Button mX;

    private int tx;
    private Button mProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.activity_main_image);
        mTranslation = (Button) findViewById(R.id.activity_main_translation);
        mXY = (Button) findViewById(R.id.activity_main_translation_xy);
        mRotate = (Button) findViewById(R.id.activity_main_rotate);
        mScale = (Button) findViewById(R.id.activity_main_scale);
        mAlpha = (Button) findViewById(R.id.activity_main_alpha);
        mSet = (Button) findViewById(R.id.activity_main_animationSet);
        mX = (Button) findViewById(R.id.activity_main_translationX);
        mProperty = (Button) findViewById(R.id.activity_main_property);

        mTranslation.setOnClickListener(this);
        mXY.setOnClickListener(this);
        mRotate.setOnClickListener(this);
        mScale.setOnClickListener(this);
        mAlpha.setOnClickListener(this);
        mImage.setOnClickListener(this);
        mSet.setOnClickListener(this);
        mX.setOnClickListener(this);
        mProperty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_translationX:
                tx += 5;

                mImage.setTranslationX(tx);

                break;
            case R.id.activity_main_property:
//                ObjectAnimator animator = ObjectAnimator.ofFloat(mImage, "translationX", -5, 5, -5, 5, 0);
//
//                animator.setDuration(2*1000);
//
//                animator.start();

                ObjectAnimator animator = ObjectAnimator.ofFloat(mImage, "rotationX", 0, 360);
                animator.setDuration(3*1000);
                animator.start();

                break;
            case R.id.activity_main_translation:
                //将动画加载进来
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.activity_mian_translation);
                //设置动画时长
                animation.setDuration(3*1000);
                //设置动画执行完成  填充
                animation.setFillAfter(true);
                //设置动画重复次数  表示重复4次，从0开始
                animation.setRepeatCount(3);
                //动画监听
                animation.setAnimationListener(this);
                //设置重复模式 1.restart（一直是从开始到结束,默认）  2.reverse反转（从开始到结束，然后再从结束到开始）
                animation.setRepeatMode(Animation.REVERSE);
                //开启动画
                mImage.startAnimation(animation);
                break;
            case R.id.activity_main_translation_xy:
                Animation animationxy = AnimationUtils.loadAnimation(this, R.anim.activity_main_translation_xy);
                animationxy.setDuration(4*1000);
                animationxy.setRepeatMode(Animation.RESTART);
                mImage.startAnimation(animationxy);
                break;
            case R.id.activity_main_rotate:
                rotateAnimation();
                break;
            case R.id.activity_main_scale:
                scaleAnimation();
                break;
            case R.id.activity_main_alpha:
                alphaAnimation();
                break;
            case R.id.activity_main_image:
                Toast.makeText(this, "你点到我了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_animationSet:
                setAnimation();
                break;
        }
    }

    private void setAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.activity_main_set);

        animation.setDuration(5*1000);

        mImage.startAnimation(animation);
    }

    private void alphaAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.activity_main_alpha);

        mImage.startAnimation(animation);
    }

    private void scaleAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.activity_main_scale);

        animation.setRepeatCount(-1);

        animation.setRepeatMode(Animation.REVERSE);

        mImage.startAnimation(animation);
    }

    private void rotateAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.activity_main_rotate);

        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);

        mImage.startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.e("TAG", "onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.e("TAG", "onAnimationEnd");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.e("TAG", "onAnimationRepeat");
    }
}
