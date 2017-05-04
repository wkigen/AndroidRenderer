package com.vicky.androidrenderer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vicky.renderer.GLView;
import com.vicky.renderer.renderable.BackgroundImage;
import com.vicky.renderer.renderable.Image;
import com.vicky.renderer.resources.ResourcesEngine;
import com.vicky.renderer.scene.SceneEngine;
import com.vicky.renderer.utils.FileUtils;

public class MainActivity extends AppCompatActivity {

    GLView glView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        glView = (GLView)findViewById(R.id.gv_main);

        Image image = new Image("leimu.jpg");
        SceneEngine.getInstance().addNode("leimu", image);
    }

    @Override
    public void onResume(){
        super.onResume();
        glView.onResume();


    }

    @Override
    public void onPause(){
        super.onPause();
        glView.onPause();
    }
}
