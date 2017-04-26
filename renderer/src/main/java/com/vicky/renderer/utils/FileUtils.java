package com.vicky.renderer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.RawRes;

import com.vicky.renderer.RenderEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by vicky on 2017/4/24.
 */
public class FileUtils {

    public static String loadShaderFromRaw(@RawRes final int resourceId){
        final InputStream inputStream = RenderEngine.getInstance().getContext().getResources().openRawResource(
                resourceId);
        final InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream);
        final BufferedReader bufferedReader = new BufferedReader(
                inputStreamReader);

        String nextLine;
        final StringBuilder body = new StringBuilder();

        try{
            while ((nextLine = bufferedReader.readLine()) != null){
                body.append(nextLine);
                body.append('\n');
            }
        }
        catch (IOException e){
            return null;
        }
        return body.toString();
    }

    public static Bitmap loadBitmapFromAsset(final String name){
        try{
            final InputStream inputStream = RenderEngine.getInstance().getContext().getResources().getAssets().open(name);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch (Exception e){
            return null;
        }
    }
}
