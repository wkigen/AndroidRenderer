package com.vicky.renderer.mesh;

import com.vicky.renderer.renderable.Renderable;
import com.vicky.renderer.renderable.RenderableType;

/**
 * Created by vicky on 2017/5/19.
 */
public class Mesh extends Renderable{

    public Mesh(){
        super();
    }

    @Override
    protected void init(){
        super.init();
        renderabletype = RenderableType.Mesh;
    }



    @Override
    public void reRead() {

    }
}
