package com.example.serviska.engine;

import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private List<Record> records=new ArrayList<>();


    public void addRecord(Record R){
        if(R==null)return;
        records.add(R);
    }
}
