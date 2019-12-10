package com.example.serviska.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View localRoot;

    private static RecyclerView listRecords;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        localRoot=root;
        createContent();

        return root;
    }

    private void createContent(){
        listRecords=localRoot.findViewById(R.id.listRecords);
        listRecords.setAdapter(new RecordsAdapter(MainActivity.recordManager.getRecords()));
        listRecords.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public static void updateAdapter(){
        listRecords.getAdapter().notifyDataSetChanged();
    }
}