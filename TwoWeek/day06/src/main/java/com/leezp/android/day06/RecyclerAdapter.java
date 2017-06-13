package com.leezp.android.day06;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.leezp.android.day06.bean.MovieBean;
import com.leezp.android.day06.bean.MovieInfoBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private List<MovieBean> data;

    private ImageOptions build;
    private RecyclerView mRecyclerView;

    public RecyclerAdapter(List<MovieBean> data) {
        this.data = data;
    }

    //刷新数据源
    public void updateAdapter(List<MovieBean> data) {
        if (this.data != null) {
            this.data.addAll(data);
        } else {
            this.data = new ArrayList<>();
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        build = new ImageOptions.Builder()
                .setFailureDrawableId(R.drawable.error)
                .setLoadingDrawableId(R.drawable.loading)
                .setAnimation(AnimationUtils.loadAnimation(parent.getContext(), R.anim.scale_in))
                .build();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieBean movieBean = data.get(position);
        List<MovieInfoBean> cates = movieBean.getCates();

        holder.title.setText(movieBean.getTitle());
        holder.info.setText(String.format("%s / %d'%d''", cates.get(0).getCatename(), movieBean.getDuration()/60, movieBean.getDuration()%60));

        x.image().bind(holder.image, movieBean.getImage(), build);

        //添加监听
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    //依附在Recycler上时进行调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        int position = mRecyclerView.getChildAdapterPosition(v);

        String postid = data.get(position).getPostid();
        //页面跳转 将postid传递到下一个页面
        Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
        intent.putExtra("PostId", postid);
        v.getContext().startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public TextView title;

        public TextView info;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.recycler_item_image);
            title = (TextView) itemView.findViewById(R.id.recycler_item_title);
            info = (TextView) itemView.findViewById(R.id.recycler_item_info);
        }
    }

}
