package com.linlif.checkboxselect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
	
    private Context mContext;
    protected List<Bean> mData;
    private List<Type> typeList = new ArrayList<>();

    //构造方法
    public RecyclerViewAdapter(Context context, List<Bean> data) {
        //成员变量进行赋值
        this.mContext = context;
        this.mData = data;
        //initData();
    }

    public void initData() {
        typeList.clear();
        for (Bean enlist : mData) {
            Type type = null;
            if (enlist.isChecked()) {
                type = Type.Checked;
            } else {
                type = Type.UnCheck;
            }
            typeList.add(type);
        }

    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
        void onCheck(View view, int position, boolean isChecked);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /*
            创建ViewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    
    /*
            绑定ViewHolder的数据
     */
    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int pos) {

        myViewHolder.select.setChecked(mData.get(pos).isChecked());

        if (mOnItemClickListener != null) {

            myViewHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int layoutPosition = myViewHolder.getLayoutPosition();

                    mOnItemClickListener.onCheck(myViewHolder.select ,layoutPosition ,isChecked);
//                    if (isChecked) {
//                        typeList.set(layoutPosition, Type.Checked);
//                    } else {
//                        typeList.set(layoutPosition, Type.UnCheck);
//                    }
                }
            });

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(v, pos);
                }
            });
            myViewHolder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(myViewHolder.button1, layoutPosition);
                }
            });

            //long click
            myViewHolder.button2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(myViewHolder.button2, layoutPosition);
                    return false;
                }
            });
        }
    }
    
	@Override
	public int getItemCount() {
		return mData.size();
	}

    public enum Type {
        Checked, UnCheck
    }
	
}


class MyViewHolder extends ViewHolder {

    CheckBox select;
    Button button1;
     Button button2;
    public MyViewHolder(View view) {
        super(view);
        select = (CheckBox) view.findViewById(R.id.checkbox);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
    }


}