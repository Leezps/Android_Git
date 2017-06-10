package com.leezp.android.image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImage;
    private Button mLoad;

    private String imageUrl = "http://cs.vmoiver.com/Uploads/cover/2017-06-09/593a5ef0a44d6_cut.jpeg";
    private String jsnUrl = "http://app.vmoiver.com/apiv3/backstage/getPostByCate";
    private Button mGetJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.activity_main_image);
        mLoad = (Button) findViewById(R.id.activity_main_load);

        mLoad.setOnClickListener(this);

        mGetJson = (Button) findViewById(R.id.activity_main_json);
        mGetJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_load:
                //建造者模式，获取实例
                ImageOptions build = new ImageOptions.Builder()
                        //设置加载过程中的配置图
                        .setLoadingDrawableId(R.drawable.loading)
                        //加载失败的占位图
                        .setFailureDrawableId(R.drawable.error)
                        //设置渐变进入
                        .setFadeIn(true)
                        //设置加载图片为圆形
                        .setCircular(true)
                        .build();

                x.image().bind(mImage, imageUrl, build);
                break;
            case R.id.activity_main_json:
                //构建请求参数
                RequestParams requestParams = new RequestParams(jsnUrl);
                //发送请求，在回调中处理结果
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        //将Json数据转化成JavaBean
                        MovieListBean movieListBean = gson.fromJson(result, MovieListBean.class);
                        Log.e("TAG", "onSuccess: "+movieListBean.getMsg() );
                        List<MovieBean> data = movieListBean.getData();

                        for (MovieBean bean: data) {
                            Log.e("TAG", "onSuccess: "+bean.getTitle());
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("TAG", "onError: "+ex.getMessage()+ex.getCause()+ex.getLocalizedMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.e("TAG", "onCancelled: " );
                    }

                    @Override
                    public void onFinished() {
                        Log.e("TAG", "onFinished: ");
                    }
                });
                break;
        }
    }
}