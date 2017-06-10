package com.leezp.android.day04.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/10.
 */

public class MovieListBean {

    private List<MovieBean> data;

    private String msg;

    public List<MovieBean> getData() {
        return data;
    }

    public void setData(List<MovieBean> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
