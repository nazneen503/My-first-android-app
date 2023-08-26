package com.poohkidslearning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class dash_counting extends AppCompatActivity {
    Toolbar toolbar;
    TextView texttitle;
    ViewPager viewPagercount;
    DBHelper dbHelper;
    Button btn_pre, btn_next;
    ImageView image;
    Button opt1, opt2, opt3;
    LottieAnimationView animation;
    int ans;
    int count = 0;
    ArrayList<suitcase_counting> countingArrayList = new ArrayList<>();
    ArrayList<dash_recycler_structure> arrStructureRecyclerDash = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_counting);
        btn_pre = findViewById(R.id.btn_pre);
        btn_next = findViewById(R.id.btn_next);
        animation = findViewById(R.id.animation);
        image = findViewById(R.id.image);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        setquiz();


        /*  viewPagercount=findViewById(R.id.viewPagercount);*/


        toolbar = findViewById(R.id.toolbar);
        texttitle = findViewById(R.id.texttitle);
        texttitle.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#FE953A"));
        texttitle.setText("COUNTING");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#FE953A"));
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            /* getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);*/
        }
//        view_pager_counting adapter=new view_pager_counting(this,countingArrayList);
//        viewPagercount.setAdapter(adapter);


    }

    public void check(View a) {
        int id = a.getId();
        if ((id == R.id.opt1 && ans == 1) || (id == R.id.opt2 && ans == 2)
                || (id == R.id.opt3 && ans == 3)) {
            animation.setVisibility(View.VISIBLE);
            animation.setAnimation(R.raw.bluestar);
            animation.playAnimation();


            Toast.makeText(this, "Bingo....you nailed it!!", Toast.LENGTH_SHORT).show();
            if (count < countingArrayList.size()) {
                count++;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       animation.setVisibility(View.INVISIBLE);
                        setquiz();
                    }
                }, 1500);

            }
        } else {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
            Toast.makeText(this, "Wrong answer...", Toast.LENGTH_SHORT).show();
            count++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setquiz();
                }
            }, 1000);

        }
    }

    private void setquiz() {

        dbHelper = dbHelper.getDB(this);
        if (!dbHelper.checkDb()) {
            dbHelper.createDB(this);
        }
        dbHelper.openDB();

        countingArrayList = dbHelper.getCounting();
        byte[] pic = countingArrayList.get(count).image;
        Bitmap image1 = BitmapFactory.decodeByteArray(pic, 0, pic.length);
        image.setImageBitmap(image1);
        opt1.setText("" + countingArrayList.get(count).opt1);
        opt2.setText("" + countingArrayList.get(count).opt2);
        opt3.setText("" + countingArrayList.get(count).opt3);
        ans = countingArrayList.get(count).correct_ans;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return false;
    }


}
