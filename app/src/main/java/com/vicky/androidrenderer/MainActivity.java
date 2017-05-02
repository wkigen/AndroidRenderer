package com.vicky.androidrenderer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vicky.renderer.RenderEngine;
import com.vicky.renderer.renderable.Image;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Image squareImage = new Image();
        squareImage.readTexture("leimu.jpg");
        squareImage.readData(null);
        //image.translation(1,0,0);
        RenderEngine.getInstance().addRenderalbe("leimu", squareImage);
    }
}
