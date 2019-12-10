package com.example.serviska.engine;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileManager {
    public static String LoadFileFromAsset(Context context, String fn){
        String result="";

        try {
            InputStream is = context.getAssets().open(fn);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!=null){
                result += line;
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

}
