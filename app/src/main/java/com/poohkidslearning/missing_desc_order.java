package com.poohkidslearning;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
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
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Collections;

public class missing_desc_order extends AppCompatActivity {
Toolbar toolbar;
TextView texttitle;
    MediaPlayer mp=new MediaPlayer();
    LottieAnimationView animation;
RecyclerView recycler,recycler1;
String[] s;
    ArrayList<missing_asc_recycler_structure> arrayList=new ArrayList<>();
    DBHelper dBhelper ;
    int count=0;

    ArrayList<String> arrHint=new ArrayList<>();
    ArrayList<String> arrChar = new ArrayList<>();
    ArrayList<String> arrCheck = new ArrayList<>();
    ArrayList<Integer> arrBlanks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_desc_order);
        recycler=findViewById(R.id.recycler);
        recycler1=findViewById(R.id.recycler1);
        animation = findViewById(R.id.animation);
        toolbar=findViewById(R.id.toolbar);
        texttitle=findViewById(R.id.texttitle);
        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#FE953A"));
        texttitle.setText(" DESCENDING ORDER");
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#FE953A"));
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            /* getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);*/

        }
       getNumber();



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
        return false;
    }
    private void getNumber() {
        arrChar.clear();
        arrCheck.clear();
        arrBlanks.clear();
        arrHint.clear();
        dBhelper = dBhelper.getDB(this);
        if(!dBhelper.checkDb()){
            dBhelper.createDB(this);
        }
        dBhelper.openDB();
        arrayList=dBhelper.getNumber(1);
        for(int i=0;i<arrayList.size();i++) {
            Log.d("arrayList", arrayList.get(i).text);
        }
        String value=arrayList.get(count).text;
        /*  Log.d("value", value);*/
        s=value.split(",");
//        Log.d("number", s[0]+" "+s.length);
        for (String s1 : s) {
            Log.d("number", s1);
            arrChar.add(s1);
            arrHint.add(s1);
        }
        arrCheck=arrChar;
       // arrHint=arrCheck;


        arrBlanks=getBlankIndexs();

        recycler.setLayoutManager(new GridLayoutManager(this,7));
        missing_desc_recycler_adapter adapter=new  missing_desc_recycler_adapter(this,arrChar,arrBlanks);
        recycler.setAdapter(adapter);

        Collections.shuffle(arrHint);
        recycler1.setLayoutManager(new GridLayoutManager(this,7));
        missing_descending_recycler_adapter adapterHint=new  missing_descending_recycler_adapter (this,arrHint,arrChar, arrBlanks);
        recycler1.setAdapter(adapterHint);
    }

    private ArrayList<Integer> getBlankIndexs() {
        int noOfBlanks = (arrChar.size() * 50) / 100;

        ArrayList<Integer> arrIndexs = new ArrayList<>();

        for (int i = 0; i < arrChar.size(); i++) {
            arrIndexs.add(i);
        }

        Collections.shuffle(arrIndexs);

        ArrayList<Integer> arrBlankIndex = new ArrayList<>();

        for (int i = 0; i < noOfBlanks; i++) {
            arrBlankIndex.add(arrIndexs.get(i));
        }
        Collections.sort(arrBlankIndex);
        return arrBlankIndex;
    }
    public void AddLetter(String alpha) {
        int pos = arrBlanks.get(0);
        arrCheck.set(pos, alpha);
        arrBlanks.remove(0);
        Log.d("arrChar",""+arrChar);
        recycler.setLayoutManager(new GridLayoutManager(this,7));
        missing_desc_recycler_adapter adapter=new  missing_desc_recycler_adapter(this,arrChar,arrBlanks);
        recycler.setAdapter(adapter);
        if (arrBlanks.isEmpty()) {
            String check = "";
            String sCheck="";
            ArrayList<String> arrSCheck = new ArrayList<>();


            for (int i = 0; i < arrChar.size(); i++) {

                check += arrCheck.get(i);



            }
            for (String s1 : s) {
                Log.d("number", s1);
                arrSCheck.add(s1);
            }

            for (int i = 0; i<arrSCheck.size(); i++){
                sCheck += arrSCheck.get(i) ;
            }
            Log.d("check11",check);
            //Toast.makeText(this, ""+check+"arraylist"+value, Toast.LENGTH_SHORT).show();

            if (check.equals(sCheck)) {
                /*animation.setVisibility(View.VISIBLE);
                animation.setAnimation(R.raw.bluestar);
                animation.playAnimation();*/
                mp=MediaPlayer.create(getApplicationContext(),R.raw.oh_yes);
                mp.start();
                Toast.makeText(this, "Well Done", Toast.LENGTH_SHORT).show();
//                getword();
                count++;
                if (count <arrayList.size()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getNumber();
                            /*animation.setVisibility(View.INVISIBLE);*/
                        }
                    },1000);

                }
                else
                {
                    Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show();
                }

            } else {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                mp=MediaPlayer.create(getApplicationContext(),R.raw.no);
                mp.start();
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNumber();
                    }
                },600);

            }

        }
    }
}
