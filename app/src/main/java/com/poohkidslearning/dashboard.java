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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {
Toolbar toolbar;
TextView texttitle;
RelativeLayout number_box,table_box;
    RecyclerView recyclerView;
    ArrayList<dash_recycler_structure> arrStructureRecyclerDash=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        number_box=findViewById(R.id.number_box);
        number_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dashboard.this,dash_number_learn.class);
                startActivity(i);
            }
        });
        table_box=findViewById(R.id.table_box);
        table_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dashboard.this,table_dashboard.class);
                startActivity(i);
            }
        });
        recyclerView=findViewById(R.id.recycler);
        toolbar=findViewById(R.id.toolbar);
        texttitle=findViewById(R.id.texttitle);
        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#27AE61"));
        texttitle.setText("DASHBOARD");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#27AE61"));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            /* getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);*/
        }
        arrAdd(R.drawable.dash_matchno,R.drawable.ic_match_the_no,"Match the No");
        arrAdd(R.drawable.dash_counting,R.drawable.ic_countdown,"Counting");
        arrAdd(R.drawable.dash_missingno,R.drawable.ic_missing_no,"Missing no");
        arrAdd(R.drawable.dash_shadow,R.drawable.ic_shadow,"   Match the"+
                                                                           "\n   Real Images");

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dash_recycler_adapter adapter=new dash_recycler_adapter(this,arrStructureRecyclerDash);
        recyclerView.setAdapter(adapter);
    }

   /* @Override
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
    }*/

    public void arrAdd(int img,int img1,String name){
        dash_recycler_structure contact=new dash_recycler_structure();
        contact.img_number_b1=img;
        contact.img_numbers=img1;
        contact.txt_match=name;
        arrStructureRecyclerDash.add(contact);
    }
}
