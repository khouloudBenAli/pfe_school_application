package com.example.school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassesAdapter_class_list extends BaseAdapter {
    Context context ;
    String listclass[];
    int listImages[];
    LayoutInflater inflater;

    public  ClassesAdapter_class_list (Context ctx, String[] classlist , int[] images) {

        this.context= ctx ;
        this.listclass = classlist ;
        this.listImages = images ;
        inflater=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return listclass.length;
    }

    @Override
    public Object getItem(int i)
    {
        return null;
        //return listclass.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
        //return i ;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.list_chape_classes , null);
        TextView textView= (TextView) convertView.findViewById(R.id.tv_class);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ImageIconClass);
        textView.setText(listclass[i]);
        imageView.setImageResource(listImages[i]);

        return convertView ;
    }
}

