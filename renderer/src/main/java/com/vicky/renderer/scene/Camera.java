package com.vicky.renderer.scene;

import android.opengl.Matrix;

/**
 * Created by vicky on 2017/4/28.
 */
public class Camera extends  Node{

    private float[] projectMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private float[] projecViewModeltMatrix = new float[16];

    public Camera(float fovy, float aspect, float near, float far, float eyex,float eyey, float eyez,
                  float viewx,float viewy,float viewz, float upx,float upy,float upz){

        Matrix.setIdentityM(projectMatrix,0);
        Matrix.setIdentityM(viewMatrix,0);
        Matrix.setIdentityM(projecViewModeltMatrix,0);

        Matrix.setLookAtM(viewMatrix,0,eyex,eyey,eyez,viewx,viewy,viewz,upx,upy,upz);
        Matrix.perspectiveM(projectMatrix, 0, fovy, aspect, near, far);
        Matrix.multiplyMM(projecViewModeltMatrix, 0, projectMatrix, 0, viewMatrix, 0);
    }

    @Override
    public void translation(float x,float y,float z){
        super.translation(x, y, z);
        Matrix.multiplyMM(viewMatrix, 0, modelMatrix, 0, viewMatrix, 0);
        Matrix.multiplyMM(projecViewModeltMatrix, 0, projectMatrix, 0, viewMatrix, 0);
    }

    public float[] getProjecViewModeltMatrix(){
        return projecViewModeltMatrix;
    }

}
