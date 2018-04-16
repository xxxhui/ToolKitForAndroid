package com.jacob.toolkit.toolkitforandroid.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.jacob.toolkit.toolkitforandroid.util.LogUtil;


/**
 * @author jacob
 * @date 4/11/18
 */

public abstract class BaseActivity extends Activity {
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "onCreate: ");
        setWindowFeature();
        initPresenter();
        setContentLayout();
        initView();
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        LogUtil.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    /**
     * init views
     */
    protected abstract void initView();

    /**
     * add event listeners
     */
    protected abstract void setListener();

    /**
     * set layout xml file
     */
    protected abstract void setContentLayout();

    /**
     * init presenters
     */
    protected abstract void initPresenter();

    protected void setWindowFeature() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
