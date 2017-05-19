package com.vicky.renderer.mesh;

import com.vicky.renderer.jni.RendererHelperJni;
import com.vicky.renderer.resources.ResourcesEngine;

import java.io.InputStream;

/**
 * Created by vicky on 2017/5/19.
 */
public class MeshEngine {

    private static MeshEngine instance;

    private MeshEngine(){}

    public static synchronized MeshEngine getInstance() {
        if (null == instance) {
            instance = new MeshEngine();
        }
        return instance;
    }

    public void createMesh(String name){
        byte[] data = ResourcesEngine.getInstance().getFileData(name);
        if (data != null)
            RendererHelperJni.readMD5Mesh(data,data.length);
    }

}
