package com.jacob.toolkit.toolkitforandroid.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.jacob.toolkit.toolkitforandroid.R;
import com.jacob.toolkit.toolkitforandroid.entity.HeadCommand;
import com.jacob.toolkit.toolkitforandroid.mvp.presenter.js.JsInterface;
import com.jacob.toolkit.toolkitforandroid.mvp.presenter.js.JsPresenter;
import com.jacob.toolkit.toolkitforandroid.mvp.view.IJsView;
import com.jacob.toolkit.toolkitforandroid.ui.BaseActivity;
import com.jacob.toolkit.toolkitforandroid.utils.LogHelper;
import com.jacob.toolkit.toolkitforandroid.utils.ToastUtil;


/**
 * @author: Jacob
 * @date: 2018/5/8
 */
public class JsActivity extends BaseActivity<JsPresenter>{

    private static final int JAVA_TO_JS = 11;
    private static final String JSON_STRING = "jsonString";

    private WebView mWebView;
    private JsPresenter mJsPresenter;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case JAVA_TO_JS:
                    String jsonToJs = msg.getData().getString(JSON_STRING);
                    String url2Js = "javascript:onSuccess('" + jsonToJs + "')";
                    mWebView.loadUrl(url2Js);
                    break;
            }
        }
    };


    @Override
    protected void initView() {
        mWebView = findViewById(R.id.webview);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_js);
    }

    @Override
    protected void initPresenter() {
       mJsPresenter = new JsPresenter(mIJsView,mJsInterface);
       mJsPresenter.start();
    }


    private final JsInterface mJsInterface = new JsInterface() {
        @Override
        @JavascriptInterface
        public void play(String jsonStringFromJs) {

            mIJsView.showToast(jsonStringFromJs);

            HeadCommand headCommand = JSON.parseObject(jsonStringFromJs,HeadCommand.class);
            LogHelper.d(TAG,headCommand.toString());

            headCommand.setId("999");
            headCommand.setCommand("voice");
            headCommand.setSpeed(20);
            headCommand.setAngle(100);
            headCommand.setType("linear");

            String jsonFromJava = JSON.toJSONString(headCommand);

            Message message = mHandler.obtainMessage();
            message.what = JAVA_TO_JS;

            Bundle bundle = new Bundle();
            bundle.putCharSequence(JSON_STRING, jsonFromJava);
            message.setData(bundle);
            mHandler.sendMessage(message);

        }
    };

    private final IJsView mIJsView = new IJsView() {
        @Override
        public void showToast(final String msg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showToast(JsActivity.this,msg);
                }
            });
        }

        @Override
        public WebView getWebView() {
            return mWebView;
        }

        @Override
        public void onPageStarted() {

        }

        @Override
        public void onPageFinished() {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mJsPresenter.release();
    }
}
