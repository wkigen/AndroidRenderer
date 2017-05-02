package com.vicky.renderer.scene;

import android.opengl.Matrix;

import java.nio.ByteBuffer;

/**
 * Created by vicky on 2017/5/2.
 */
public class Camera3D extends Camera {

    public Camera3D(float fovy, float aspect, float near, float far, float eyex,float eyey, float eyez,
                  float centerx,float centery,float centerz, float upx,float upy,float upz){
        super();

        float[] lookatMatrix = new float[16];
        float[] projectMatrix = new float[16];

        Matrix.setIdentityM(lookatMatrix, 0);
        Matrix.setIdentityM(projectMatrix,0);
        Matrix.setIdentityM(viewModeltMatrix, 0);

        Matrix.setLookAtM(lookatMatrix, 0, eyex, eyey, eyez, centerx, centery, centerz, upx, upy, upz);
        Matrix.perspectiveM(projectMatrix, 0, fovy, aspect, near, far);
        Matrix.multiplyMM(viewModeltMatrix, 0, projectMatrix, 0, lookatMatrix, 0);

        toViewMatrix();
    }



}
