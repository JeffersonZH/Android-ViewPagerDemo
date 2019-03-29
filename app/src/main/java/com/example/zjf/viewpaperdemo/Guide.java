package com.example.zjf.viewpaperdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Guide extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3};
    private Button start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.guide);

        initViews();

        initDots();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one, null));
        views.add(inflater.inflate(R.layout.two, null));
        views.add(inflater.inflate(R.layout.three, null));

        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);

        //设置对pager的监听:setOnPageChangeListener已经Deprecated，建议使用addOnPageChangeListener
        //vp.setOnPageChangeListener(this);
        vp.addOnPageChangeListener(this);

        //初始化
        start_btn = views.get(2).findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Guide.this, MainActivity.class);
                startActivity(i);
                //该Activity结束，并关闭该Activity
                finish();
            }
        });
    }

    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    //滑动每个pager后调用
    @Override
    public void onPageSelected(int i) {
        for (int j = 0; j < ids.length; j++) {
            if (j == i) {
                dots[j].setImageResource(R.drawable.login_point_selected);
            }
            else {
                dots[j].setImageResource(R.drawable.login_point);
            }
        }
    }


    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
