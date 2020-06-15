package com.example.splash.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.splash.R;
import com.example.splash.models.Clientes;

import java.util.List;

public class ClientesAdaptador extends ArrayAdapter<Clientes> {
    Context context;
    ImageLoader queue;

    private class ViewHolder {
        TextView phone;
        TextView nickname;
        NetworkImageView image;

        private ViewHolder() {
        }
    }
    public ClientesAdaptador(Context context, List<Clientes> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue = _queue;
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
            holder.image = (NetworkImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.phone.setText(rowItem.phone);
        holder.nickname.setText(rowItem.nickname);
        holder.image.setImageUrl(rowItem.urlImage,this.queue);
        return convertView;
    }
}