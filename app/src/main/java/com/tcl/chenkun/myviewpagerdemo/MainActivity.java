package com.tcl.chenkun.myviewpagerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity {

    private static final int NEXT_IMG = 1;
    int[] Imgs = {R.mipmap.a, R.mipmap.b, R.mipmap.a, R.mipmap.b,};

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int nextItem = advertiseViewPager.getCurrentItem()+1;
            advertiseViewPager.setCurrentItem(nextItem);
            sendEmptyMessageDelayed(NEXT_IMG,4000);
        }
    };
    private AdvertiseViewPager advertiseViewPager;
    private View pagerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        advertiseViewPager = (AdvertiseViewPager) findViewById(R.id.vp);
        advertiseViewPager.setOffscreenPageLimit(3);
        initPagerScrollSpeed();
        advertiseViewPager.setAdapter(new MyVipagerAdapter());
        advertiseViewPager.setCurrentItem(Integer.MAX_VALUE / 2);

        pagerContainer = findViewById(R.id.rl_pager_container);
        pagerContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return advertiseViewPager.dispatchTouchEvent(event);
            }
        });

        advertiseViewPager.setPageTransformer(true, new ScalePageTransformer());
        advertiseViewPager.setPageMargin(-200);
        handler.sendEmptyMessageDelayed(NEXT_IMG,4000);


    }
    /**
     * 修改 viewpager 页面的切换速度
     */
    private void initPagerScrollSpeed() {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(this);
            mScroller.set(advertiseViewPager, scroller);
        } catch (Exception e) {

        }
    }


    private class MyVipagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            container.addView(imageView);
            imageView.setImageResource(Imgs[position % Imgs.length]);
            imageView.setId(position);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


    }

}
