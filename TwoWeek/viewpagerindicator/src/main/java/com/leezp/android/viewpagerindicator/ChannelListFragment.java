package com.leezp.android.viewpagerindicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ChannelListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView view = new TextView(getActivity());

        view.setBackgroundColor(Color.GREEN);

        view.setText("Channel");

        return view;
    }

}
