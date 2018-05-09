package com.jacob.toolkit.toolkitforandroid.mvp.view;

import android.hardware.camera2.params.Face;
import android.media.ImageReader;
import android.view.Surface;
import android.view.TextureView;

import java.io.File;

/**
 * @author: Jacob
 * @date: 2018/5/8
 */
public interface ICamera2View {

    String getCameraId();
    boolean getFlashSupported();
    void showToast(String msg);
    void refreshFaces(Face [] faces);
    ImageReader initCameraOutputs(int width,int height);
    void initConfigureTransform(int width,int height);
    void onError();
    Surface getSurface();
    int getRotation();
    File getFile();

}
