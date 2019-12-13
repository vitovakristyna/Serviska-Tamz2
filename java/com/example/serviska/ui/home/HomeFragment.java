package com.example.serviska.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviska.MainActivity;
import com.example.serviska.R;
import com.example.serviska.engine.Record;
import com.example.serviska.manageRecordActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static View localRoot;

    private static ListView listRecords;
    public static final int RESULT_OK=-1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        localRoot=root;
        createContent();

        return root;
    }

    private void createContent(){
        listRecords=localRoot.findViewById(R.id.listRecords);
        listRecords.setAdapter(new RecordsAdapter(getContext(),R.layout.item_record,MainActivity.recordManager.getRecords()));

        listRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Record record=(Record) parent.getItemAtPosition(position);
                startRecordActivityStart(record);
            }
        });
    }

    public static void updateAdapter() {
        synchronized(listRecords.getAdapter()){
            listRecords.getAdapter().notifyAll();
            localRoot.invalidate();
        }
    }

    public void startRecordActivityStart(Record R){
        Intent intent = new Intent(getContext(), manageRecordActivity.class);
        intent.putExtra("ActualRecord", R);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 100:{
                if(resultCode==RESULT_OK){
                    Record tmp=(Record) data.getSerializableExtra("ActualRecord");
                    MainActivity.recordManager.updateRecord(tmp);
                    Toast.makeText(getContext(), "HomeAct handler", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /*@Override
    public void onResume() {
        super.onResume();
        updateAdapter();
    }*/

}