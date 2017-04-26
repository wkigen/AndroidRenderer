package com.vicky.renderer.renderable;

import com.vicky.renderer.Constant;
import com.vicky.renderer.utils.OpenGlUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by vicky on 2017/4/24.
 */
public class Image extends Renderable {

    static final float DATA[] = {
            //POSITION   NORMAL  TEXTURE_COORDINATE
            -1.0f, -1.0f, 1.0f, 0.0f,0.0f,0.0f, 0.0f, 1.0f,
            1.0f, -1.0f, 1.0f,  0.0f,0.0f,0.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,  0.0f,0.0f,0.0f, 0.0f, 0.0f,
            1.0f, 1.0f, 1.0f,   0.0f,0.0f,0.0f, 1.0f, 0.0f,
    };

    public static final int INDEX[]={
            0,1,2,
            1,3,2,
    };

    public Image(){
        super();
    }

    @Override
    protected void init(){
        super.init();
        dataBuffer = ByteBuffer.allocateDirect(DATA.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        dataBuffer.put(DATA).flip();

        indexBuffer  = ByteBuffer.allocateDirect(INDEX.length * 4)
                .order(ByteOrder.nativeOrder())
                .asIntBuffer();
        indexBuffer.put(INDEX).flip();

        faceNum = 2;
    }

    @Override
    public void readData(String path){
        addRunable(new Runnable() {
            @Override
            public void run() {
                dataBuffersId = OpenGlUtils.loadArrayBuffers(dataBuffersId, dataBuffer);
                elementBuffersId = OpenGlUtils.loadElementBuffers(elementBuffersId, indexBuffer);
            }
        });
    }

}
