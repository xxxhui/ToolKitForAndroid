package com.jacob.toolkit.toolkitforandroid.ndk;

import com.jacob.toolkit.toolkitforandroid.util.LogUtil;

/**
 * @author jacob
 * @date 2/22/18
 */

public class JniInterface {

    private String name;

    static {
        System.loadLibrary("JniInterface");
    }

    private static JniInterface instance = null;

    private JniInterface() {
    }

    public static JniInterface getInstance() {

        if (instance == null) {
            instance = new JniInterface();
        }
        return instance;
    }


    public native String stringFromJNI();

    public native String changeString(String originalStr);

    public native float[] changeFloatArray(float[] floatArray);

    public void callJava() {
        LogUtil.e1("callJava() called");
    }

    public void setName(String name){
        this.name = name;
        LogUtil.e1("setName() called with: name = [" + name + "]");
    }

    public String append (int age, String gender){
        LogUtil.e1("append() called with: age = [" + age + "], gender = [" + gender + "]");
        return age+","+gender;
    }

    private static String staticMethod(String wife){
        return "Jacob:"+wife;
    }



}
