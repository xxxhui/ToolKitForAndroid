package com.jacob.toolkit.toolkitforandroid.ndk;

import android.widget.TextView;

import com.jacob.toolkit.toolkitforandroid.R;
import com.jacob.toolkit.toolkitforandroid.base.BaseActivity;
import com.jacob.toolkit.toolkitforandroid.util.LogUtil;

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
            LogUtil.e1("(" + i + "," + floats[i] + ")");
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
