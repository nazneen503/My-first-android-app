package com.poohkidslearning;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class match_real_img extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {
Toolbar toolbar;
TextView texttitle;
DBHelper dbHelper;
ViewPager viewPagermatch;
Button btn_pre,btn_next;
int count=0;
    RelativeLayout lno1,lno2,lno3;
    TextView no1,no2,no3,lb1,lb2,lb3,result1,result2,result3;

    //ArrayList<dash_recycler_structure> arrStructureRecyclerDash=new ArrayList<>();
    ArrayList<suitcase_match_realimages> matchRealimagesArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_real_img);
        toolbar=findViewById(R.id.toolbar);
        texttitle=findViewById(R.id.texttitle);



        lb1=findViewById(R.id.lb1);
        lb1.setOnTouchListener(this);
        lb1.setTag("Number1");
        lb2=findViewById(R.id.lb2);
        lb2.setOnTouchListener(this);
        lb2.setTag("Number2");
        lb3=findViewById(R.id.lb3);
        lb3.setOnTouchListener(this);
        lb3.setTag("Number3");


        findViewById(R.id.lno1).setOnDragListener(this);
        findViewById(R.id.lno2).setOnDragListener(this);
        findViewById(R.id.lno3).setOnDragListener(this);

        result1=findViewById(R.id.result1);
        result2=findViewById(R.id.result2);
        result3=findViewById(R.id.result3);
        no1=findViewById(R.id.no1);
        no2=findViewById(R.id.no2);
        no3=findViewById(R.id.no3);






       // viewPagermatch=findViewById(R.id.viewPagermatch);
        btn_pre=findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* int currentpage=viewPagermatch.getCurrentItem()-1;
                if (currentpage<matchRealimagesArrayList.size()){
                    viewPagermatch.setCurrentItem(currentpage);
                }*/
            }
        });
        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentpage=viewPagermatch.getCurrentItem()+1;
               /* if (currentpage<matchRealimagesArrayList.size()){
                    viewPagermatch.setCurrentItem(currentpage);
                }*/
            }
        });


        dbHelper=dbHelper.getDB(this);


        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#FE953A"));
        texttitle.setText("MATCH REAL IMAGE");
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#FE953A"));
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            /* getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);*/
        }
        if (!dbHelper.checkDb()){
            dbHelper.createDB(this);

        }
        dbHelper.openDB();
        matchRealimagesArrayList =dbHelper.getMatch();
        suitcase_match_realimages suitcaseMatchRealimages=new suitcase_match_realimages();
        for(int i=0;i<matchRealimagesArrayList.size();i++){
            Log.d("pattern",matchRealimagesArrayList.get(i).pattern1+"");
        }
        SetNumber();
       /* view_pager_match_real_images adapter=new view_pager_match_real_images(this,matchRealimagesArrayList);
        viewPagermatch.setAdapter(adapter);*/

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

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();

        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:

                if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                view.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                //v.getBackground().clearColorFilter();
                view.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                view.invalidate();
                View vw = (View) dragEvent.getLocalState();
                if(view.getId()==R.id.lno1)

                {
                    ViewGroup owner = (ViewGroup) vw.getParent();
                    owner.removeView(vw);
                    {
                        result1.setVisibility(View.VISIBLE);
                        no1.setVisibility(View.GONE);
                        /*RelativeLayout container = (RelativeLayout) v;
                        container.addView(vw);
//Add the dragged view
                        //result1.setVisibility(View.VISIBLE);
                        no1.setVisibility(View.GONE);
                    }
                }
                else if(view.getId()==R.id.lno2)

                {
                    if(vw.getId()==R.id.lb2) {
                     /*   ViewGroup owner = (ViewGroup) vw.getParent();
                        owner.removeView(vw);*/

                        /*RelativeLayout container = (RelativeLayout) v;
                        container.addView(vw);*///Add the dragged view
                        result2.setVisibility(View.VISIBLE);
                        no2.setVisibility(View.GONE);
                    }
                }
                else if(view.getId()==R.id.lno3)

                {
                    if(vw.getId()==R.id.lb3) {
                        /*vw.setVisibility(View.GONE);*/
                        /*RelativeLayout container = (RelativeLayout) v;
                        container.addView(vw);*///Add the dragged view
                        //result3.setVisibility(View.VISIBLE);
                        result3.setVisibility(View.VISIBLE);
                         no3.setVisibility(View.GONE);
                    }
                }


                vw.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                view.invalidate();
                ((View)dragEvent.getLocalState()).setVisibility(View.VISIBLE);

        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(view);

        view.startDrag(data
                , dragshadow
                , view
                , 0
        );
        return true;
    }
    public void SetNumber(){

        lb1.setText(matchRealimagesArrayList.get(count).pattern1);
        lb2.setText(matchRealimagesArrayList.get(count).pattern2);
        lb3.setText(matchRealimagesArrayList.get(count).pattern3);

       String[] lb11={matchRealimagesArrayList.get(count).pattern1,matchRealimagesArrayList.get(count).pattern2,matchRealimagesArrayList.get(count).pattern3};
       Collections.shuffle(Arrays.asList(lb11));

      /* TextView[] arrText={lb1,lb2,lb3};
       int k;
        k = 0;
        for(TextView i:arrText){
           if(k==0 || k==1 || k==2){
               i.setText(lb11[k]);
               i.setTag("Result");
           }
           else
           {
               i.setText(lb11[k]);
               i.setTag("noResult");
           }
           k++;
       }*/
        result1.setText(lb11[0]);
        result2.setText(lb11[1]);
        result3.setText(lb11[2]);
        no1.setText(lb11[0]);
        no2.setText(lb11[1]);
        no3.setText(lb11[2]);

    }
}