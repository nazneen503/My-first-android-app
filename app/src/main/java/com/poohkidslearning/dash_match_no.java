package com.poohkidslearning;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
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
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Collections;

public class dash_match_no extends AppCompatActivity {
    Toolbar toolbar;
    TextView texttitle;
    DBHelper dbHelper;
    MediaPlayer mp=new MediaPlayer();
    /*LottieAnimationView animation;*/
    RecyclerView recyclerView;
    ArrayList<Integer> arrValues=new ArrayList<>();
    ArrayList<byte[]>arrImages=new ArrayList<>();
    ArrayList<dash_quiz_recycler_structure> arrStructureRecyclerDash=new ArrayList<dash_quiz_recycler_structure>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_match_no);
        toolbar = findViewById(R.id.toolbar);
        texttitle = findViewById(R.id.texttitle);
        /*animation = findViewById(R.id.animation);*/
        setSupportActionBar(toolbar);
        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        toolbar.setBackgroundColor(Color.parseColor("#50C1CF"));
        texttitle.setText("MATCH THE NUMBER");
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#50C1CF"));
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            /* getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);*/
        }
       recyclerView=findViewById(R.id.recycler);
dbHelper=dbHelper.getDB(this);

if (!dbHelper.checkDb()){
    dbHelper.createDB(this);
}

dbHelper.openDB();

arrImages=dbHelper.getFlip(getImages());

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        Log.d("arrSize", ""+arrImages.size());
        arrAdd(R.drawable.quiz_match_no,arrImages.get(0),"A");
        arrAdd(R.drawable.quiz_match_no2,arrImages.get(1),"B");
        arrAdd(R.drawable.quiz_match_no3,arrImages.get(2),"C");
        arrAdd(R.drawable.quiz_match_no4,arrImages.get(3),"D");
        arrAdd(R.drawable.quiz_match_no,arrImages.get(0),"A");
        arrAdd(R.drawable.quiz_match_no2,arrImages.get(1),"B");
        arrAdd(R.drawable.quiz_match_no3,arrImages.get(2),"C");
        arrAdd(R.drawable.quiz_match_no4,arrImages.get(3),"D");


        recyclerView.setLayoutManager(new GridLayoutManager(dash_match_no.this,4));
        quiz_recycler_adapter adapter=new quiz_recycler_adapter(dash_match_no.this,arrStructureRecyclerDash);
        recyclerView.setAdapter(adapter);
    }
}, 4000);




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
    public void arrAdd(int linear_match,byte[] match_img,String tag){
    dash_quiz_recycler_structure contact=new dash_quiz_recycler_structure();
    contact.linear_match=linear_match;
    contact.match_img=match_img;
    contact.tag=tag;
        arrStructureRecyclerDash.add(contact);


    }
    private ArrayList<Integer> getImages(){

        ArrayList<Integer> arrIndex=new ArrayList<>();
        for(int i=1;i<19;i++){
            arrIndex.add(i);
        }
        Collections.shuffle(arrIndex);

        for(int i=0;i<4;i++){
            arrValues.add(arrIndex.get(i));
        }
        Collections.sort(arrValues);
        return arrValues;

    }
}
