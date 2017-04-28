package com.vicky.renderer.scene;

import android.opengl.Matrix;

/**
 * Created by vicky on 2017/4/27.
 */
public class Node {

    protected float[] modelMatrix;

    public Node(){
        init();
    }

    protected void init(){
        modelMatrix = new float[16];
        Matrix.setIdentityM(modelMatrix, 0);
    }

    public float[] getModelMatrix(){
        return modelMatrix;
    }

    public void translation(float x,float y,float z) {
        Matrix.translateM(modelMatrix,0,x,y,z);
    }

}
