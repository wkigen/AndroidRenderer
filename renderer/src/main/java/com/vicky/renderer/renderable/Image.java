package com.vicky.renderer.renderable;

import android.graphics.Bitmap;

import com.vicky.renderer.renderer.RenderEngine;
import com.vicky.renderer.resources.ResourcesEngine;
import com.vicky.renderer.utils.OpenGlUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by vicky on 2017/4/24.
 */
public class Image extends Renderable {

    float aspect = 1;
    public static final int INDICE[]={
            0,1,2,
            1,3,2,
    };

    public Image(){
        super();
    }

    public Image(String name){
        super();
        readTexture(ResourcesEngine.getInstance().getBitmap(name));
        readData(null);
    }

    @Override
    protected void init(){
        super.init();
        renderabletype = RenderableType.Image;
    }

    @Override
    public void readTexture(final Bitmap bitmap){
        aspect = (float)bitmap.getWidth() / (float)bitmap.getHeight();
        super.readTexture(bitmap);
    }

    @Override
    public void readData(String path){

        float DATA[] = {
                //POSITION   NORMAL  TEXTURE_COORDINATE
                -aspect,-1.0f,0.0f, 0.0f,0.0f,0.0f, 0.0f, 1.0f,
                aspect,-1.0f,0.0f,  0.0f,0.0f,0.0f, 1.0f, 1.0f,
                -aspect,1.0f,0.0f,  0.0f,0.0f,0.0f, 0.0f, 0.0f,
                aspect,1.0f,0.0f,   0.0f,0.0f,0.0f, 1.0f, 0.0f,
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
                elementBuffersId = OpenGlUtils.loadElementBuffers( indexBuffer);
            }
        };
        RenderEngine.getInstance().addRunable(runnable);

    }

    @Override
    public void reRead() {
        textureId = OpenGlUtils.loadTexture(bitmap);
        dataBuffersId = OpenGlUtils.loadArrayBuffers(dataBuffer);
        elementBuffersId = OpenGlUtils.loadElementBuffers( indexBuffer);
    }

}
