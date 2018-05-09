package com.jacob.toolkit.toolkitforandroid.mvp.presenter.js;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jacob.toolkit.toolkitforandroid.mvp.presenter.BasePresenter;
import com.jacob.toolkit.toolkitforandroid.mvp.view.IJsView;
import com.jacob.toolkit.toolkitforandroid.ui.activity.JsActivity;

/**
 * @author: Jacob
 * @date: 2018/5/2
 */
public class JsPresenter extends BasePresenter<JsActivity> {

    private IJsView mIJsView;
    private JsInterface mJsInterface;
    private WebView mWebView;

    public JsPresenter(IJsView mIJsView, JsInterface mJsInterface) {
        this.mIJsView = mIJsView;
        this.mJsInterface = mJsInterface;
        mWebView = mIJsView.getWebView();
    }

    public void start() {
        initWebView();
        mWebView.loadUrl("file:///android_asset/js.html");
    }

    public void release() {
        mWebView.loadUrl(null);
        mWebView.destroy();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.addJavascriptInterface(mJsInterface, "JavaAndJs");
    }


    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    };

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    };
}
