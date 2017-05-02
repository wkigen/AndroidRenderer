package com.vicky.androidrenderer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vicky.renderer.renderable.BackgroundImage;
import com.vicky.renderer.renderable.Image;
import com.vicky.renderer.scene.SceneEngine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackgroundImage image = new BackgroundImage();
        image.readTexture("leimu.jpg");
        image.readData(null);
        //image.translation(1,0,0);
        SceneEngine.getInstance().addNode("leimu", image);
    }
}
