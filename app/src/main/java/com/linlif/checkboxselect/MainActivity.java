package com.linlif.checkboxselect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    public void listview (View v){
        Intent intent = new Intent(this , ListViewActivity.class);
        startActivity(intent);
    }

    public void recyclerview (View v){
        Intent intent = new Intent(this , RecyclerViewActivity.class);
        startActivity(intent);
    }
}

