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
        HomeFragment.updateAdapter();
        records.add(R);
    }

    public Record getLastRecord(){
        if(records.size()==0)return null;
        return records.get(records.size()-1);
    }

    public List<Record> getRecords(){
        return records;
    }
}
