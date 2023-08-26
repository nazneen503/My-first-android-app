package com.poohkidslearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

public class table_dashboard extends AppCompatActivity {
    LinearLayout table_2;
    Toolbar toolbar;
    TextView texttitle;
    RecyclerView recyclertable;
    ArrayList<structure_dash_recycler_table> arrtable=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_dashboard);

        recyclertable=findViewById(R.id.recyclertable);

        toolbar=findViewById(R.id.toolbar);
        texttitle=findViewById(R.id.texttitle);
        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#9B2AAE"));
        texttitle.setText("TABLES");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#9B2AAE"));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);
        }
        table_2=findViewById(R.id.table_2);
        /*table_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(table_dashboard.this,dash_table_learn.class);
                startActivity(i);
            }
        });*/
        arrAdd(R.drawable.two_table_dash_box,"Table of Two");
        arrAdd(R.drawable.three_table_dash_box,"Table of Three");
        arrAdd(R.drawable.four_table_dash_box,"Table of Four");
        arrAdd(R.drawable.five_table_dash_box,"Table of Five");
        arrAdd(R.drawable.two_table_dash_box,"Table of Six");
        arrAdd(R.drawable.three_table_dash_box,"Table of Seven");
        arrAdd(R.drawable.four_table_dash_box,"Table of Eight");
        arrAdd(R.drawable.five_table_dash_box,"Table of Nine");
        arrAdd(R.drawable.two_table_dash_box,"Table of Ten");
        recyclertable.setLayoutManager(new GridLayoutManager(this,1));
        dash_recycler_table_adapter adapter=new dash_recycler_table_adapter(this,arrtable);
        recyclertable.setAdapter(adapter);

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

    public void arrAdd(int table_2,String texttableno){
        structure_dash_recycler_table contact=new structure_dash_recycler_table();
        contact.table_2=table_2;
        contact.texttableno=texttableno;
        arrtable.add(contact);

    }
}
