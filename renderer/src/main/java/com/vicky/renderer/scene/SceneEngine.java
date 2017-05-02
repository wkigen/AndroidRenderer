package com.vicky.renderer.scene;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicky on 2017/5/2.
 */
public class SceneEngine {

    private static SceneEngine instance;
    private Map<String,Node> renderableList;

    private SceneEngine(){
        renderableList = new HashMap<>();
    }

    public static synchronized SceneEngine getInstance() {
        if (null == instance) {
            instance = new SceneEngine();
        }
        return instance;
    }

    public Map<String,Node> getNodeList(){
        synchronized (renderableList){
            return renderableList;
        }
    }

    public void addNode(String name, Node node){
        synchronized (renderableList){
            renderableList.put(name,node);
        }
    }

    public void removeNode(String name){
        synchronized (renderableList){
            renderableList.remove(name);
        }
    }


}
