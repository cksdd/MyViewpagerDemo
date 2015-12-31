package com.tcl.chenkun.myviewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 修改 viewpager 绘制子view 的 z-order 顺序
 * 让当前选中页 获得最大的z－order
 * @author chenkun
 * @version 1.0 on 2015/12/2.
 */
public class AdvertiseViewPager extends ViewPager {
    public AdvertiseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setChildrenDrawingOrderEnabled(true);
    }

    public AdvertiseViewPager(Context context) {
        super(context);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int selectedIndex = -1;
        View currentView = findViewById(getCurrentItem());
        // 找到当前选中view再viewgroup中的位置
        selectedIndex = indexOfChild(currentView);
        if (selectedIndex < 0) {
            return i;
        }
        if (i < selectedIndex) {
            return i;
        } else if (i >= selectedIndex) {
            return childCount - 1 - i + selectedIndex;
        } else {
            return i;
        }
    }
}
