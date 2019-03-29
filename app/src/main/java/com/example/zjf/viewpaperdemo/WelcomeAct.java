package com.example.zjf.viewpaperdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Time;

public class WelcomeAct extends Activity {

    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    //定义Handler：首次启动进入引导页，之后启动直接进入主页面
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME: {
                    goHome();
                }
                break;
                case GO_GUIDE: {
                    goGuide();
                }
                break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome);
        init();
    }

    private void init() {
        //使用SharedPreferences记录状态
        SharedPreferences pref = getSharedPreferences("zjf", MODE_PRIVATE);
        isFirstIn = pref.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        }
        else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            //存储数据
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }
    }

    //跳转主页面
    private void goHome() {
        Intent i = new Intent(WelcomeAct.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    //跳转引导页面
    private void goGuide() {
        Intent i = new Intent(WelcomeAct.this, Guide.class);
        startActivity(i);
        finish();
    }
}
