package com.vicky.renderer.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.vicky.renderer.RenderEngine;
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

    private Map<RenderableType,Process>     processes;
    private Queue<Renderable>               renderableQueue;

    public Renderer(){
        processes = new HashMap<>();
        renderableQueue = new LinkedList<>();
    }

    public void addProcess(RenderableType type,Process process){
        processes.put(type,process);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        for (Map.Entry<RenderableType,Process>  process : processes.entrySet()){
            process.getValue().render_init();
        }
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
        Map<String,Node> renderableList = SceneEngine.getInstance().getNodeList();
        for (String name : renderableList.keySet()){
            Node node = renderableList.get(name);
            if (node instanceof  Renderable){
                Renderable renderable = (Renderable)node;
                renderable.runRunable();
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
            Process process = processes.get(renderableType);
            if (process != null){
                process.process(renderable,projectMatrix);
            }
        }
    }

}
