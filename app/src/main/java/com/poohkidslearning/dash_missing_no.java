package com.poohkidslearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dash_missing_no extends AppCompatActivity {
Toolbar toolbar;
TextView texttitle;
/*LinearLayout asc1;*/
TextView asc,desc;
RecyclerView recycler,recycler1;
ArrayList<missing_recycler_structure> arrmissing=new ArrayList<>();
ArrayList<missing_recycler_structure>arrmissingDes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_missing_no);
        desc=findViewById(R.id.desc1);
        asc=findViewById(R.id.asc);
        recycler=findViewById(R.id.recycler);
        recycler1=findViewById(R.id.recycler1);
        toolbar=findViewById(R.id.toolbar);
        texttitle=findViewById(R.id.texttitle);
        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dash_missing_no.this,missing_ascending_order.class);
                startActivity(i);
            }
        });
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dash_missing_no.this,missing_desc_order.class);
               startActivity(i);
            }
        });
        toolbar.setBackgroundColor(Color.parseColor("#13867D"));
        texttitle.setText("MISSING NO");
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#13867D"));
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
             //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);
        }

        arradd("1");
        arradd("2");
        arradd("3");
        arradd("4");
        arradd("5");
        arradd("6");
        arradd("7");

      for(int i=7;i>=1;i--){
          arrdec(""+i);
      }
      recycler.setLayoutManager(new GridLayoutManager(this,arrmissing.size()));
      recycler1.setLayoutManager(new GridLayoutManager(this,arrmissingDes.size()));
        missing_recycler_adapter adapter=new missing_recycler_adapter(this,arrmissing);
        missing_recycler_adapter2 adapter2=new missing_recycler_adapter2(this,arrmissingDes);
        recycler.setAdapter(adapter);
        recycler1.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            onBackPressed();
        }
        return true;
    }
    public void arradd(String text){
       missing_recycler_structure missing=new missing_recycler_structure();
        missing.text=text;
        arrmissing.add(missing);
    }
    public void arrdec(String text){
        missing_recycler_structure missing1=new missing_recycler_structure();
        missing1.text=text;
        arrmissingDes.add(missing1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
