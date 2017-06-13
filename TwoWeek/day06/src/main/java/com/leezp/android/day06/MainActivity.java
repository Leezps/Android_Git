package com.leezp.android.day06;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.leezp.android.day06.bean.MovieBean;
import com.leezp.android.day06.bean.MovieListBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * RecyclerView
 *      1.添加依赖
 *      2.在xml中声明
 *      3.在java中进行初始化findViewById
 *      4.需要设置布局管理器和适配器
 *      5.布局管理器可以用代码设置，也可以在xml中设置
 *          app:layoutManager   自定义属性
 *      6.设置适配器
 *          - 创建一个适配器，继承自RecyclerView.Adapter<VH extends ViewHolder>
 *          - 创建一个ViewHolder 继承自RecyclerView.ViewHolder
 *          - 分析数据，创建数据源
 *          - 实现方法，绑定适配器
 */

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecycler;
    private RecyclerAdapter adapter;

    private String movieUrl = "http://app.vmoiver.com/apiv3/post/getPostByTab";
    private SwipeRefreshLayout mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.activity_main_recycler);
        //设置布局管理器
//        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        //设置适配器
        adapter = new RecyclerAdapter(getData());
        mRecycler.setAdapter(adapter);

        mRefresh = (SwipeRefreshLayout) findViewById(R.id.activity_main_refresh);
        //设置刷新颜色
        mRefresh.setColorSchemeResources(R.color.colorRed, R.color.colorBlue, R.color.colorYellow);
        //设置刷新监听
        mRefresh.setOnRefreshListener(this);
    }

    private List<MovieBean> getData() {

        MovieListBean movieListBean = new MovieListBean();

        /**
         * xUtils http模块
         *      1.构建一个RequestParams
         *      2.调用http模块将请求发送出去
         */
        RequestParams requestParams = new RequestParams(movieUrl);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                //数据解析
                Gson gson = new Gson();
                MovieListBean bean = gson.fromJson(result, MovieListBean.class);
                //刷新适配器
                adapter.updateAdapter(bean.getData());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG",ex.getMessage()+ex.getLocalizedMessage()+ex.getCause());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled");
            }

            @Override
            public void onFinished() {
                Log.e("TAG","数据已发送回来！");
                //设置刷新结束
                mRefresh.setRefreshing(false);
            }
        });

        return movieListBean.getData();
    }

    @Override
    public void onRefresh() {
        //请求最新的网络数据
        getData();
    }
}
