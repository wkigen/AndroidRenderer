package com.vicky.androidrenderer;

import android.app.Application;

import com.vicky.renderer.renderer.RenderEngine;
import com.vicky.renderer.resources.ResourcesEngine;

/**
 * Created by vicky on 2017/5/3.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        RenderEngine.getInstance().init();
        ResourcesEngine.getInstance().init(this);

    }
}
