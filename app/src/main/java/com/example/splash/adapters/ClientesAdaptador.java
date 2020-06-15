package com.example.splash.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.splash.R;
import com.example.splash.models.Clientes;

import java.util.List;

public class ClientesAdaptador extends ArrayAdapter<Clientes> {
    Context context;
    private class ViewHolder {
        TextView phone;
        TextView nickname;

        private ViewHolder() {
        }
    }
    public ClientesAdaptador(Context context, List<Clientes> items) {
        super(context, 0, items);
        this.context = context;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Clientes rowItem = (Clientes) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_clientes, null);
            holder = new ViewHolder();
            holder.phone = (TextView) convertView.findViewById(R.id.phone);
            holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.phone.setText(rowItem.phone);
        holder.nickname.setText(rowItem.nickname);
        return convertView;
    }
}