package com.jacob.toolkit.toolkitforandroid.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author jacob
 * @date 5/7/18
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private static final String TAG = "CrashHandler";

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;

    /**
     * 使用Properties来保存设备的信息和错误堆栈信息
     */
    private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "stackTrace";
    private static final String EXCEPTION = "exception";

    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";


    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }


    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param throwable
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable throwable) {
        if (throwable == null) {
            LogHelper.e(TAG, "handleException ---> throwable==null");
            return true;
        }
        final String msg = throwable.getLocalizedMessage();
        if (TextUtils.isEmpty(msg)) {
            return false;
        }
        LogHelper.e(TAG, "异常信息->" + msg);
        collectCrashDeviceInfo(mContext);
        saveCrashInfoToFile(throwable);
        return true;
    }


    /**
     * 保存错误信息到文件中
     */
    private void saveCrashInfoToFile(Throwable throwable) {

        StringWriter info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String result = info.toString();
        printWriter.close();
        mDeviceCrashInfo.put(EXCEPTION, throwable.getLocalizedMessage());
        mDeviceCrashInfo.put(STACK_TRACE, result);
        try {
            String fileName = "crash-" + getCurrentTime() + CRASH_REPORTER_EXTENSION;
            FileOutputStream trace = mContext.openFileOutput(fileName,Context.MODE_PRIVATE);
            mDeviceCrashInfo.store(trace, "save crash info");
            trace.flush();
            trace.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 收集程序崩溃的设备信息
     */
    public void collectCrashDeviceInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put(VERSION_CODE, "" + pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), "" + field.get(null));
                LogHelper.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /***************************************************************************************************/

    private static CrashHandler INSTANCE;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (CrashHandler.class) {

                if (INSTANCE == null) {
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }
}
