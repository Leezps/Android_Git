package com.leezp.android.day07;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

/**
 * 做一个简单视频播放
 *      VideoView
 *      MedioPlayer
 *
 *      c做的编程
 *          ijkplayer
 *          vitamio
 *
 *      FFMPEG 雷晓华 专门处理音视频的库
 *
 *      使用VideoView进行视频播放
 *          1.xml文件中声明控件
 *          2.java代码中数据实例化控件
 *
 */

public class MainActivity extends AppCompatActivity implements Handler.Callback, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static final int UPDATE_TIME = 1001;
    private VideoView mVideo;
    private CheckBox mPlay;
    private CheckBox mFullScreen;
    private TextView mCurrentTime;
    private TextView mTotalTime;
    private SeekBar mProgress;

    private Handler handler = new Handler(this);

    private String VIDEO_PATH="http://42.123.114.113/xdispatch/7rflo2.com2.z0.glb.qiniucdn.com/5714b0b53c958.mp4";
    private FrameLayout mVideoContainer;
    private int mContainerHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVideoContainer = (FrameLayout) findViewById(R.id.activity_main_video_container);
        mVideo = (VideoView) findViewById(R.id.activity_main_video);
        mPlay = (CheckBox) findViewById(R.id.activity_main_play);
        mFullScreen = (CheckBox) findViewById(R.id.activity_main_full_screen);
        mCurrentTime = (TextView) findViewById(R.id.activity_main_current_time);
        mTotalTime = (TextView) findViewById(R.id.activity_main_total_time);
        mProgress = (SeekBar) findViewById(R.id.activity_main_pregress);

        mPlay.setOnCheckedChangeListener(this);
        mProgress.setOnSeekBarChangeListener(this);
        mFullScreen.setOnCheckedChangeListener(this);
        
        //设置一个播放地址
//        mVideo.setVideoPath(VIDEO_PATH);
        //android.resource://报名/资源id
        mVideo.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.dance));
        //设置一个播放的媒体控制器
//        mVideo.setMediaController(new MediaController(this));
        //调用播放
//        mVideo.start();
        //加载资源，加载完成了，准备好了
        mVideo.setOnPreparedListener(this);
        //视频播放完成，默认调用此下监听器，结束更新播放时间的handler
        mVideo.setOnCompletionListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.activity_main_play:
                if (isChecked) {
                    mVideo.pause();
                    handler.removeMessages(UPDATE_TIME);
                } else {
                    mVideo.start();
                    handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                }
                break;
            case R.id.activity_main_full_screen:
                ViewGroup.LayoutParams layoutParams = mVideoContainer.getLayoutParams();
                if (isChecked) {
                    mContainerHeight = mVideoContainer.getLayoutParams().height;
                    //切换到横屏模式   添加一个全屏的标记
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    //请求横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //设置高度为match_parent
                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                    mVideoContainer.setLayoutParams(layoutParams);
                } else {
                    //切换到默认模式   清除全屏标记
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    //请求纵屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    //还原高度
                    layoutParams.height = mContainerHeight;
                    mVideoContainer.setLayoutParams(layoutParams);
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.activity_main_pregress:
                if (fromUser) {
                    //将视频移动到指定位置进行播放
                    mVideo.seekTo(progress);
                }
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATE_TIME:
                //更新事件
                int currentPosition = mVideo.getCurrentPosition();
                mCurrentTime.setText(android.text.format.DateFormat.format("mm:ss", currentPosition));
                mProgress.setProgress(currentPosition);

                handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                break;
        }
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //加载完成，准备完成，调用播放
        mVideo.start();
        //发送消息，通知当前时间进行更改 播放进度更新
        handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
        //获取视频总时长
        int duration = mVideo.getDuration();
        mProgress.setMax(duration);
        //设置总时间
        mTotalTime.setText(DateFormat.format("mm:ss", duration));
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        handler.removeMessages(UPDATE_TIME);
    }
}
