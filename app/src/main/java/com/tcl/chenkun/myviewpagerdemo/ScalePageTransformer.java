package com.tcl.chenkun.myviewpagerdemo;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 设置viewpager 页面切换的动画
 * @author chenkun
 * @version 1.0 on 2015/12/1.
 */
public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 1f;
    public static final float MIN_SCALE = 0.6f;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;
        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
