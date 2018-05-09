package com.jacob.toolkit.toolkitforandroid.ui;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.jacob.toolkit.toolkitforandroid.mvp.presenter.BasePresenter;
import com.jacob.toolkit.toolkitforandroid.utils.LogHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author jacob
 * @date 4/11/18
 */

public abstract class BaseActivity<T extends BasePresenter> extends Activity implements LifecycleOwner {

    protected String TAG = getClass().getSimpleName();
    private LifecycleRegistry mLifecycleRegistry;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.d(TAG, "onCreate: ");
        setWindowFeature();
        setContentLayout();

        mUnbinder = ButterKnife.bind(this);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        initView();
        setListener();
        initPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogHelper.d(TAG, "onStart: ");
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogHelper.d(TAG, "onResume: ");
        mLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogHelper.d(TAG, "onPause: ");
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogHelper.d(TAG, "onDestroy: ");
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        mUnbinder.unbind();
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


    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
