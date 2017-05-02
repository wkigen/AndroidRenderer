package com.vicky.renderer.renderable;

import android.graphics.Bitmap;

import com.vicky.renderer.utils.FileUtils;
import com.vicky.renderer.utils.OpenGlUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by vicky on 2017/4/24.
 */
public class Image extends Renderable {

    public static final int INDICE[]={
            0,1,2,
            1,3,2,
    };

    float aspect = 1;

    public Image(){
        super();
    }

    @Override
    protected void init(){
        super.init();
    }

    @Override
    public void readTexture(String name){
        final Bitmap bitmap = FileUtils.loadBitmapFromAsset(name);
        aspect = (float)bitmap.getWidth() / (float)bitmap.getHeight();
        if (bitmap != null)
            addRunable(new Runnable() {
                @Override
                public void run() {
                    textureId = OpenGlUtils.loadTexture(bitmap,textureId,true);
                }
            });
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

        addRunable(new Runnable() {
            @Override
            public void run() {
                dataBuffersId = OpenGlUtils.loadArrayBuffers(dataBuffersId, dataBuffer);
                elementBuffersId = OpenGlUtils.loadElementBuffers(elementBuffersId, indexBuffer);
            }
        });
    }

}
