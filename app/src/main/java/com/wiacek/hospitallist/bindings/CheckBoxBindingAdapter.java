package com.wiacek.hospitallist.bindings;

import android.databinding.BindingAdapter;
import android.widget.CheckBox;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class CheckBoxBindingAdapter {
    @BindingAdapter("android:checked")
    public static void setCheckBoxChecked(CheckBox checkBox,
                                          boolean checked) {
        checkBox.setChecked(checked);
    }
}
