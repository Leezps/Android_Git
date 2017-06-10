package com.leezp.android.day04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.leezp.android.day04.adapter.RecyclerAdapter;
import com.leezp.android.day04.bean.MovieBean;
import com.leezp.android.day04.bean.MovieListBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的基本使用
 *  1.添加依赖recycler
 *  2.在xml文件中声明控件 com.support.v7.widget.RecyclerView
 *  3.在java中进行findViewById初始化
 *  4.为RecyclerView设置LayoutManager
 *  5.为RecyclerView设置适配器
 *      - 创建一个类继承RecyclerView.Adapter，需要一个ViewHolder的泛型
 *      - 创建一个ViewHolder，继承自ViewHolder，并创建匹配父类的构造
 *      - 创建数据源，定义Bean类，存储所需属性和方法
 *      - 声明数据源实现方法
 *          - getItemCount()， 使用三目运算符，保证count可以正常返回
 *          - onCreateViewHolder 创建ViewHolder，根据itemView创建
 *          - onBindViewHolder 绑定ViewHolder，数据加载
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter adapter;

    private String jsnUrl = "http://app.vmoiver.com/apiv3/backstage/getPostByCate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setupView();
    }

    private void setupView() {

        //进行网络请求    数据解析    更新适配器
        RequestParams requestParams = new RequestParams(jsnUrl);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "onSuccess"+result);
                Gson gson = new Gson();
                MovieListBean movieListBean = gson.fromJson(result, MovieListBean.class);

                adapter.updateRes(movieListBean.getData());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError"+ex.getMessage()+ex.getLocalizedMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled");
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished");
            }
        });

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler);
        /**
         * Recycler必须设置一个布局管理器和适配器
         *      LayoutManager作用：控制item排列显示样式
         *
         */
        layoutManager = new LinearLayoutManager(this);
        //设置布局方向
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        GridLayoutManager layout = new GridLayoutManager(this, 2);
//        layout.setOrientation(GridLayout.HORIZONTAL);
//        mRecyclerView.setLayoutManager(layout);
        adapter = new RecyclerAdapter(getData());
        mRecyclerView.setAdapter(adapter);
    }

    private List<MovieBean> getData() {
        List<MovieBean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MovieBean movieBean = new MovieBean();
            movieBean.setTitle("Title---->"+i);
            data.add(movieBean);
        }
        return data;
    }
}
