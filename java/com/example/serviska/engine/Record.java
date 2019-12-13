package com.example.serviska.engine;

import android.content.Intent;

import java.io.Serializable;
import java.util.Date;

public class Record  implements Serializable { //class reprezentující jeden záznam
    public String ID,deviceName,deviceInfo,deviceType;
    public String deviceErrorDescription, deviceCost;
    public String personName, personContact, personInfo;
    public Date recordDate;

    public Boolean isNew;

    public Record(){
        isNew=true;
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
}
