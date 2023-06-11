package com.example.school;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ClassesAdapter_historique extends BaseAdapter{

    Context context ;
    String listsalle[];
    int listImages[];
    LayoutInflater inflater;

    public  ClassesAdapter_historique (Context ctx, String[] sallelist , int[] images) {

        this.context= ctx ;
        this.listsalle = sallelist ;
        this.listImages = images ;
        inflater=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return listsalle.length;
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
        convertView = inflater.inflate(R.layout.list_chape_salle , null);
        TextView textView= (TextView) convertView.findViewById(R.id.tv_salle);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ImageIconSalle);
        textView.setText(listsalle[i]);
        imageView.setImageResource(listImages[i]);

        return convertView ;
    }

}
