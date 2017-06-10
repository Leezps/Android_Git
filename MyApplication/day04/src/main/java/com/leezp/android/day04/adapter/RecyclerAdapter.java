package com.leezp.android.day04.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leezp.android.day04.R;
import com.leezp.android.day04.bean.MovieBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/10.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<MovieBean> data;

    //图片加载 暂定
    ImageOptions build = new ImageOptions.Builder()
            .setLoadingDrawableId(R.drawable.loading)
            .setFailureDrawableId(R.drawable.error)
            .setFadeIn(true)
            .setCircular(true)
            .build();

    public RecyclerAdapter(List<MovieBean> data) {
        this.data = data;
    }

    public void updateRes(List<MovieBean> data) {
        //清除原有数据
        this.data.clear();
        //添加新的数据
        this.data.addAll(data);
        //通知适配器数据刷新了
        notifyDataSetChanged();
    }

    /**
     * 创建ViewHolder,实例化ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //使用布局导入器导入itemView的布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);

        //使用item布局创建ViewHolder
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    /**
     * 绑定ViewHolder数据加载
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieBean movieBean = data.get(position);

        //文字加载
        holder.title.setText(movieBean.getTitle());

        x.image().bind(holder.image, movieBean.getImage(),build);
    }

    /**
     * 获取数据源的条目数
     */
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.activity_main_item_img);

            title = (TextView) itemView.findViewById(R.id.activity_main_item_title);
        }
    }

}
