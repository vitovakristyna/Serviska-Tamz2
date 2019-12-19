package com.example.serviska.engine;

import android.graphics.Bitmap;

import java.io.Serializable;

public class BitMapHolder implements Serializable {
    public static Bitmap photo;

    public static void clearHolder(){
        photo=null;
    }
}
