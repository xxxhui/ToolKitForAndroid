package com.jacob.toolkit.toolkitforandroid.ndk;

import android.widget.TextView;

import com.jacob.toolkit.toolkitforandroid.R;
import com.jacob.toolkit.toolkitforandroid.ui.BaseActivity;

/**
 * @author jacob
 * @date 4/11/18
 */

public class NdkActivity extends BaseActivity{
    @Override
    protected void initView() {
        TextView content = findViewById(R.id.content);
        content.setText(JniInterface.getInstance().changeString("I feel bad!"));

        float[] floats = new float[]{1.0f, 2.0f, 3.0f, 4.0f};

        floats = JniInterface.getInstance().changeFloatArray(floats);

        for (int i = 0; i < floats.length; i++) {
        }

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setContentLayout() {

        setContentView(R.layout.activity_ndk);

    }

    @Override
    protected void initPresenter() {

    }
}
