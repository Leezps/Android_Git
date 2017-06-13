package com.leezp.android.day06.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class MovieListBean {

    private String msg;

    private List<MovieBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<MovieBean> getData() {
        return data;
    }

    public void setData(List<MovieBean> data) {
        this.data = data;
    }
}
