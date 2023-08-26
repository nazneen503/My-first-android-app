package com.poohkidslearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class dash_table_learn extends AppCompatActivity {
Toolbar toolbar;
TextView texttitle;
LinearLayout table_2;
RecyclerView recycler;
DBHelper dbHelper;
ArrayList<table_recycler_structure> arrtable=new ArrayList<>();
ArrayList<table_recycler_structure> arrStructureRecyclerDash=new ArrayList<table_recycler_structure>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_table_learn);
        recycler=findViewById(R.id.recycler);
        toolbar=findViewById(R.id.toolbar);
        texttitle=findViewById(R.id.texttitle);


        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#FC5B6A"));
        texttitle.setText("TABLES");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#FC5B6A"));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);
        }

        dbHelper=dbHelper.getDB(this);
        if(!dbHelper.checkDb()){
            dbHelper.createDB(this);
        }
        dbHelper.openDB();
        String flag1=getIntent().getStringExtra("flag");

            if(flag1.equals("0")){
                arrtable=dbHelper.gettable(2);
        }
        else if(flag1.equals("1")){
                arrtable=dbHelper.gettable(3);
            }
            else if(flag1.equals("2")){
                arrtable=dbHelper.gettable(4);
            }
            else if(flag1.equals("3")){
                arrtable=dbHelper.gettable(5);
            }
            else if(flag1.equals("4")){
                arrtable=dbHelper.gettable(6);
            }
            else if(flag1.equals("5")){
                arrtable=dbHelper.gettable(7);
            }
            else if(flag1.equals("6")){
                arrtable=dbHelper.gettable(8);
            }
            else if(flag1.equals("7")){
                arrtable=dbHelper.gettable(9);
            }
            else if(flag1.equals("8")){
                arrtable=dbHelper.gettable(10);
            }

//    for(int i=2;i<=2;i++) {
//        for(int j=1;j<=10;j++){
//            add(String.valueOf(i),String.valueOf(j), String.valueOf(i*j));
//        }
//    }
    recycler.setLayoutManager(new LinearLayoutManager(this));
    recycler_table_adapter adapter=new recycler_table_adapter(this,arrtable);
    recycler.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return false;
    }
   /* public void add(String num1, String num2, String num3){
        table_recycler_structure content=new table_recycler_structure();
        content.num1=num1;
        content.num2=num2;
        content.num3=num3;
        arrtable.add(content);
        arrStructureRecyclerDash.add(content);
    }*/
}
