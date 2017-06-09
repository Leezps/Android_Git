package com.leezp.android.day03;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/6/9.
 */

public class GuideThree extends Fragment implements View.OnClickListener {

    private View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_guide_three, container, false);

        ImageView mImage = (ImageView) layout.findViewById(R.id.fragment_guide_three_go_second);
        mImage.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_guide_three_go_second:
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
                //结束当前页面
                getActivity().finish();
                break;
        }
    }
}
