package com.example.serviska.engine;

import android.content.Intent;
import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

public class Record  implements Serializable { //class reprezentující jeden záznam
    public String ID,deviceName,deviceInfo,deviceType;
    public String deviceErrorDescription, deviceCost;
    public String personName, personContact, personInfo;
    public String recordDate;
    public String TimeStamp;

    public Boolean isNew;
    public Boolean hasPhoho;

    public Record(){
        isNew=true;
        hasPhoho=false;
        try {
            ID= Integer.toString(RecordManager.getNextId());
        }
        catch (Exception e){
            e.printStackTrace();
            ID="undefined";
        }
    }

    @Override
    public String toString(){
        return ID+"->"+deviceName+" "+deviceType;
    }

    public String getFileRecordName(){
        return TimeStamp+".txt";
    }

    public String getPhotoName(){return  TimeStamp+"_PHOTO.png";}

    public String getDataForRecordFile(){
        return FileResolverHelper.convertRecordToFile(this);
    }

    public Long getTimeStamp(){
        return ResourceConverter.convertStringToLong(TimeStamp);
    }

}
