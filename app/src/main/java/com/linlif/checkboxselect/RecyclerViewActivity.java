package com.linlif.checkboxselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener , CompoundButton.OnCheckedChangeListener {

    private RecyclerView recyclerView;
    private CheckBox selectAll;
    private TextView selectNum;
    private List<Bean> mData;
    private RecyclerViewAdapter checkBoxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.list_item);
        selectAll = (CheckBox) findViewById(R.id.select_all);
        selectNum = (TextView) findViewById(R.id.select_num);
        selectAll.setOnCheckedChangeListener(this);

        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Bean bean = new Bean(i +"" , false);
            mData.add(bean);
        }
        checkBoxAdapter = new RecyclerViewAdapter(this, mData);
        checkBoxAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(checkBoxAdapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));

    }


    @Override
    public void onItemClick(View view, int position) {
        int id = view.getId();
        Bean bean = mData.get(position);
        switch (id){
            case R.id.item_layout:
                Toast.makeText(RecyclerViewActivity.this ,  bean.getName()+"itemClick" ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.button1:
                Toast.makeText(RecyclerViewActivity.this , bean.getName() +"button1Click" ,Toast.LENGTH_SHORT).show();
                break;
        }


    }

    @Override
    public void onItemLongClick(View view, int position) {
        Bean bean = mData.get(position);
        Toast.makeText(RecyclerViewActivity.this ,bean.getName() + "button2Click" ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheck(View view, int position, boolean isChecked) {
        Bean bean = mData.get(position);
        checkBoxAdapter.initData(bean ,isChecked);
        selectNum.setText(checkBoxAdapter.getSelected()+"");

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checkBoxAdapter.selectedInit(isChecked);
        selectNum.setText(mData.size()+"");
    }
}

class Bean{
    private String name;
    private boolean isChecked;//实际没用到

    public Bean(String s, boolean b) {
        this.name =s;
        this.isChecked = b;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
