package com.threelinksandonedefense.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-6-24.
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<String> listda;
    Context context;
    LayoutInflater inflater;
    public ListViewAdapter(Context context, ArrayList<String> listda) {
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.listda=listda;
    }

    @Override
    public int getCount() {
        return listda.size();
    }

    @Override
    public Object getItem(int position) {
        return listda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     ViewHolder holder;
        if (null == convertView){
            convertView = inflater.inflate(R.layout.lay,null);
            holder =new ViewHolder(convertView,position);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder{
        public ViewHolder(View view,int pisition){

        }
    }

}

