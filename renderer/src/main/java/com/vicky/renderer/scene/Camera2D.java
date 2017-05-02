package com.vicky.renderer.scene;

import android.opengl.Matrix;

/**
 * Created by vicky on 2017/5/2.
 */
public class Camera2D extends Camera {

    public Camera2D(float left,float right,float bottom,float top,float near,float far){
        super();

        Matrix.orthoM(viewModeltMatrix,0,left,right,bottom,top,near,far);

        toViewMatrix();
    }
}
