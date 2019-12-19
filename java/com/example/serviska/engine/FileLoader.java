package com.example.serviska.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileLoader {

    public static String LoadFile(Uri uri){
        String result="";
        File file=null;
        //File sdDir = Environment.getExternalStorageDirectory();
        try {
            file=new File(uri.getPath());
            FileInputStream fis=new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while((line = reader.readLine())!=null){
                result += line;
                result += "\n";
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("error-file",uri.getPath());
            return null;
        }
        finally {
            return result;
        }
    }

    public static boolean ExistLocalFile(Context context, String fp){
        File file=context.getFileStreamPath(fp);
        return file.exists();
    }

    public static Bitmap LoadImage(Context context, String fp){
        Bitmap result=null;
        if(!ExistLocalFile(context,fp))return result;

        try {
            InputStream is=context.openFileInput(fp);
            result=BitmapFactory.decodeStream(is);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static String LoadFile(Context context, String fp){
        String result="";

        try {
            InputStream is = context.openFileInput(fp);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!=null){
                result += line;
                result += "\n";
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            return result;
        }
    }
    public static String LoadFileFromAsset(Context context, String fn){
        String result="";

        try {
            InputStream is = context.getAssets().open(fn);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!=null){
                result += line;
                result += "\n";
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            return result;
        }
    }
}
