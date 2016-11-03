package com.bing.android.utils;

/**
 * Created by Administrator on 2015/11/16 0016.
 */
/*
 * author: Spring, email: ming.zhang@bitbao.net version: 2011-9-1815:09:28
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * The Class CheckHttpUtil.
 */
public class CheckHttpUtil {

    /**
     * Check http state.
     *
     * @param context
     *            the context
     * @return true, if successful
     */
    public static boolean isNetworkConnected(Context context) {
        if (context == null)
            return false;
        Context gContext = context.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) gContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

    public static boolean checkHttpState(Context context) {
        return isNetworkConnected(context);
    }

    public static boolean is2gOr3g(Context context) {
        Context gContext = context.getApplicationContext();
        ConnectivityManager mConnec = (ConnectivityManager) gContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = NetworkInfo.State.UNKNOWN;
        NetworkInfo networkInfo = mConnec
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            state = networkInfo.getState();
            if (state == NetworkInfo.State.CONNECTED)
                return true;
        }
        return false;
    }

    public static boolean isWifi(Context context) {
        Context gContext = context.getApplicationContext();
        ConnectivityManager mConnec = (ConnectivityManager) gContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = NetworkInfo.State.UNKNOWN;
        NetworkInfo networkInfo = mConnec
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null) {
            state = networkInfo.getState();
            if (state == NetworkInfo.State.CONNECTED)
                return true;
        }
        return false;
    }
}
