package com.example.serviska.engine;

import com.example.serviska.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private List<Record> records=new ArrayList<>();
    private static int nextId=0;

    public static int getNextId() {
        return nextId++;
    }

    public void addRecord(Record R){
        if(R==null)return;
        records.add(R);
        HomeFragment.updateAdapter();
    }

    public void updateRecord(Record R) {
        if (records.contains(R)) {
            records.remove(R);
            records.add(R);
        } else
            addRecord(R);
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
}
