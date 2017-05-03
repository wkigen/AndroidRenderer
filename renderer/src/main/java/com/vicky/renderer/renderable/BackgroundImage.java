package com.vicky.renderer.renderable;

import com.vicky.renderer.renderer.RenderEngine;
import com.vicky.renderer.utils.OpenGlUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by vicky on 2017/5/2.
 */
public class BackgroundImage extends Image {

    public BackgroundImage(){
        super();
    }

    @Override
    protected void init(){
        super.init();
        renderabletype = RenderableType.Background;
    }

    @Override
    public void readData(String path){

        float DATA[] = {
                //POSITION   NORMAL  TEXTURE_COORDINATE
                -1.0f,-1.0f,0.0f, 0.0f,0.0f,0.0f, 0.0f, 1.0f,
                1.0f,-1.0f,0.0f,  0.0f,0.0f,0.0f, 1.0f, 1.0f,
                -1.0f,1.0f,0.0f,  0.0f,0.0f,0.0f, 0.0f, 0.0f,
                1.0f,1.0f,0.0f,   0.0f,0.0f,0.0f, 1.0f, 0.0f,
        };

        dataBuffer = ByteBuffer.allocateDirect(DATA.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        dataBuffer.put(DATA).flip();

        indexBuffer  = ByteBuffer.allocateDirect(INDICE.length * 4)
                .order(ByteOrder.nativeOrder())
                .asIntBuffer();
        indexBuffer.put(INDICE).flip();

        faceNum = 2;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dataBuffersId = OpenGlUtils.loadArrayBuffers( dataBuffer);
                elementBuffersId = OpenGlUtils.loadElementBuffers(indexBuffer);
            }
        };

        RenderEngine.getInstance().addRunable( runnable);
    }

}
