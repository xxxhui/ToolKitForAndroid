package com.jacob.toolkit.toolkitforandroid;

import android.view.View;

import com.jacob.toolkit.toolkitforandroid.base.BaseActivity;
import com.jacob.toolkit.toolkitforandroid.ndk.NdkActivity;
import com.jacob.toolkit.toolkitforandroid.seekbar.SeekBarActivity;

/**
 * @author jacob
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initView() {
        findViewById(R.id.ndk_button).setOnClickListener(this);
        findViewById(R.id.seekbar_button).setOnClickListener(this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ndk_button:
                startActivity(NdkActivity.class);
                break;
            case R.id.seekbar_button:
                startActivity(SeekBarActivity.class);
                break;
        }
    }
}
