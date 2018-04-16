package com.jacob.toolkit.toolkitforandroid.util;

import android.util.Log;

/**
 * @author jacob
 * @date 4/11/18
 */

public class LogUtil {

    private static final int CURRENT_LEVEL = -1;

    private static final int V_LEVEL = 1;
    private static final int D_LEVEL = 2;
    private static final int I_LEVEL = 3;
    private static final int W_LEVEL = 4;
    private static final int E_LEVEL = 5;


    /**
     * ----------------------normal log--------------------------------
     **/
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

    /**
     * @return the class name of the caller
     */
    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    /**
     * ----------------------log with caller class as default TAG--------------------------------
     **/
    public static void v1(String message) {
        if (V_LEVEL > CURRENT_LEVEL) {
            Log.v(getTag(), message);
        }
    }

    public static void d1(String message) {
        if (D_LEVEL > CURRENT_LEVEL) {
            Log.d(getTag(), message);
        }
    }

    public static void i1(String message) {
        if (I_LEVEL > CURRENT_LEVEL) {
            Log.i(getTag(), message);
        }
    }

    public static void w1(String message) {
        if (W_LEVEL > CURRENT_LEVEL) {
            Log.w(getTag(), message);
        }
    }

    public static void e1(String message) {
        if (E_LEVEL > CURRENT_LEVEL) {
            Log.e(getTag(), message);
        }
    }

    private static String getFunctionName() {
        StackTraceElement[] sts = new Throwable().fillInStackTrace().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtil.class.getName())) {
                    continue;
                }
                return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName() + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }

    private static String getMsgFormat(String message) {
        return message + " <-------> " + getFunctionName();
    }

    /**
     * ----------------------log with track information--------------------------------
     **/
    public static void v2(String message) {
        if (V_LEVEL > CURRENT_LEVEL) {
            Log.v(getTag(), getMsgFormat(message));
        }
    }

    public static void d2(String message) {
        if (D_LEVEL > CURRENT_LEVEL) {
            Log.d(getTag(), getMsgFormat(message));
        }
    }

    public static void i2(String message) {
        if (I_LEVEL > CURRENT_LEVEL) {
            Log.i(getTag(), getMsgFormat(message));
        }
    }

    public static void w2(String message) {
        if (W_LEVEL > CURRENT_LEVEL) {
            Log.w(getTag(), getMsgFormat(message));
        }
    }

    public static void e2(String message) {
        if (E_LEVEL > CURRENT_LEVEL) {
            Log.e(getTag(), getMsgFormat(message));
        }
    }

}
