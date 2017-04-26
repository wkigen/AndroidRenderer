package com.vicky.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

/**
 * Created by vicky on 2017/4/21.
 */
public class GLView extends GLSurfaceView {

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEGLContextClientVersion(2);
        RenderEngine.getInstance().init(context);
        setRenderer(RenderEngine.getInstance().getRenderer());
        setRenderMode(RENDERMODE_CONTINUOUSLY);
        requestRender();

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
