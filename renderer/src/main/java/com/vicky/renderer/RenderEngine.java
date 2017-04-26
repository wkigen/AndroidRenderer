package com.vicky.renderer;

import android.content.Context;
import android.graphics.Camera;

import com.vicky.renderer.renderable.Renderable;
import com.vicky.renderer.renderer.*;
import com.vicky.renderer.renderer.Process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.RecursiveTask;

/**
 * Created by vicky on 2017/4/21.
 */
public class RenderEngine {

    private static RenderEngine     instance;
    private Camera                  camera;
    private Renderer                renderer;
    private Map<String,Renderable>  renderableList;
    private Context                 context;

    private RenderEngine(){
    }

    public static synchronized RenderEngine getInstance() {
        if (null == instance) {
            instance = new RenderEngine();
        }
        return instance;
    }

    public void init(Context context)
    {
        this.context = context;

        camera = new Camera();
        camera.setLocation(0,0,-10);

        renderer = new Renderer(new Process());

        renderableList = new HashMap<>();
    }

    public Context getContext(){
        return context;
    }

    public Renderer getRenderer(){
        return renderer;
    }

    public Camera getCamera(){
        return camera;
    }

    public Map<String,Renderable> getRenderableList(){
        synchronized (renderableList){
            return renderableList;
        }
    }

    public void addRenderalbe(String name,Renderable renderable){
        synchronized (renderableList){
            renderableList.put(name,renderable);
        }
    }

    public void removeRenderable(String name){
        synchronized (renderableList){
            renderableList.remove(name);
        }
    }

}
