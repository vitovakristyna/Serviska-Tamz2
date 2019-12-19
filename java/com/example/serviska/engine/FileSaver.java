package com.example.serviska.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;

public class FileSaver {
    public static boolean SaveFile(Uri uri, String data){
        File file=null;
        try {
            file=new File(uri.getPath());
            FileOutputStream fos =new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean SaveFile(Context context,String fn, String data){
        try {
            FileOutputStream fos = context.openFileOutput(fn, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean SaveImage(Context context,String fn, Bitmap data){
        try {
            FileOutputStream fos = context.openFileOutput(fn, Context.MODE_PRIVATE);
            data.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
