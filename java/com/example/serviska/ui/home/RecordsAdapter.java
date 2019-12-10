package com.example.serviska.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviska.R;
import com.example.serviska.engine.Record;

import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView =(TextView)itemView.findViewById(R.id.txtRecordInfo);
        }
    }


    private List<Record> localRecords;
    public RecordsAdapter(List<Record> reclist){
        localRecords=reclist;
    }

    @NonNull
    @Override
    public RecordsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recordsView = inflater.inflate(R.layout.item_record, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(recordsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsAdapter.ViewHolder holder, int position) {
        Record record=localRecords.get(position);
        holder.nameTextView.setText(record.toString());
    }

    @Override
    public int getItemCount() {
        return localRecords.size();
    }
}
