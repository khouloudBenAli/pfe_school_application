package com.example.school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassesAdapter_schedule extends BaseAdapter {
    Context context ;
    String listdays[];
    int listImages[];
    LayoutInflater inflater;

    public  ClassesAdapter_schedule (Context ctx, String[] dayslist , int[] images) {

        this.context= ctx ;
        this.listdays = dayslist ;
        this.listImages = images ;
        inflater=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return listdays.length;
    }

    @Override
    public Object getItem(int i)
    {
        return null;
        //return listdays.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
        //return i ;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.list_chape , null);
        TextView textView= (TextView) convertView.findViewById(R.id.tv_day);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ImageIcon);
        textView.setText(listdays[i]);
        imageView.setImageResource(listImages[i]);

        return convertView ;
    }
}
