package com.example.serviska;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import com.example.serviska.engine.BitMapHolder;
import com.example.serviska.engine.FileLoader;
import com.example.serviska.engine.FileResolverHelper;
import com.example.serviska.engine.FileSaver;
import com.example.serviska.engine.Record;

public class manageRecordActivity extends AppCompatActivity {

    private ImageButton btnClose;
    private ImageButton btnOK;
    private ImageButton btnCamera;
    private ImageButton btnSpeaker;
    private Record ActualRecord;

    private EditText txtDeviceName;
    private EditText txtDeviceType;
    private EditText txtDeviceInfo;
    private EditText txtDeviceError;
    private EditText txtPersonName;
    private EditText txtPersonContact;
    private EditText txtPersonInfo;

    private static final int REQUEST_CODE=111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        ActualRecord=(Record) getIntent().getSerializableExtra("ActualRecord");
        if(ActualRecord==null){
            finish();
            return;
        }
        BitMapHolder.clearHolder();
        //Toast.makeText(getApplicationContext(), "Working :)"+ActualRecord, Toast.LENGTH_SHORT).show();

        txtDeviceName=this.findViewById(R.id.txtDeviceName);
        txtDeviceType=this.findViewById(R.id.txtDeviceType);
        txtDeviceInfo=this.findViewById(R.id.txtDeviceInfo);
        txtDeviceError=this.findViewById(R.id.txtDeviceError);
        txtPersonName=this.findViewById(R.id.txtPersonName);
        txtPersonContact=this.findViewById(R.id.txtPersonContact);
        txtPersonInfo=this.findViewById(R.id.txtPersonInfo);

        loadRecord(ActualRecord);

        btnClose=findViewById(R.id.btnClosePic);
        btnClose.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    BitMapHolder.clearHolder();
                    ActualRecord = null;
                    finish();
                }
                return true;
            }
        });
        btnOK=findViewById(R.id.btnOK);
        btnOK.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                processRecord();
                if(!chceckRecord()){
                    Toast.makeText(getApplicationContext(),"Device NAME,TYPE and Person NAME are requied!",Toast.LENGTH_SHORT).show();
                    return false;
                }
                MainActivity.recordManager.updateRecords(ActualRecord);

                finish();
                return true;
            }
                return false;
            }
        });
        btnCamera=findViewById(R.id.btnCamera);
        btnCamera.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    Intent intent=new Intent(getApplicationContext(),getCameraImageActivity.class);
                    startActivityForResult(intent,REQUEST_CODE);
                }
                return true;
            }
        });

        btnSpeaker=findViewById(R.id.btnSpeaker);
        btnSpeaker.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    Intent intent=new Intent(getApplicationContext(),getSpeakerRerordActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

    }
    private void processRecord(){
        if(ActualRecord.isNew){
            ActualRecord.recordDate=FileResolverHelper.getDate();
            ActualRecord.TimeStamp= FileResolverHelper.createTimeStamp();
        }
        ActualRecord.isNew=false;
        ActualRecord.deviceName=txtDeviceName.getText().toString();
        ActualRecord.deviceType=txtDeviceType.getText().toString();
        ActualRecord.deviceInfo=txtDeviceInfo.getText().toString();
        ActualRecord.deviceErrorDescription=txtDeviceError.getText().toString();
        ActualRecord.personName=txtPersonName.getText().toString();
        ActualRecord.personContact=txtPersonContact.getText().toString();
        ActualRecord.personInfo=txtPersonInfo.getText().toString();
    }

    private boolean chceckRecord(){
        if(ActualRecord.deviceName.isEmpty())return false;
        if(ActualRecord.deviceType.isEmpty())return false;
        if(ActualRecord.personName.isEmpty())return false;
        return true;
    }

    private void loadRecord(Record R){
        if(R.isNew)return;
        if(R.hasPhoho){
            BitMapHolder.photo= FileLoader.LoadImage(getApplicationContext(),R.getPhotoName());
        }

        Toast.makeText(getApplicationContext(),"Load record for edit...",Toast.LENGTH_SHORT).show();
        txtDeviceName.setText(R.deviceName);
        txtDeviceType.setText(R.deviceType);
        txtDeviceInfo.setText(R.deviceInfo);
        txtDeviceError.setText(R.deviceErrorDescription);
        txtPersonName.setText(R.personName);
        txtPersonContact.setText(R.personContact);
        txtPersonInfo.setText(R.personInfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_CODE:{
                if(resultCode==RESULT_OK){
                    if(BitMapHolder.photo!=null)
                        ActualRecord.hasPhoho=true;
                        FileSaver.SaveImage(getApplicationContext(),ActualRecord.getPhotoName(),BitMapHolder.photo);
                }
            }
        }
    }
}
