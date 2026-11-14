package com.dineops.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/** Keeps restaurant selection behavior while giving each entry a readable card. */
public class RestaurantAdapter extends ArrayAdapter<String> {
    public RestaurantAdapter(Context context, List<String> restaurants) {
        super(context, R.layout.restaurant_row, restaurants);
    }

    @Override public View getView(int position, View recycled, ViewGroup parent) {
        View row = recycled == null ? LayoutInflater.from(getContext()).inflate(
            R.layout.restaurant_row, parent, false) : recycled;
        String name = getItem(position);
        ((TextView) row.findViewById(R.id.restaurant_name)).setText(name);
        ((TextView) row.findViewById(R.id.restaurant_detail)).setText(
            "KFC".equals(name) ? getContext().getString(R.string.message_48) : getContext().getString(R.string.message_49));
        return row;
    }
}
