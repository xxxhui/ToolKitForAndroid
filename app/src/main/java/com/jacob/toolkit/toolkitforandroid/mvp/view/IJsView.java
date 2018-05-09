package com.jacob.toolkit.toolkitforandroid.mvp.view;

import android.webkit.WebView;

/**
 * @author: Jacob
 * @date: 2018/5/8
 */
public interface IJsView {

    void showToast(String msg);

    WebView getWebView();

    void onPageStarted();

    void onPageFinished();
}
