package com.jacob.toolkit.toolkitforandroid.utils;

import android.util.Log;

/**
 * @author jacob
 * @date 4/11/18
 */

public class LogHelper {

    private static final int CURRENT_LEVEL = -1;

    private static final int V_LEVEL = 1;
    private static final int D_LEVEL = 2;
    private static final int I_LEVEL = 3;
    private static final int W_LEVEL = 4;
    private static final int E_LEVEL = 5;

    public static void v(String TAG, String message) {
        if (V_LEVEL > CURRENT_LEVEL) {
            Log.v(TAG, message);
        }
    }

    public static void d(String TAG, String message) {
        if (D_LEVEL > CURRENT_LEVEL) {
            Log.d(TAG, message);
        }
    }

    public static void i(String TAG, String message) {
        if (I_LEVEL > CURRENT_LEVEL) {
            Log.i(TAG, message);
        }
    }

    public static void w(String TAG, String message) {
        if (W_LEVEL > CURRENT_LEVEL) {
            Log.w(TAG, message);
        }
    }

    public static void e(String TAG, String message) {
        if (E_LEVEL > CURRENT_LEVEL) {
            Log.e(TAG, message);
        }
    }
}
