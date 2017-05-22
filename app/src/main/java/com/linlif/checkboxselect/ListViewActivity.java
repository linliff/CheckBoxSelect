package com.linlif.checkboxselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2017/1/8.
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,CompoundButton.OnCheckedChangeListener ,ListViewAdapter.ListItemButtonClickListener {

    private ListView listView;
    private CheckBox selectAll;
    private TextView selectNum;
    private List<Bean> mData;
    private ListViewAdapter listViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = (ListView) findViewById(R.id.list_item);
        selectAll = (CheckBox) findViewById(R.id.select_all);
        selectNum = (TextView) findViewById(R.id.select_num);
        selectAll.setOnCheckedChangeListener(this);
        listView.setOnItemClickListener(this);

        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Bean bean = new Bean(i +"" , false);
            mData.add(bean);
        }
        listViewAdapter = new ListViewAdapter(this, mData);
        listView.setAdapter(listViewAdapter);
        listViewAdapter.setOnListItemClickListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listViewAdapter.selectedInit(isChecked);
        selectNum.setText(mData.size()+"");

    }

    @Override
    public void onItemClick(View view, int position) {
        Bean bean = mData.get(position);
        Toast.makeText(ListViewActivity.this , bean.getName()+"button1" ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Bean bean = mData.get(position);
        Toast.makeText(ListViewActivity.this , bean.getName()+"button2" ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheck(View view, int position, boolean isChecked) {
        Bean bean = mData.get(position);
        listViewAdapter.initData(bean ,isChecked);
        selectNum.setText(listViewAdapter.getSelected()+"");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bean bean = mData.get(position);
        Log.e("linlifff", "itemclick");
        Toast.makeText(ListViewActivity.this , bean.getName()+"item" ,Toast.LENGTH_SHORT).show();
    }
}
