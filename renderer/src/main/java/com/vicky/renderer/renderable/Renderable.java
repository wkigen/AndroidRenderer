package com.vicky.renderer.renderable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.vicky.renderer.Constant;
import com.vicky.renderer.utils.FileUtils;
import com.vicky.renderer.utils.OpenGlUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by vicky on 2017/4/21.
 */
public class Renderable {

    protected int           textureId = Constant.Invalid_TextureId;
    protected int           dataBuffersId = Constant.Invalid_BuffersId;
    protected int           elementBuffersId = Constant.Invalid_BuffersId;

    protected FloatBuffer   dataBuffer;
    protected IntBuffer     indexBuffer;
    protected FloatBuffer   textureCoordinateBuffer;

    protected int           vertexNum;
    protected int           faceNum;

    protected Matrix        matrix;

    protected Queue<Runnable>   runnableQueue;

    public Renderable(){
        matrix = new Matrix();
        init();
    }

    protected void init(){
        runnableQueue = new LinkedList<>();
    }

    public void addRunable(Runnable runnable){
        synchronized (runnableQueue){
            runnableQueue.add(runnable);
        }
    }

    public void runRunable(){
        synchronized (runnableQueue){
            while (!runnableQueue.isEmpty()){
                runnableQueue.poll().run();
            }
        }
    }

    public void readTexture(String name){
        final Bitmap bitmap = FileUtils.loadBitmapFromAsset(name);
        if (bitmap != null)
            addRunable(new Runnable() {
                @Override
                public void run() {
                    textureId = OpenGlUtils.loadTexture(bitmap,textureId,true);
                }
            });
    }

    public void readData(String path){

    }

    public int getTextureId(){
        return textureId;
    }

    public int getDataBuffersId(){
        return dataBuffersId;
    }

    public int getElementBuffersId(){
        return elementBuffersId;
    }

    public int getVertexNum(){
        return vertexNum;
    }

    public int getFaceNum(){
        return faceNum;
    }
}
