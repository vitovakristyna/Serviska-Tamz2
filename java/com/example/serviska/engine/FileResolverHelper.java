package com.example.serviska.engine;

import android.util.Log;

import java.sql.Time;
import java.util.Date;

public class FileResolverHelper {
    public static final String TIMESTAMP="TIMESTAMP";
    public static final String ID="RECORDID";
    public static final String DEVICE_NAME="DEVICE_NAME";
    public static final String DEVICE_TYPE="DEVICE_TYPE";
    public static final String DEVICE_INFO="DEVICE_INFO";
    public static final String DEVICE_ERROR="DEVICE_ERROR";
    public static final String PERSON_NAME="PERSON_NAME";
    public static final String PERSON_CONTACT="PERSON_CONTACT";
    public static final String PERSON_INFO="PERSON_INFO";
    public static final String DATE="DATE";


    public static String convertRecordToFile(Record record){
        String data="";
        data += TIMESTAMP+"\t"+record.lastTimeStamp+"\n";
        data += ID+"\t"+record.ID+"\n";
        data += DEVICE_NAME+"\t"+record.deviceName+"\n";
        data += DEVICE_TYPE+"\t"+record.deviceType+"\n";
        data += DEVICE_INFO+"\t"+record.deviceInfo+"\n";
        data += DEVICE_ERROR+"\t"+record.deviceErrorDescription+"\n";
        data += PERSON_NAME+"\t"+record.personName+"\n";
        data += PERSON_CONTACT+"\t"+record.personContact+"\n";
        data += PERSON_INFO+"\t"+record.personInfo+"\n";
        data += DATE+"\t"+record.recordDate+"\n";
        Log.d("info-record","Record "+record.toString()+" parse!");
        return data;
    }

    public static Record convertFileToRecord(String data){
        Record record=new Record();
        String [] dataSpit=data.split("\n");
        int helpIndex=0;
        int maxIndex=0;
        int actualDataIndex=0;

        try {
        actualDataIndex=0;
        if(dataSpit[actualDataIndex].contains(TIMESTAMP)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.lastTimeStamp=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=1;
        if(dataSpit[actualDataIndex].contains(ID)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.ID=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=2;
        if(dataSpit[actualDataIndex].contains(DEVICE_NAME)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.deviceName=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=3;
        if(dataSpit[actualDataIndex].contains(DEVICE_TYPE)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.deviceType=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=4;
        if(dataSpit[actualDataIndex].contains(DEVICE_INFO)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.deviceInfo=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=5;
        if(dataSpit[actualDataIndex].contains(DEVICE_ERROR)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.deviceErrorDescription=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=6;
        if(dataSpit[actualDataIndex].contains(PERSON_NAME)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.personName=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=7;
        if(dataSpit[actualDataIndex].contains(PERSON_CONTACT)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.personContact=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=8;
        if(dataSpit[actualDataIndex].contains(PERSON_INFO)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.personInfo=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        actualDataIndex=9;
        if(dataSpit[actualDataIndex].contains(DATE)){
            helpIndex=dataSpit[actualDataIndex].lastIndexOf("\t")+1;
            maxIndex=(dataSpit[actualDataIndex].length());
            record.recordDate=dataSpit[actualDataIndex].substring(helpIndex,maxIndex);
        }else return null;
        Log.d("info-record","Record "+record.toString()+" resolve!");
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        record.isNew=false;
        return record;
    }

    public static String createTimeStamp(){
        Date tmp=new Date();
        return Long.toString(tmp.getTime());
    }
    public static String getDate(){
        Date tmp=new Date();
        return tmp.toString();
    }
}
