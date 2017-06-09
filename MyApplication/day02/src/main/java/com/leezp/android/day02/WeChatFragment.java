package com.leezp.android.day02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;

/**
 * Fragment 碎片，片段
 *      Android 3.0 推出API11
 *      后来又推出兼容包 兼容到V4
 * Created by Administrator on 2017/6/8.
 */

public class WeChatFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, RatingBar.OnRatingBarChangeListener {
    private ImageView mLogo;

    /**
     *LayoutInflater    将布局导入器 打气筒，充气筒 将layout布局导入转换成View
     *
     * Bundle 数据容器，通常用来存储恢复数据的，也可以用来在Intent传值
     *
     * .cast类型转换
     */

    private int page;
    private SeekBar mSeek;
    private EditText password;
    private RatingBar mRating;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragemnt_wechat, container, false);

        mLogo = ((ImageView) layout.findViewById(R.id.teach_fragemnt_wechat_logo));
        mLogo.setOnClickListener(this);
        mSeek = ((SeekBar) layout.findViewById(R.id.teach_fragment_seekbar));
        mSeek.setOnSeekBarChangeListener(this);
        password = ((EditText) layout.findViewById(R.id.teach_fragment_wechat_password));
        mRating = ((RatingBar) layout.findViewById(R.id.teach_fragment_wechat_rating));

        mRating.setOnRatingBarChangeListener(this);


        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.teach_fragemnt_wechat_logo:
                if(page%4 == 0) {
                    mLogo.setImageResource(R.drawable.takeout_progress_loading_image_01);
                } else if(page%4 == 1) {
                    mLogo.setImageResource(R.drawable.takeout_progress_loading_image_02);
                } else if (page%4 == 2) {
                    mLogo.setImageResource(R.drawable.takeout_progress_loading_image_03);
                } else if(page%4 == 3) {
                    mLogo.setImageResource(R.drawable.takeout_progress_loading_image_04);
                }
                page++;
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e("TAG", "onProgressChanged: "+progress);
        if(progress%4 == 0) {
            mLogo.setImageResource(R.drawable.takeout_progress_loading_image_01);
        } else if(progress%4 == 1) {
            mLogo.setImageResource(R.drawable.takeout_progress_loading_image_02);
        } else if (progress%4 == 2) {
            mLogo.setImageResource(R.drawable.takeout_progress_loading_image_03);
        } else if(progress%4 == 3) {
            mLogo.setImageResource(R.drawable.takeout_progress_loading_image_04);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.e("TAG", "onStartTrackingTouch: ");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.e("TAG", "onStopTrackingTouch: ");
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Log.e("TAG", "onRatingChanged:"+rating+"password:"+password.getText().toString());
    }
}
