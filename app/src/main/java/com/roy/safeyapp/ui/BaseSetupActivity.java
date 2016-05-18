package com.roy.safeyapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Roy Sun on 2016/5/18.
 */
public abstract class BaseSetupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        // 初始化数据
        initData();
        // 初始化事件
        initEvent();
    }

    public void initEvent() {

    }

    // 有的界面不需要 就不是abstract
    public void initData() {

    }

    protected abstract void initView();


    // 抽取的按钮点击事件
    public void next(View view) {
        nextActivity();

        nextAnimation();
    }


    // 抽取的按钮点击事件
    public void prev(View view) {
        prevActivity();

        nextAnimation();
    }

    // 转场动画 用的系统提供的动画
    public void nextAnimation() {
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }


    public void start(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }


    protected abstract void nextActivity();

    protected abstract void prevActivity();
}
