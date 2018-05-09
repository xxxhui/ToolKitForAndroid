package com.jacob.toolkit.toolkitforandroid.mvp.presenter;

import com.jacob.toolkit.toolkitforandroid.ui.BaseActivity;

/**
 * @author: Jacob
 * @date: 2018/5/8
 */
public abstract class BasePresenter<T extends BaseActivity> {
    protected String TAG = getClass().getSimpleName();
}
