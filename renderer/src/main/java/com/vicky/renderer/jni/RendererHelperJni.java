package com.vicky.renderer.jni;

/**
 * Created by vicky on 2017/5/19.
 */
public class RendererHelperJni {

    static {
        System.loadLibrary("renderer_core");
    }

    public static native void readMD5Mesh(byte[] data,long len);
}
