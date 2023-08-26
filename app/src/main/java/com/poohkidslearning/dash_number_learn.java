package com.poohkidslearning;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class dash_number_learn extends AppCompatActivity {
Toolbar toolbar;
TextView texttitle;
ViewPager viewPager;
Button btn_pre,btn_next;
ArrayList<dash_recycler_structure> arrStructureRecyclerDash=new ArrayList<>();
    ArrayList<suitcaseNumberLearning> numberLearningArrayList = new ArrayList<>();
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_number_learn);
        toolbar=findViewById(R.id.toolbar);
        texttitle=findViewById(R.id.texttitle);

       viewPager=findViewById(R.id.viewPager);

        btn_pre=findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentpage=viewPager.getCurrentItem()-1;
                if (currentpage<numberLearningArrayList.size()){
                    viewPager.setCurrentItem(currentpage);
                }
            }
        });
        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentpage=viewPager.getCurrentItem()+1;
                if (currentpage<numberLearningArrayList.size()){
                    viewPager.setCurrentItem(currentpage);
                }
            }
        });
        dbHelper =dbHelper.getDB(this);
        if (!dbHelper.checkDb()){
            dbHelper.createDB(this);
        }
        dbHelper.openDB();


        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#429C91"));
        texttitle.setText("NUMBER");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#429C91"));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            /* getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);*/
        }
        numberLearningArrayList=dbHelper.getImage();
        view_pager_number_learn adapter=new view_pager_number_learn(this,numberLearningArrayList);
        viewPager.setAdapter(adapter);
        for(int i=0;i<numberLearningArrayList.size();i++){
            Log.d("VALUES",numberLearningArrayList.get(i).count_number+"");
        }
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
   /* public void arrAdd(int img,int img1,String name){
        dash_recycler_structure contact=new dash_recycler_structure();
        contact.img_number_b1=img;
        contact.img_numbers=img1;
        contact.txt_match=name;
        arrStructureRecyclerDash.add(contact);
    }*/
}
