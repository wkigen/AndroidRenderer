package com.vicky.renderer;

import android.content.Context;

import com.vicky.renderer.renderable.Renderable;
import com.vicky.renderer.renderer.*;
import com.vicky.renderer.renderer.Process;
import com.vicky.renderer.scene.Camera;
import com.vicky.renderer.scene.Camera2D;
import com.vicky.renderer.scene.Camera3D;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by vicky on 2017/4/21.
 */
public class RenderEngine {

    private static RenderEngine     instance;
    private Renderer                renderer;
    private Map<String,Renderable>  renderableList;
    private Context                 context;
    private Camera                  camera3D,camera2D,currCamera;
    private int                     width;
    private int                     height;

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

        renderer = new Renderer(new Process());

        renderableList = new HashMap<>();
    }

    public void setViewPort(int width,int height){
        this.width = width;
        this.height = height;

        float aspectRatio = (float)width / (float)height;
        camera3D = new Camera3D(45,aspectRatio ,1.0f,10.0f,0.0f,0.0f,3.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f);

        currCamera = camera3D;
    }

    public Context getContext(){
        return context;
    }

    public Renderer getRenderer(){
        return renderer;
    }

    public Camera getCamera(){
        return currCamera;
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
