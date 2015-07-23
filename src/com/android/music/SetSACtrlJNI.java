package com.android.music;

import android.util.Log;

//Wrapper for Audio effect native library
public class SetSACtrlJNI {
    static {
        try {
            Log.i("SF", "Trying to load libsa_jni.so");
            System.loadLibrary("sa_jni");
        }
        catch (UnsatisfiedLinkError ule) {
            Log.e("SF", "Warning: Could not load libsa_jni.so");
        }
    }

    // @param : Audio Effect number
    public static native void set(int num);
}
