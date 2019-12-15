package com.example.serviska.engine;

import android.content.Context;
import android.widget.Toast;

import com.example.serviska.ui.home.HomeFragment;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileManager
{
    Context context;
    private final String rootFile="rootDB.dtb";
    private List<String> files;
    private List<Record> recordList;

    public FileManager(Context c, List<Record> rclist){
        context=c;
        files=new ArrayList<>();
        recordList=rclist;
    }

    private boolean checkRoot(){
        if(FileLoader.ExistLocalFile(context,rootFile)){
            return loadRoot();
        }
        else createRoot();
        return false;
    }

    private void createRoot(){
        FileSaver.SaveFile(context,rootFile,"");
    }

    private void saveRoot(){
        String data="";

        for (String file : files){
            data +=file;
            data += "\n";
        }
        FileSaver.SaveFile(context,rootFile,data);
    }

    private boolean loadRoot(){
        String data=FileLoader.LoadFile(context,rootFile);
        if(data==null || data.isEmpty())return false;

        parseRootData(data);
        return true;
    }

    private void parseRootData(String data){
        for (String file : data.split("\n") ){
            files.add(file);
        }
    }

    public void reloadRecordFiles(){
        if(!checkRoot())return;

        recordList.clear();
        Record tmp=null;
        for(String file : files){
            if((tmp=loadRecordFile(file))!=null){
                recordList.add(tmp);
            }
            tmp=null;
        }
        Toast.makeText(context,"File database load success! Found: "+(recordList.size()),Toast.LENGTH_SHORT).show();
    }

    private Record loadRecordFile(String file){
        String data=FileLoader.LoadFile(context,file);
        if(data==null || data.isEmpty())return null;
        return parseRecordFile(data);
    }

    private Record parseRecordFile(String recordData){
        return FileResolverHelper.convertFileToRecord(recordData);
    }

    private void updateFiles(){
        for(Record R : recordList){
            FileSaver.SaveFile(context,R.getFileRecordName(),R.getDataForRecordFile());
        }
    }

    public void updateRecordsFiles(){
        files.clear();
        for (Record R : recordList){
            files.add(R.getFileRecordName());
        }
        saveRoot();
        updateFiles();
    }
}
