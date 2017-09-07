package com.wiacek.hospitallist.bindings;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ViewBindingAdapter {
    @BindingAdapter("visibility")
    public static void setViewVisibilityByString(View view,
                                          String text) {
        view.setVisibility((text == null || text.isEmpty()) ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter("android:visibility")
    public static void setViewVisibilityByBoolean(View view,
                                         boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }
}
