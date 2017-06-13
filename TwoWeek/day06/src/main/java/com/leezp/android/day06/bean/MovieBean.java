package com.leezp.android.day06.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class MovieBean {

    private String postid;

    private String title;

    private String image;

    private int duration;

    private List<MovieInfoBean> cates;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<MovieInfoBean> getCates() {
        return cates;
    }

    public void setCates(List<MovieInfoBean> cates) {
        this.cates = cates;
    }
}
