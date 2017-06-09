package com.leezp.android.myapplication_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Activity 简单理解为界面
 *
 * AndroidManifest 清单文件
 * 注册页面
 * 权限声明
 *
 * 源文件 src/main
 *      java java代码，通常都是控制业务逻辑的代码
 *      res resource资源文件，存放布局，字符串，颜色，图片
 *      AndroidManifest 清单文件
 *
 *      View 安卓显示单元
 *
 *      View的体系
 *          View 基本显示单元
 *          ViewGroup 显示一组View,也是View的子类
 *          AdapterView 适配器类型View
 *
 *       ViewGroup 常用的有三种
 *          LinearLayout 线性布局
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
