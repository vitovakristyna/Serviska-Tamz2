package com.example.serviska.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.serviska.R;
import com.example.serviska.engine.Record;
import java.util.List;

public class RecordsAdapter extends ArrayAdapter<Record> {

    Context context;
    int layoutResourceId;
    List<Record> records = null;


    public RecordsAdapter(Context context, int layoutResourceId, List<Record> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.records = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EntryHolder();
            holder.txtInfo= row.findViewById(R.id.txtRecordInfoEntry);

            row.setTag(holder);
        }
        else
        {
            holder = (EntryHolder)row.getTag();
        }

        Record entry = records.get(position);
        holder.txtInfo.setText(entry.toString());

        return row;
    }

    static class EntryHolder
    {
        TextView txtInfo;
    }

}
