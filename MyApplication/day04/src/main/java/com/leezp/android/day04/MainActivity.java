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
 *
 * 上周知识简单回顾
 *      Day01
 *          搭建AndroidStudio开发环境
 *          HelloWorld
 *              View体系
 *              ViewGroup   layout_gravity
 *                  LinearLayout
 *                      layout_weight
 *                  RelativeLayout
 *                      使用场景    强调相对关系
 *                          - 相对父控件
 *                          - 相对于同级控件
 *                  FrameLayout
 *                      层叠排放
 *              高级复合控件
 *      Day02
 *          View
 *              TextView
 *              Button
 *              EditText
 *              CheckBox
 *              RadioButton     RadioGroup
 *              SeekBar
 *              ProgressBar
 *              RatingBar
 *              ImageView
 *          Fragment    碎片、片段
 *              Android 3.0 API 11
 *                  onCreateView    创建Fragment的布局
 *                      LayoutInflater  布局导入器（充气筒）
 *                      将xml布局导入转换成View进行一个返回
 *              Fragment动态加载
 *                  需要一个FragmentManager 碎片管理者
 *                  开启事务    FragmentTransaction
 *                  在事务中添加动作    （操作Fragment）    add hide show/ replace
 *                  提交事务
 *              底部导航    RadioGroup+Fragment
 *                  RadioGroup  -> LinearLayout 子类  权重  方向  都能直接使用
 *                              -> 单选   选中的Child未发生改变   不会重复回调
 *                              -> 使用Selector   定制样式
 *                                  选择器 根据不同的状态 设置不同的资源
 *                                      状态成对出现  资源还要进行匹配
 *                  Drawable left right top bottom
 *      Day03
 *          ViewPager
 *              高级复合控件  它的显示需要适配器进行配合
 *              Fragment与ViewPager进行配合
 *                  PagerAdapter
 *                      FragmentPagerAdapter    页面不会彻底销毁，只会销毁View
 *                      FragmentStatePagerAdapter   页面彻底销毁，当嵌套时候，页面刷新不出来，优先使用
 *              页面跳转    Intent
 *                  -context 场景
 *                  -目标页面的Class(Activity)
 *              Fragment中的控件初始化以及监听
 *                  需要使用我们的布局初始化    layout.findViewById
 *              关闭Activity，跳转之后关闭不想继续回退的页面
 *
 *          双击退出
 *              监听返回按键 onKeyDown
 *                  按下返回按钮 -> 提示再按一次退出 -> 改变标记位，变为可退出状态，设置一个定时任务（用来还原标记位）
 *                               -> 自己处理任务的时候，记得返回True
 *          TabLayout
 *              design  API22 Android5.1推出
 *                  TabLayout 导航条，通常结合ViewPager进行使用
 *                  TabLayout   有一个模式属性 scrollable 可以拉伸标题长度 fixed 默认填满
 *                  与ViewPager结合使用
 *                      TabLayout调用setupWithViewPager
 *                      ViewPager的PagerAdapter实现getPagerTitle方法
 *          Fragment传值
 *              setArguments    向Fragment中传值，这个Fragment是没有加载过得
 *              getArguments    获取传递过来的值
 *          逐帧动画    frame-by-frame
 *              在drawable创建一个animation-list，每一帧就是一个item,drawable指定当前帧的图片，duration当前帧存在的时间
 *              设置drawable为背景，获取出来转换AnimationDrawable，然后start
 *      Day04
 *          RecyclerView 一个比ListView，GridView更加灵活更加高效的控件
 *              RecyclerView - LayoutManager -adapte
 *              Adapter需要ViewHolder的泛型
 *          xUtils  快速开发框架  中国人写的
 *              View    注入
 *              image   图片加载
 *              http    网络请求
 *              orm     数据库
 *              使用框架的注意事项
 *                  - 初始化权限
 *                      - 权限配置
 *                      - 在使用前进行代码初始化，通常框架的初始化都是在Application中
 *          image模块
 *              ImageView   资源路径(可以是网络的，也可以是本地的)
 *              如果对样式有需求的话，我们可以指定额外的参数 ImageOptions
 *          http 模块
 *              RequestParams 请求参数  包含请求地址和自己的配置参数
 *              请求回调    CommonCallBack<String>
 *                              -onSuccess  成功
 *                              -onError    错误
 *                              -onFinished 完成  一定会走
 *                              -onCancelled 取消
 *                          如果即走了成功，又走了错误
 *                              那么你的错误一定是在onSuccess中的代码出了问题
 *
 *          动态刷新
 *              在获取到数据之后，将数据传递到适配器中，并通知适配器刷新
 *          梳理流程
 *              确认数据是否请求回来
 *              确认解析是否成功
 *              确认数据是否传递到适配中
 *              确认适配器是同一个
 *
 *              打印日志 debug
 *
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
