package com.vicky.renderer.scene;

import android.opengl.Matrix;

/**
 * Created by vicky on 2017/4/28.
 */
public class Camera extends  Node{

    protected float[] viewModeltMatrix = new float[16];
    protected float[] viewMatrix = new float[16];
    public Camera(){
        Matrix.setIdentityM(viewModeltMatrix,0);
        Matrix.setIdentityM(viewMatrix,0);
    }

    protected void toViewMatrix(){
        for (int ii = 0 ;ii < 16;ii++){
            viewMatrix[ii] = viewModeltMatrix[ii];
        }
    }

    public void translation(float x,float y,float z) {
        Matrix.translateM(modelMatrix,0,x,y,z);
        Matrix.multiplyMM(viewMatrix, 0, viewModeltMatrix, 0, modelMatrix, 0);
    }

    public float[] getViewMatrix(){
        return viewMatrix;
    }

}
