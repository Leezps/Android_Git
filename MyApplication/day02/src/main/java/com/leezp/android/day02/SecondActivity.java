package com.leezp.android.day02;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SecondActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mController;
    private RadioButton wechat;
    private FragmentManager fragmentManager;
    private Fragment wechatFragment;
    private Fragment maillistFragment;
    private Fragment findFragment;
    private Fragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        initView();
    }

    private void initView() {
        mController = (RadioGroup) findViewById(R.id.teach_controller);
        mController.setOnCheckedChangeListener(this);
        /**
         * Fragment的动态加载
         * 1.使用一个碎片管理器进行管理
         * 2.碎片管理器在操作一个碎片的时候需要开启事务
         * 3.在事务中添加自己的操作
         * 4.提交事务
         */

        fragmentManager = getSupportFragmentManager();

        //第一个按钮设置成选中状态
        wechat = (RadioButton) findViewById(R.id.teach_second_wechat);
        wechat.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);

        switch (checkedId) {
            case R.id.teach_second_wechat:
                if (wechatFragment == null) {
                    wechatFragment = new WeChatFragment();
                    transaction.add(R.id.teach_container, wechatFragment);
                } else {
                    transaction.show(wechatFragment);
                }
                break;
            case R.id.teach_second_mail_list:
                if (maillistFragment == null) {
                    maillistFragment = new MailListFragment();
                    transaction.add(R.id.teach_container, maillistFragment);
                } else {
                    transaction.show(maillistFragment);
                }
                break;
            case R.id.teach_second_find:
                if(findFragment == null) {
                    findFragment = new FindFragment();
                    transaction.add(R.id.teach_container, findFragment);
                } else {
                    transaction.show(findFragment);
                }
                break;
            case R.id.teach_second_mine:
                if(mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.teach_container, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (wechatFragment != null) {
            transaction.hide(wechatFragment);
        }
        if (maillistFragment != null) {
            transaction.hide(maillistFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if(mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }
}
