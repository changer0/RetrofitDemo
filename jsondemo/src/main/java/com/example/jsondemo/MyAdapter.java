package com.example.jsondemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by lulu on 2016/11/24.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Cook> list;

    public MyAdapter(Context context, List<Cook> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.bindView(list.get(position), position);
        return convertView;
    }

    public void addAll (Collection<? extends Cook> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }
    private static class ViewHolder {

        private final ImageView image;
        private final TextView title;
        private final TextView info;

        public ViewHolder(View itemView) {
            image = ((ImageView) itemView.findViewById(R.id.item_image));
            title = ((TextView) itemView.findViewById(R.id.item_title));
            info = ((TextView) itemView.findViewById(R.id.item_info));
        }

        public void bindView(Cook cook, int position) {
            title.setText(cook.getName());
            info.setText(cook.getDescription());
            Picasso.with(image.getContext()).load("http://tnfs.tngou.net/img" + cook.getImg()).into(image);
        }

    }
}
