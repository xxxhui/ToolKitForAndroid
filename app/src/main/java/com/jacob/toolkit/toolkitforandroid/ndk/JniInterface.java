package com.jacob.toolkit.toolkitforandroid.ndk;

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
    }

    public void setName(String name){
        this.name = name;
    }

    public String append (int age, String gender){
        return age+","+gender;
    }

    private static String staticMethod(String wife){
        return "Jacob:"+wife;
    }



}
