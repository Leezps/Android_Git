package com.leezp.android.day06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MovieDetailActivity extends AppCompatActivity {

    private WebView mWeb;

    private String WEB_URL = "http://app.vmoiver.com/%s?qingapp=app_new";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String postid = intent.getStringExtra("PostId");
        mWeb = (WebView) findViewById(R.id.activity_movie_detail_web);

        //需要打开JavaScript功能
        mWeb.getSettings().setJavaScriptEnabled(true);
        //为WebView设置自己的浏览客户端
        mWeb.setWebViewClient(new WebViewClient());
        //加载进度以及JS调用监控的客户端
        mWeb.setWebChromeClient(new WebChromeClient());

        mWeb.loadUrl(String.format(WEB_URL, postid));
    }
}
