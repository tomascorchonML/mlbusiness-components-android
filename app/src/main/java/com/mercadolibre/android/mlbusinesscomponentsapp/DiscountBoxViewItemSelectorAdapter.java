package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DiscountBoxViewItemSelectorAdapter extends BaseAdapter {

    private List<String> itemsQuantityList;

    public DiscountBoxViewItemSelectorAdapter() {
        itemsQuantityList = new ArrayList<>();
        itemsQuantityList.add("1");
        itemsQuantityList.add("2");
        itemsQuantityList.add("3");
        itemsQuantityList.add("4");
        itemsQuantityList.add("5");
        itemsQuantityList.add("6");
    }

    @Override
    public int getCount() {
        return itemsQuantityList == null ? 0 : itemsQuantityList.size() ;
    }

    @Override
    public String getItem(int position) {
        return position < 0 ? null : itemsQuantityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView siteId = view.findViewById(android.R.id.text1);
        siteId.setText(getItem(position));
        return view;
    }
}
