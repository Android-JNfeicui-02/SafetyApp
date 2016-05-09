package com.roy.safeyapp.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.roy.safeyapp.R;

import java.util.ArrayList;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public static final String SPLASH_CONFIG = "splash_config";
    private ViewPager mViewPager;

    private ArrayList<View> mList;

    int[] pics = {R.mipmap.adware_style_creditswall, R.mipmap.adware_style_banner,
                  R.mipmap.adware_style_applist};

    private Button mBtnSkip;

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断是否是第一次运行程序
        SharedPreferences preferences = getSharedPreferences(SPLASH_CONFIG, MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);

        if (!isFirstRun) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_splash);
            initView();
        }


    }

    // 保存第一次运行的SP
    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences(SPLASH_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstRun", false);
        editor.apply();
    }

    private void initView() {

        mBtnSkip = (Button) findViewById(R.id.btn_skip);
        mBtnSkip.setOnClickListener(this);

        mList = new ArrayList<>();

        mViewPager = (ViewPager) findViewById(R.id.vp_guide);

        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(pics[i]);
            mList.add(iv);
        }

        mViewPager.setAdapter(new MyPagerAdapter(mList));

        mViewPager.addOnPageChangeListener(this);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    // 正在滚动的时候 调用的方法 会反复调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG,
              "onPageScrolled: start" + "position:" + position + "  offset" + positionOffset + "" +
                      "  pixels:" + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {

        if (position == 2) {
            mBtnSkip.setVisibility(View.VISIBLE);
        } else {
            mBtnSkip.setVisibility(View.INVISIBLE);
        }

        Log.d(TAG, "onPageSelected: start,  position:" + position);
    }

    // 当viewpager在滚动的时候 调用的第一个方法
    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "onPageScrollStateChanged: start,  state: " + state);
    }

    // 官方提供的动画1
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    // 官方提供的动画2
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                                      (scaleFactor - MIN_SCALE) /
                                              (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }


    // 按钮的跳转方法
    @Override
    public void onClick(View v) {
        savePreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private class MyPagerAdapter extends PagerAdapter {

        private ArrayList<View> mList;

        public MyPagerAdapter(ArrayList<View> list) {
            mList = list;
        }


        // 初始化position 展现到界面上来
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position), 0);
            return mList.get(position);
        }

        // 当不可见时 销毁position
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public int getCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
