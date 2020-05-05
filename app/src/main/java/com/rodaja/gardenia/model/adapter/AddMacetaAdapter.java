package com.rodaja.gardenia.model.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.rodaja.gardenia.R;

import java.util.List;

public class AddMacetaAdapter implements ListAdapter {
    private List<String> listWifi;
    private Context contexto;

    public AddMacetaAdapter(List<String> listWifi, Context contexto) {
        this.listWifi = listWifi;
        this.contexto = contexto;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

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
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater lf= LayoutInflater.from(contexto);
        View vista=lf.inflate(R.layout.activity_add_flowerpot_adapter, viewGroup, false);

        TextView tvWifi=(TextView)vista.findViewById(R.id.tvWifi);

        tvWifi.setText(listWifi.get(i));

        return vista;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
