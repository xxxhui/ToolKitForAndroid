//
// Created by Jacob Wong on 2/22/18.
//

// 在Logcat中打印C++消息
#define LOG_TAG "Jacob"
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__);

#include "com_jacob_toolkit_toolkitforandroid_ndk_JniInterface.h"
#include <iostream>
#include <android/log.h>
#include <stdlib.h>


JNIEXPORT jstring JNICALL Java_com_jacob_toolkit_toolkitforandroid_ndk_JniInterface_stringFromJNI
        (JNIEnv *env, jobject thiz) {

    // C++字符串转换jstring字符串
    std::string name = "String from C++";
    jstring jstring2 = env->NewStringUTF(name.c_str());

    // jstring字符串转C++字符串
    const char *str;
    jboolean isCopy;

    str = env->GetStringUTFChars(jstring2, &isCopy);

    LOGE("%s", str);

    env->ReleaseStringUTFChars(jstring2, str); //释放内存

    if (JNI_TRUE == isCopy) {
        LOGE("C++ string is a copy of the Java string");
    } else {
        LOGE("C++ string points to actual string");
    }

    return jstring2;
}


JNIEXPORT jstring JNICALL Java_com_jacob_toolkit_toolkitforandroid_ndk_JniInterface_changeString
        (JNIEnv *env, jobject thiz, jstring javaString) {
    const char *str;
    jboolean isCopy;

    str = env->GetStringUTFChars(javaString, &isCopy);

    LOGE("%s", str);

    // 字节缓冲区
    unsigned char *buffer = (unsigned char *) malloc(1024);
    jobject directBuffer;
    directBuffer = env->NewDirectByteBuffer(buffer, 1024);

    buffer = (unsigned char *) env->GetDirectBufferAddress(directBuffer);

    // C++利用反射调用java方法
    jclass clazz = env->GetObjectClass(thiz);// 获取该对象的类
    jmethodID methodID1 = env->GetMethodID(clazz, "callJava", "()V"); // 获取callJava方法ID
    env->CallVoidMethod(thiz, methodID1);

    jmethodID methodID2 = env->GetMethodID(clazz, "setName",
                                           "(Ljava/lang/String;)V"); // 获取setName方法ID

    jstring name = env->NewStringUTF("jacob");
    env->CallVoidMethod(thiz, methodID2, name);

    env->DeleteLocalRef(name);

    jmethodID methodID3 = env->GetMethodID(clazz, "append", "(ILjava/lang/String;)Ljava/lang/String;"); // 获取append方法ID
    int age = 28;
    jstring gender = env->NewStringUTF("male");
    jstring result;

    result = (jstring) env->CallObjectMethod(thiz, methodID3, age, gender);

    const char *str1;
    jboolean isCopy1;

    str1 = env->GetStringUTFChars(result, &isCopy1);

    LOGE("%s", str1);

    env->ReleaseStringUTFChars(result,str1);
    env->DeleteLocalRef(gender);
    env->DeleteLocalRef(result);

    // 静态Java方法
    jmethodID methodID4 = env->GetStaticMethodID(clazz, "staticMethod", "(Ljava/lang/String;)Ljava/lang/String;"); // 获取callJava方法ID
    jstring wife = env->NewStringUTF("Iris");
    jstring couple = (jstring) env->CallStaticObjectMethod(clazz, methodID4, wife);

    env->DeleteLocalRef(wife);
    return couple;
}


JNIEXPORT jfloatArray JNICALL Java_com_jacob_toolkit_toolkitforandroid_ndk_JniInterface_changeFloatArray
        (JNIEnv *env, jobject thiz, jfloatArray javaFloatArray) {

    // 获取java传来的数组的长度
    jsize length = env->GetArrayLength(javaFloatArray);
    // 定义一个C++数组
    jfloat nativeArray[length];
    // 将java数组区复制到C++数组区
    env->GetFloatArrayRegion(javaFloatArray, 0, length, nativeArray);
    // 打印java数组的元素并+0.1f
    for (int i = 0; i < length; ++i) {
        LOGE("%.1f", nativeArray[i]);
        nativeArray[i] = nativeArray[i] + 0.1f;
    }
    // 将修改后的C++数组重新设置给Java数组
    env->SetFloatArrayRegion(javaFloatArray, 0, length, nativeArray);

    // 指针获取java数组元素,节省上述方法的copy成本
    jfloat *nativeDirectArray;
    jboolean isCopy;
    nativeDirectArray = env->GetFloatArrayElements(javaFloatArray, &isCopy);


    for (int i = 0; i < length; i++) {
        LOGE("%.2f", nativeDirectArray[i]);
    }


    env->ReleaseFloatArrayElements(javaFloatArray, nativeDirectArray, 0);

    return javaFloatArray;
}
