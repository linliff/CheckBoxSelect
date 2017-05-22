package com.linlif.checkboxselect;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lin on 2017/1/8.
 */
public class ListViewAdapter<T> extends BaseAdapter{

    private Context mContext;
    protected List<T> mData;
    private HashMap<T, Boolean> hashMap ;

    public ListViewAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
        selectedInit(false);
    }


    //初始化 默认为False
    public void selectedInit(Boolean selected){
        if(hashMap == null)
            hashMap = new HashMap<T, Boolean>();
        for(T t : mData){
            hashMap.put(t, selected);

        }
        notifyDataSetChanged();
    }

    public int getSelected(){
        int i = 0;
        for (T enlist : mData){
            if (hashMap.get(enlist) ){
                i++;
            }
        }
        return i;
    }

    public void initData(T data ,Boolean selected) {
        hashMap.put(data ,selected);
        //notifyDataSetChanged();
    }



    private ListItemButtonClickListener mOnItemClickListener;

    public void setOnListItemClickListener(ListItemButtonClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v , position);
            }
        });

        holder.button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.onItemLongClick(v , position);
                return false;
            }
        });

        holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mOnItemClickListener.onCheck(buttonView ,position ,isChecked);
                hashMap.put(mData.get(position), isChecked);
            }
        });

       if (hashMap.get(mData.get(position))){
           holder.select.setChecked(true);
       }else{
           holder.select.setChecked(false);
       }
        return convertView;
    }


    public interface ListItemButtonClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
        void onCheck(View view, int position, boolean isChecked);
    }

}

class ViewHolder  {
    CheckBox select;
    Button button1;
    Button button2;
    public ViewHolder(View convertView) {
        select = (CheckBox) convertView.findViewById(R.id.checkbox);
        button1 = (Button) convertView.findViewById(R.id.button1);
        button2 = (Button) convertView.findViewById(R.id.button2);
    }
}
