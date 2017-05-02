package com.vicky.renderer.renderable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.NonNull;

import com.vicky.renderer.Constant;
import com.vicky.renderer.scene.Node;
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
public class Renderable extends Node{

    //data struct
    //POSITION       NORMAL         TEXTURE_COORDINATE
    //  3(float)       3(float)           2(float)


    //indice struce
    //  int

    protected int           textureId = Constant.Invalid_TextureId;
    protected int           dataBuffersId = Constant.Invalid_BuffersId;
    protected int           elementBuffersId = Constant.Invalid_BuffersId;

    protected FloatBuffer   dataBuffer;
    protected IntBuffer     indexBuffer;
    protected FloatBuffer   textureCoordinateBuffer;

    protected int           vertexNum;
    protected int           faceNum;

    protected RenderableType renderabletype;

    protected Queue<Runnable>   runnableQueue;

    public Renderable(){
        super();
    }

    @Override
    protected void init(){
        super.init();
        runnableQueue = new LinkedList<>();
        renderabletype = RenderableType.Null;
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

    public RenderableType getRenderabletype(){
        return renderabletype;
    }
}
