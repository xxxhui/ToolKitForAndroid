package com.jacob.toolkit.toolkitforandroid;

import android.app.Application;
import android.content.Context;

import com.jacob.toolkit.toolkitforandroid.utils.CrashHandler;

/**
 * @author: Jacob
 * @date: 2018/5/6
 */
public class JacobApplication extends Application {

    private static Application instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashHandler.getInstance().init(getContext());
    }

}
