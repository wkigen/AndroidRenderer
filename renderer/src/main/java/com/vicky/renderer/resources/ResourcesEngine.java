package com.vicky.renderer.resources;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.RawRes;

import com.vicky.renderer.utils.FileUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicky on 2017/5/3.
 */
public class ResourcesEngine {

    private static ResourcesEngine instance;
    private Context context;

    private Map<String,Bitmap> bitmapMap = new HashMap<>();
    private Map<Integer,String> shaderMap = new HashMap<>();

    private ResourcesEngine(){}

    public static synchronized ResourcesEngine getInstance() {
        if (null == instance) {
            instance = new ResourcesEngine();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
    }

    public Bitmap getBitmap(String name){
        if (bitmapMap.containsKey(name)){
            return bitmapMap.get(name);
        }else {
            Bitmap bitmap = FileUtils.loadBitmapFromAsset(context,name);
            if (bitmap != null)
                bitmapMap.put(name,bitmap);
            return bitmap;
        }
    }

    public String getShaderFromRaw(@RawRes final int resourceId){
        if (shaderMap.containsKey(resourceId)){
            return shaderMap.get(resourceId);
        }else {
            String shader = FileUtils.loadShaderFromRaw(context,resourceId);
            if (shader != null)
                shaderMap.put(resourceId,shader);
            return shader;
        }
    }

}
