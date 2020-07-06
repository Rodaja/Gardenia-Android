package com.rodaja.gardenia.model.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.rodaja.gardenia.R;

import java.util.List;

public class WifiAdapter extends BaseAdapter {
    private List<String> listWifi;
    private Context contexto;

    public WifiAdapter(List<String> listWifi, Context contexto) {
        this.listWifi = listWifi;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return listWifi.size();
    }

    @Override
    public Object getItem(int i) {
        return listWifi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater lf= LayoutInflater.from(contexto);
        View vista=lf.inflate(R.layout.activity_add_flowerpot_adapter, viewGroup, false);

        TextView tvWifi=(TextView)vista.findViewById(R.id.tvWifi);

        tvWifi.setText(listWifi.get(i));

        return vista;

    }
}
