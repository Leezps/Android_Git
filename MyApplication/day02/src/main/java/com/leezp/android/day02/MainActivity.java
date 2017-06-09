package com.leezp.android.day02;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {

    private Button mLogin;
    private CheckBox mBall;
    private CheckBox mGame;
    private RadioGroup mGroup;

    /**
     * alt + enter 万能键修复
     * <p>
     * control + alt + space(空格) 提示键
     * <p>
     * fbc 查找控件并进行转型
     * <p>
     * .field 提取成员变量
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        mLogin = (Button) findViewById(R.id.teach_login);
        mLogin.setOnClickListener(this);

        mBall = (CheckBox) findViewById(R.id.teach_ball);
        mGame = (CheckBox) findViewById(R.id.teach_game);
        mBall.setOnCheckedChangeListener(this);
        mGame.setOnCheckedChangeListener(this);

        mGroup = (RadioGroup) findViewById(R.id.teach_radio_group);
        mGroup.setOnCheckedChangeListener(this);
    }

    /**
     * .sw 快捷生成 switch
     * <p>
     * Toast 选择创建一个Toast
     *
     * @param v
     */

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.teach_login:
                Toast.makeText(this, "你被点击了", Toast.LENGTH_SHORT).show();

                /**
                 * .var  生成局部变量
                 *
                 * 页面跳转
                 *
                 *  Intent 意图 跳转
                 *
                 */

                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * boolean      .if     生成if语句
     *
     * @param buttonView
     * @param isChecked
     */

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.teach_ball:
                if (isChecked) {
                    mLogin.setVisibility(View.INVISIBLE);
                } else {
                    mLogin.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.teach_game:
                if (isChecked) {
                    Log.e("TAG", "onCheckedChanged: 游戏开始");
                } else {
                    Log.e("TAG", "onCheckedChanged: 20投了");
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.teach_man:
                Toast.makeText(this, "你是男的", Toast.LENGTH_SHORT).show();
                break;
            case R.id.teach_women:
                Toast.makeText(this, "你是女的", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
