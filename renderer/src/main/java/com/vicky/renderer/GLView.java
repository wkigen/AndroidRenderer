package com.vicky.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import com.vicky.renderer.renderer.RenderEngine;
import com.vicky.renderer.scene.SceneEngine;

/**
 * Created by vicky on 2017/4/21.
 */
public class GLView extends GLSurfaceView {

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setRenderer(RenderEngine.getInstance().getRenderer());
        setRenderMode(RENDERMODE_CONTINUOUSLY);

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
        SceneEngine.getInstance().setNeedReRead(true);
    }

}
