package com.vicky.renderer.renderer;

import android.content.Context;

import com.vicky.renderer.renderable.RenderableType;
import com.vicky.renderer.scene.Camera;
import com.vicky.renderer.scene.Camera3D;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


/**
 * Created by vicky on 2017/4/21.
 */
public class RenderEngine {

    private static RenderEngine     instance;
    private Renderer                renderer;
    private Camera                  camera3D,currCamera;
    private int                     width;
    private int                     height;

    private Map<RenderableType,Process> processes;

    protected Queue<Runnable>       runnableQueue;

    private RenderEngine(){
    }

    public static synchronized RenderEngine getInstance() {
        if (null == instance) {
            instance = new RenderEngine();
        }
        return instance;
    }

    public void init()
    {
        processes = new HashMap<>();
        runnableQueue = new LinkedList<>();

        processes.put(RenderableType.Image, new Process());
        processes.put(RenderableType.Background,new BackgroundProcess());

        renderer = new Renderer();
    }

    public void setViewPort(int width,int height){
        this.width = width;
        this.height = height;

        float aspectRatio = (float)width / (float)height;
        camera3D = new Camera3D(45,aspectRatio ,1.0f,10.0f,0.0f,0.0f,3.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f);

        currCamera = camera3D;
    }

    public void addProcess(RenderableType type,Process process){
        processes.put(type,process);
    }

    public Process getProcess(RenderableType type){
        return  processes.get(type);
    }

    public void renderInit(){
        for (Map.Entry<RenderableType,Process>  processEntry : processes.entrySet()){
            Process process = processEntry.getValue();
            process.renderInit();
        }
    }

    public Renderer getRenderer(){
        return renderer;
    }

    public Camera getCamera(){
        return currCamera;
    }

    public void addRunable(Runnable runnable){
        synchronized (runnableQueue){
            runnableQueue.add(runnable);
        }
    }

    public void runRunable(){
        synchronized (runnableQueue){
            while (!runnableQueue.isEmpty()){
                runnableQueue.poll().run();
            }
        }
    }

}
