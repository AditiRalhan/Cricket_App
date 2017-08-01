package com.example.aditi.champleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ADITI on 6/29/2017.
 */

public class CricAdapter extends BaseAdapter
{

  static ArrayList<String> result;
    int[] imgid;
    Context context;
    public static LayoutInflater inflater=null;

    public CricAdapter(ArrayList<String> result, int[] imgid, Context context)
    {
        this.result = result;
        this.imgid = imgid;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount()
    {
        return result.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }


    public class Holder
    {
        ImageView img2;
        TextView tv2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holder holder=new Holder();
        View rowview;
        rowview = inflater.inflate(R.layout.values,null);
        holder.tv2 = (TextView) rowview.findViewById(R.id.topictext2);
        holder.img2 = (ImageView) rowview.findViewById(R.id.topicimg2);
        holder.tv2.setText(result.get(position));
        System.out.println(result);
        holder.img2.setImageResource(imgid[position]);

        return rowview;
    }
}
