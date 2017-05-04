package com.vicky.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.vicky.renderer.renderer.RenderEngine;
import com.vicky.renderer.scene.Camera;
import com.vicky.renderer.scene.SceneEngine;
import com.vicky.renderer.utils.DistanceUtil;

/**
 * Created by vicky on 2017/4/21.
 */
public class GLView extends GLSurfaceView {

    private float lastX;
    private float lastY;

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

    @Override
    public boolean onTouchEvent(MotionEvent event){
        controlCamera(event);
        return true;
    }

    private void controlCamera(MotionEvent event){
        final float x = event.getX();
        final float y = event.getY();
        final int action = event.getAction();
        Camera camera = RenderEngine.getInstance().getCamera();
        switch (action){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(y - lastY) < 20){
                    float offest = x - lastX;
                    float angle = 360 * DistanceUtil.realToGlForWidth(offest);
                    camera.rotate(angle,0,1,0);
                }else {
                    float offest = y - lastY;
                    float angle = 360 * DistanceUtil.realToGlForHeight(offest);
                    camera.rotate(angle, 1, 0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
    }

}
