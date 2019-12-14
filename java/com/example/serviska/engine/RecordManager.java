package com.example.serviska.engine;

import android.content.Context;

import com.example.serviska.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private List<Record> records=new ArrayList<>();
    private static int nextId=0;
    private Context context;

    private FileManager FileManager;

    public RecordManager(Context C){
        context=C;
        FileManager=new FileManager(C,getRecords());
    }

    public static int getNextId() {
        return nextId++;
    }

    public void addRecord(Record R){
        if(R==null)return;
        records.add(R);
    }

    public void updateRecords(Record R) {
        if (!findAndReplaceRecord(R)) {
            addRecord(R);
        }
        updateFileRecordDB();
        HomeFragment.updateAdapter();
    }

    public Record getRecord(int index){
        return records.get(index);
    }

    public Record getLastRecord(){
        if(records.size()==0)return null;
        return records.get(records.size()-1);
    }

    public List<Record> getRecords(){
        return records;
    }

    private boolean findAndReplaceRecord(Record R){
        Record tmp=findRecordByID(R);
        if(tmp==null)return false;
        records.remove(tmp);
        records.add(R);
        return true;
    }

    private Record findRecordByID(Record R){
        for (Record record : records){
            if(record.ID.compareTo(R.ID)==0)return record;
        }
        return null;
    }

    public void updateFileRecordDB(){
        FileManager.updateRecordsFiles();
    }
}
