package com.vicky.renderer.renderer;

import android.opengl.GLES20;

import com.vicky.renderer.Constant;
import com.vicky.renderer.R;
import com.vicky.renderer.renderable.Renderable;
import com.vicky.renderer.utils.OpenGlUtils;


/**
 * Created by vicky on 2017/4/21.
 */
public class Process {

    protected int programId = Constant.Invalid_Program;
    protected int attribPosition;
    protected int attribTextureCoordinate;
    protected int uniformTexture;
    protected int uniformModelMatrix;
    protected int uniformProjectdViewMatrix;

    public Process(){
        init();
    }

    protected void init(){
    }

    public void renderInit(){
        programId = OpenGlUtils.loadProgramFromRaw(R.raw.vertex,R.raw.fragment);
        attribPosition = GLES20.glGetAttribLocation(programId, "position");
        uniformTexture = GLES20.glGetUniformLocation(programId, "inputImageTexture");
        attribTextureCoordinate = GLES20.glGetAttribLocation(programId, "inputTextureCoordinate");
        uniformModelMatrix = GLES20.glGetUniformLocation(programId, "modelMatrix");
        uniformProjectdViewMatrix = GLES20.glGetUniformLocation(programId, "projectViewdMatrix");
    }

    public void process(Renderable renderable,float[] projectMatrix){
        GLES20.glUseProgram(programId);

        GLES20.glEnableVertexAttribArray(attribPosition);
        GLES20.glEnableVertexAttribArray(attribTextureCoordinate);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, renderable.getDataBuffersId());
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, renderable.getElementBuffersId());
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, renderable.getTextureId());

        GLES20.glVertexAttribPointer(attribPosition, 3, GLES20.GL_FLOAT, false, 32, 0);
        GLES20.glVertexAttribPointer(attribTextureCoordinate, 2, GLES20.GL_FLOAT, false, 32, 24);

        GLES20.glUniformMatrix4fv(uniformModelMatrix, 1, false,renderable.getModelMatrix(), 0);
        GLES20.glUniformMatrix4fv(uniformProjectdViewMatrix, 1, false, projectMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, renderable.getFaceNum() * 3, GLES20.GL_UNSIGNED_INT, 0);

        GLES20.glDisableVertexAttribArray(0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

}
