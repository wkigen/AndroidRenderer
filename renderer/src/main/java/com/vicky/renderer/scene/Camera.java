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

    @Override
    public void translation(float x,float y,float z) {
        super.translation(x, y, z);
        Matrix.multiplyMM(viewMatrix, 0, viewModeltMatrix, 0, modelMatrix, 0);
    }

    @Override
    public void rotate(float a,float x,float y,float z){
        super.rotate(a,x,y,z);
        Matrix.multiplyMM(viewMatrix, 0, viewModeltMatrix, 0, modelMatrix, 0);
    }

    @Override
    public void reRead() {

    }

    public float[] getViewMatrix(){
        return viewMatrix;
    }

}
