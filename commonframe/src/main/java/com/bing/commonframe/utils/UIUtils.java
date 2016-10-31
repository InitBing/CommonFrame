package com.bing.commonframe.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Zn on 2015/11/10 0010.
 */
public class UIUtils {


    public static void hideKeyBoard(Context ctx, View view) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getApplicationContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyBoard(Context ctx, EditText view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) ctx
                .getApplicationContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getScreenSize(Context context, boolean widthB) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        if (widthB) {
            return metrics.widthPixels;
        } else {
            return metrics.heightPixels;
        }
    }
}
