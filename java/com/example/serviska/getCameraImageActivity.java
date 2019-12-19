package com.example.serviska;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.Toast;

import com.example.serviska.engine.BitMapHolder;
import com.example.serviska.engine.Record;

public class getCameraImageActivity extends AppCompatActivity
{
    private static final int CAMERA_REQUEST = 910;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    private ImageView imgPicture;
    private ImageButton btnNewPic;
    private ImageButton btnOK;
    private ImageButton btnClose;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_camera_image);

        imgPicture = (ImageView)this.findViewById(R.id.imgPicture);

        btnNewPic= (ImageButton) this.findViewById(R.id.btnNewPic);
        btnOK= (ImageButton) this.findViewById(R.id.btnOkPic);
        btnClose= (ImageButton) this.findViewById(R.id.btnClosePic);

        chceckHolderForImage();

        btnNewPic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        btnClose.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    finish();
                }
                return true;
            }
        });

        btnOK.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    Intent resultIntent = new Intent();
                    setResult(manageRecordActivity.RESULT_OK, resultIntent);
                    finish();
                }
                return true;
            }
        });

    }

    private void chceckHolderForImage(){
        if(BitMapHolder.photo!=null){
            imgPicture.setImageBitmap(BitMapHolder.photo);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if(photo==null)return;

            imgPicture.setImageBitmap(photo);
            BitMapHolder.photo=photo;
        }
    }
}
