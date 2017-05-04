package com.vicky.renderer.utils;

/**
 * Created by vicky on 2017/5/4.
 */
public class DistanceUtil {

    public static float width;
    public static float height;

    public static void init(int w,int h){
        width = w;
        height = h;
    }

    public static float realToGlForWidth(float distance){
        return distance / width / 2;
    }

    public static float realToGlForHeight(float distance){
        return distance / height / 2;
    }

}
