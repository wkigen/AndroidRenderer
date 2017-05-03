package com.vicky.renderer.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.vicky.renderer.renderable.Renderable;
import com.vicky.renderer.renderable.RenderableType;
import com.vicky.renderer.scene.Camera;
import com.vicky.renderer.scene.Node;
import com.vicky.renderer.scene.SceneEngine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by vicky on 2017/4/21.
 */
public class Renderer implements GLSurfaceView.Renderer {

    private Queue<Renderable>   renderableQueue;

    public Renderer(){
        renderableQueue = new LinkedList<>();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        RenderEngine.getInstance().renderInit();
        SceneEngine.getInstance().reRead();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        RenderEngine.getInstance().setViewPort(width, height);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        Camera camera = RenderEngine.getInstance().getCamera();
        RenderEngine.getInstance().runRunable();
        preDraw();
        draw(camera.getViewMatrix());
        postDraw();
    }

    private void preDraw(){
        Map<String,Node> renderableList = SceneEngine.getInstance().getNodeList();
        for (String name : renderableList.keySet()){
            Node node = renderableList.get(name);
            if (node instanceof  Renderable){
                Renderable renderable = (Renderable)node;
                renderableQueue.add(renderable);
            }
        }
    }

    private void postDraw(){

    }

    private void draw(float[] projectMatrix){

        while (!renderableQueue.isEmpty()){
            Renderable renderable = renderableQueue.poll();
            RenderableType renderableType = renderable.getRenderabletype();
            Process process = RenderEngine.getInstance().getProcess(renderableType);
            if (process != null){
                process.process(renderable,projectMatrix);
            }
        }
    }

}
