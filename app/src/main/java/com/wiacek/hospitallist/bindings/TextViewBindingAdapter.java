package com.wiacek.hospitallist.bindings;

import android.databinding.BindingAdapter;
import android.text.util.Linkify;
import android.widget.TextView;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class TextViewBindingAdapter {
    @BindingAdapter(value = {"value", "textYes", "textNo"}, requireAll = true)
    public static void setTextViewText(TextView textView,
                                          boolean value, String textYes, String textNo) {
        textView.setText(value ? textYes : textNo);
    }

    @BindingAdapter("linkifyText")
    public static void setLinkifyText(TextView textView, String text) {
        textView.setText(text);
        Linkify.addLinks(textView, Linkify.ALL);
    }
}
