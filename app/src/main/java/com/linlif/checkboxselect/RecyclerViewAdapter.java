package com.linlif.checkboxselect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
	
    private Context mContext;
    protected List<Bean> mData;
    private HashMap<Bean, Boolean> hashMap ;

    public RecyclerViewAdapter(Context context, List<Bean> data) {
        this.mContext = context;
        this.mData = data;
        selectedInit(false);
    }
     //初始化 默认为False
    public void selectedInit(Boolean selected){
        if(hashMap == null)
            hashMap = new HashMap<Bean, Boolean>();
        for(Bean t : mData){
            hashMap.put(t, selected);

        }
        notifyDataSetChanged();
    }

    public int getSelected(){
        int i = 0;
        for (Bean enlist : mData){
            if (hashMap.get(enlist) ){
                i++;
            }
        }
        return i;
    }

    public void initData(Bean data ,Boolean selected) {
        hashMap.put(data ,selected);
        //notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
        void onCheck(View view, int position, boolean isChecked);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    //创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view,mOnItemClickListener);

        return myViewHolder;
    }
    
    //绑定ViewHolder的数据
    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int pos) {

        if (mOnItemClickListener != null) {
            myViewHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onCheck(myViewHolder.select ,layoutPosition ,isChecked);
                    hashMap.put(mData.get(pos), isChecked);

                }
            });
            myViewHolder.item_layout.setClickable(true);
            myViewHolder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, pos);
                }
            });
            myViewHolder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(myViewHolder.button1, pos);
                }
            });

            //long click
            myViewHolder.button2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(myViewHolder.button2, pos);
                    return false;
                }
            });

            //注意先后顺序
            if (hashMap.get(mData.get(pos))){
                myViewHolder.select.setChecked(true);
            }else{
                myViewHolder.select.setChecked(false);
            }
        }
    }
    
	@Override
	public int getItemCount() {
		return mData.size();
	}

}


class MyViewHolder extends ViewHolder{

    CheckBox select;
    Button button1;
    Button button2;
    LinearLayout item_layout;
    RecyclerViewAdapter.OnItemClickListener mOnItemClickListener;
    public MyViewHolder(View view , RecyclerViewAdapter.OnItemClickListener  mOnItemClickListener) {
        super(view);
        this.mOnItemClickListener = mOnItemClickListener;
        select = (CheckBox) view.findViewById(R.id.checkbox);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        item_layout = (LinearLayout) view.findViewById(R.id.item_layout);
    }


}