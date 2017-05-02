package com.vicky.renderer.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.vicky.renderer.RenderEngine;
import com.vicky.renderer.renderable.Renderable;
import com.vicky.renderer.scene.Camera;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by vicky on 2017/4/21.
 */
public class Renderer implements GLSurfaceView.Renderer {

    private Process             process;
    private Queue<Renderable>   renderableQueue;

    public Renderer(Process process){
        this.process = process;
        renderableQueue = new LinkedList<Renderable>();
    }

    public void setProcess(Process process){
        this.process = process;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        process.render_init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        RenderEngine.getInstance().setViewPort(width, height);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        Camera camera = RenderEngine.getInstance().getCamera();
        preDraw();
        draw(camera.getViewMatrix());
        postDraw();

    }

    private void preDraw(){

        Map<String,Renderable> renderableList = RenderEngine.getInstance().getRenderableList();
        for (String name : renderableList.keySet()){
            Renderable renderable = renderableList.get(name);
            renderable.runRunable();
            renderableQueue.add(renderable);
        }

    }

    private void postDraw(){

    }

    private void draw(float[] projectMatrix){

        while (!renderableQueue.isEmpty()){
            Renderable renderable = renderableQueue.poll();
            process.process(renderable,projectMatrix);
        }
    }

}
