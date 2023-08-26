package com.poohkidslearning;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.provider.MediaStore;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class profile extends AppCompatActivity {
    Toolbar toolbar;
    TextView texttile;
    CircleImageView userImage;
    Button btn_next,btn_skip;
    LinearLayout btnGallery,btnCamera;
    RelativeLayout imgProfile;
    EditText editName;
    DBHelper dbHelper;
    ArrayList<profile_suitcase> arrUser=new ArrayList<>();
    profile_suitcase suitcase=new profile_suitcase();
    ArrayList<dash_recycler_structure> arrStructureRecyclerDash=new ArrayList<>();
    byte[] image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgProfile=findViewById(R.id.imgProfile);
        userImage=findViewById(R.id.userImage);
        editName=findViewById(R.id.editName);
        dbHelper=dbHelper.getDB(profile.this);
        if (!dbHelper.checkDb()){
            dbHelper.createDB(profile.this);
        }
        dbHelper.openDB();
        //TOOLBAR
        toolbar = findViewById(R.id.toolbar);
        texttile = findViewById(R.id.texttitle);
        btn_next=findViewById(R.id.btn_next);
        btn_skip=findViewById(R.id.btn_skip);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suitcase.img=image1;
                suitcase.name=editName.getText().toString();
                dbHelper.userInsertImg(suitcase);
                Intent i=new Intent(profile.this,dashboard.class);
                startActivity(i);
                finishAffinity();
            }
        });
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(profile.this,dashboard.class);
                startActivity(i);
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog select_photo = new Dialog(profile.this);
                select_photo.setContentView(R.layout.profile_select_dialog);
                btnCamera = select_photo.findViewById(R.id.btnCamera);
                btnGallery = select_photo.findViewById(R.id.btnGallery);
                select_photo.show();
                btnGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iGallery = new Intent(Intent.ACTION_GET_CONTENT);
                        iGallery.setType("image/*");
                        startActivityForResult(iGallery, 0);
                        select_photo.dismiss();
                    }
                });
                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent iCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(iCam, 1);
                        select_photo.dismiss();
                    }
                });

            }

        });
        arrUser = dbHelper.getProfile();
        for (int i = 0; i < arrUser.size(); i++) {
            Log.d("user_name", arrUser.get(i).name);
        }
        if (arrUser.size() > 0) {
            setUserProfile();
        }
        texttile.setGravity(Gravity.CENTER_HORIZONTAL);
        setSupportActionBar(toolbar);
        texttile.setText("\t \t \t\t\t\t\t\t\tSTUDENT");
        //notification bar color
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#C1392B"));
        }

        /*if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_more_vert_black_24dp);
        }*/

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //camera
                Bitmap image = (Bitmap) data.getExtras().get("data");
                userImage.setImageBitmap(image);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 50, stream);
                image1 = stream.toByteArray();
            } else {
                //gallery

                try {
                    Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    image1 = stream.toByteArray();
                    userImage.setImageBitmap(image);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
    public void arrAdd(int img,int img1,String name){
        dash_recycler_structure contact=new dash_recycler_structure();
        contact.img_number_b1=img;
        contact.img_numbers=img1;
        contact.txt_match=name;
        arrStructureRecyclerDash.add(contact);
    }
    public void setUserProfile() {

        arrUser = dbHelper.getProfile();
        for (int i = 0; i < arrUser.size(); i++) {
            Log.d("user_name", arrUser.get(i).name);
        }

        editName.setText(arrUser.get(0).name);
        byte[] pic = arrUser.get(0).img;
        Bitmap image = BitmapFactory.decodeByteArray(pic, 0, pic.length);
        userImage.setImageBitmap(image);

    }


}
