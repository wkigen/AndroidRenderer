package com.vicky.renderer.utils;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.support.annotation.RawRes;
import android.util.Log;

import com.vicky.renderer.Constant;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class OpenGlUtils {

    public static void checkError(String op) {
        int error = GLES20.glGetError();
        if (error != GLES20.GL_NO_ERROR){
            String msg = op + ": glError 0x" + Integer.toHexString(error);
            Log.e("OpenGlUtils", msg);
            throw new RuntimeException(msg);
        }
    }

    public static int createTexture()
    {
        int[] textures = new int[1];

        GLES20.glGenTextures(1, textures, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        return textures[0];
    }

    public static int loadTexture(final Bitmap img, final int textureId, final boolean recycle) {
        int textures[] = new int[1];
        if (textureId == Constant.Invalid_TextureId) {
            GLES20.glGenTextures(1, textures, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, img, 0);
        } else {
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
            GLUtils.texSubImage2D(GLES20.GL_TEXTURE_2D, 0, 0, 0, img);
            textures[0] = textureId;
        }
        if (recycle) {
            img.recycle();
        }
        return textures[0];
    }

    public static int loadShader(final String strSource, final int iType) {
        int[] compiled = new int[1];
        int shader = GLES20.glCreateShader(iType);
        GLES20.glShaderSource(shader, strSource);
        GLES20.glCompileShader(shader);
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        checkError("load shader");
        return shader;
    }

    public static int loadProgram(final String strVSource, final String strFSource) {
        int vShader;
        int fShader;
        int programId;
        int[] link = new int[1];
        vShader = loadShader(strVSource, GLES20.GL_VERTEX_SHADER);
        checkError("load vertex shader");

        fShader = loadShader(strFSource, GLES20.GL_FRAGMENT_SHADER);
        checkError("load fragment shader");

        programId = GLES20.glCreateProgram();

        GLES20.glAttachShader(programId, vShader);
        GLES20.glAttachShader(programId, fShader);

        GLES20.glLinkProgram(programId);

        GLES20.glGetProgramiv(programId, GLES20.GL_LINK_STATUS, link, 0);
        checkError("link program");

        GLES20.glDeleteShader(vShader);
        GLES20.glDeleteShader(fShader);
        return programId;
    }

    public static int loadProgramFromRaw(@RawRes final int vRaw, @RawRes int  fRaw) {
        String strVShder = FileUtils.loadShaderFromRaw(vRaw);
        String strFShder = FileUtils.loadShaderFromRaw(fRaw);
        return loadProgram(strVShder,strFShder);
    }

    public static int loadArrayBuffers(final int buffersId,final FloatBuffer floatBuffer){
        return loadBuffers(GLES20.GL_ARRAY_BUFFER, buffersId, floatBuffer);
    }

    public static int loadElementBuffers(final int buffersId,final IntBuffer intBuffer){
        return loadBuffers(GLES20.GL_ELEMENT_ARRAY_BUFFER,buffersId,intBuffer);
    }

    public static int loadBuffers(final int traget,final int buffersId,final Buffer buffer){
        int buffers[] = new int[1];
        if (buffersId == Constant.Invalid_BuffersId){
            GLES20.glGenBuffers(1,buffers,0);
            GLES20.glBindBuffer(traget, buffers[0]);
        }else{
            GLES20.glBindBuffer(traget,buffersId);
        }

        GLES20.glBufferData(traget,buffer.limit() * 4,buffer,GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(traget, 0);
        return buffers[0];
    }



}
