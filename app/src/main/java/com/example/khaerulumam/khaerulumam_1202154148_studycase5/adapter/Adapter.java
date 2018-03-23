package com.example.khaerulumam.khaerulumam_1202154148_studycase5.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.khaerulumam.khaerulumam_1202154148_studycase5.R;
import com.example.khaerulumam.khaerulumam_1202154148_studycase5.model.Data;

import java.util.List;

/**
 * Created by Umam on 3/22/2018.
 */

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView a = (TextView) convertView.findViewById(R.id.nama);
        TextView b = (TextView) convertView.findViewById(R.id.deskripsi);
        TextView c = (TextView) convertView.findViewById(R.id.jumlah);

        Data data = items.get(position);

        a.setText(data.getName());
        b.setText(data.getDeskripsi());
        c.setText(data.getJumlah());

        return convertView;
    }
}
