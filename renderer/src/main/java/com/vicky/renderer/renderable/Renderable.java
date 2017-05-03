package com.vicky.renderer.renderable;

import android.graphics.Bitmap;

import com.vicky.renderer.Constant;
import com.vicky.renderer.renderer.RenderEngine;
import com.vicky.renderer.scene.Node;
import com.vicky.renderer.utils.OpenGlUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicky on 2017/4/21.
 */
public abstract class Renderable extends Node{

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
    protected Bitmap        bitmap;

    protected int           vertexNum;
    protected int           faceNum;

    protected RenderableType renderabletype;

    public Renderable(){
        super();
    }

    @Override
    protected void init(){
        super.init();
        renderabletype = RenderableType.Null;
    }

    public void readTexture(Bitmap bit){
        this.bitmap = bit;
        if (bitmap != null){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    textureId = OpenGlUtils.loadTexture(bitmap);
                }
            };
            RenderEngine.getInstance().addRunable(runnable);
        }
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
