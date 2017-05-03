package com.vicky.renderer.scene;


import com.vicky.renderer.renderable.Renderable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicky on 2017/5/2.
 */
public class SceneEngine {

    private static SceneEngine instance;
    private Map<String,Node> nodeList;
    private Boolean needReRead = false;

    private SceneEngine(){
        nodeList = new HashMap<>();
    }

    public static synchronized SceneEngine getInstance() {
        if (null == instance) {
            instance = new SceneEngine();
        }
        return instance;
    }

    public Map<String,Node> getNodeList(){
        synchronized (nodeList){
            return nodeList;
        }
    }

    public void addNode(String name, Node node){
        synchronized (nodeList){
            nodeList.put(name, node);
        }
    }

    public void removeNode(String name){
        synchronized (nodeList){
            nodeList.remove(name);
        }
    }

    public void reRead(){
        if (needReRead){
            for (Map.Entry<String,Node> node : nodeList.entrySet()){
                node.getValue().reRead();
            }
            needReRead = false;
        }
    }

    public void setNeedReRead(boolean needReRead){
        this.needReRead = needReRead;
    }
}
