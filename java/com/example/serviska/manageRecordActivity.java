package com.example.serviska;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import com.example.serviska.engine.Record;
import com.example.serviska.ui.home.HomeFragment;

public class manageRecordActivity extends AppCompatActivity {

    private ImageButton btnClose;
    private ImageButton btnOK;
    private Record ActualRecord;

    private EditText txtDeviceName;
    private EditText txtDeviceType;
    private EditText txtDeviceInfo;
    private EditText txtDeviceError;
    private EditText txtPersonName;
    private EditText txtPersonContact;
    private EditText txtPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        ActualRecord=(Record) getIntent().getSerializableExtra("ActualRecord");
        if(ActualRecord==null){
            finish();
            return;
        }

        //Toast.makeText(getApplicationContext(), "Working :)"+ActualRecord, Toast.LENGTH_SHORT).show();

        txtDeviceName=this.findViewById(R.id.txtDeviceName);
        txtDeviceType=this.findViewById(R.id.txtDeviceType);
        txtDeviceInfo=this.findViewById(R.id.txtDeviceInfo);
        txtDeviceError=this.findViewById(R.id.txtDeviceError);
        txtPersonName=this.findViewById(R.id.txtPersonName);
        txtPersonContact=this.findViewById(R.id.txtPersonContact);
        txtPersonInfo=this.findViewById(R.id.txtPersonInfo);

        loadRecord(ActualRecord);

        btnClose=findViewById(R.id.btnClose);
        btnClose.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                ActualRecord=null;
                finish();
                return true;
            }
        });
        btnOK=findViewById(R.id.btnOK);
        btnOK.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                processRecord();
                MainActivity.recordManager.updateRecord(ActualRecord);
                /*Intent resultIntent = new Intent();
                resultIntent.putExtra("ActualRecord", ActualRecord);
                MainActivity.recordManager.updateRecord(ActualRecord);
                setResult(MainActivity.RESULT_OK, resultIntent);*/
                finish();
                return true;
            }
        });
    }
    private void processRecord(){
        ActualRecord.isNew=false;
        ActualRecord.deviceName=txtDeviceName.getText().toString();
        ActualRecord.deviceType=txtDeviceType.getText().toString();
        ActualRecord.deviceInfo=txtDeviceInfo.getText().toString();
        ActualRecord.deviceErrorDescription=txtDeviceError.getText().toString();
        ActualRecord.personName=txtPersonName.getText().toString();
        ActualRecord.personContact=txtPersonContact.getText().toString();
        ActualRecord.personInfo=txtPersonInfo.getText().toString();
    }

    private void loadRecord(Record R){
        if(R.isNew)return;

        Toast.makeText(getApplicationContext(),"Load record for edit...",Toast.LENGTH_SHORT).show();
        txtDeviceName.setText(R.deviceName);
        txtDeviceType.setText(R.deviceType);
        txtDeviceInfo.setText(R.deviceInfo);
        txtDeviceError.setText(R.deviceErrorDescription);
        txtPersonName.setText(R.personName);
        txtPersonContact.setText(R.personContact);
        txtPersonInfo.setText(R.personInfo);
    }
}
