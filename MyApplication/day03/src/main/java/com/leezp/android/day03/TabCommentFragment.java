package com.leezp.android.day03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2017/6/9.
 */

public class TabCommentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button button = new Button(getActivity());
        //获取数据
        Bundle bundle = getArguments();

        String content = bundle.getString("content");

        button.setText(content);

        return button;
    }

}
