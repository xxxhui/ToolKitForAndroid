package com.jacob.toolkit.toolkitforandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jacob.toolkit.toolkitforandroid.ndk.NdkActivity;
import com.jacob.toolkit.toolkitforandroid.seekbar.SeekBarActivity;
import com.jacob.toolkit.toolkitforandroid.ui.BaseActivity;
import com.jacob.toolkit.toolkitforandroid.ui.activity.Camera2Activity;
import com.jacob.toolkit.toolkitforandroid.ui.activity.JsActivity;
import com.jacob.toolkit.toolkitforandroid.ui.activity.StateMachineActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jacob
 */
public class MainActivity extends BaseActivity {

    private static final int REQUEST_CAMERA_PERMISSION_CODE = 1;

    @BindView(R.id.ndk_button)
    Button ndkButton;
    @BindView(R.id.seekbar_button)
    Button seekbarButton;
    @BindView(R.id.js_button)
    Button jsButton;
    @BindView(R.id.camera2_button)
    Button camera2Button;
    @BindView(R.id.sm_button)
    Button smButton;


    @Override
    protected void initView() {

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

    private void enterCamera2Activity() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION_CODE);
        } else {
            startActivity(Camera2Activity.class);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(Camera2Activity.class);
        } else {
            Toast.makeText(this, "This sample requires Camera access", Toast.LENGTH_LONG).show();
        }
    }


    @OnClick({R.id.ndk_button, R.id.seekbar_button, R.id.js_button, R.id.camera2_button, R.id.sm_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ndk_button:
                startActivity(NdkActivity.class);
                break;
            case R.id.seekbar_button:
                startActivity(SeekBarActivity.class);
                break;
            case R.id.js_button:
                startActivity(JsActivity.class);
                break;
            case R.id.camera2_button:
                enterCamera2Activity();
                break;
            case R.id.sm_button:
                startActivity(StateMachineActivity.class);
                break;
        }
    }

}
