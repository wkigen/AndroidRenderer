package com.vicky.androidrenderer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vicky.renderer.RenderEngine;
import com.vicky.renderer.renderable.Image;
import com.vicky.renderer.renderable.Renderable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Image image = new Image();
        image.readTexture("leimu.jpg");
        image.readData(null);
        image.translation(2,0,0);
        RenderEngine.getInstance().addRenderalbe("leimu",image);
    }
}
